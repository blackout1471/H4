using SecurePassword.Utility;
using System.Collections.Generic;
using System;

namespace SecurePassword
{
    public class UserLogin
    {
        private readonly IReadWriteFile<UserDto> _userAccess;
        private readonly ICryptoProvider _provider;

        private readonly byte _maxLoginTries;
        private readonly int _timoutInMinutes;

        private const int hashIterations = 20000;
        private const int hashSize = 128;
        private const int saltSize = 32;

        public UserLogin(IReadWriteFile<UserDto> userAccess, ICryptoProvider provider, byte maxLoginTries, int timeoutInMinutes)
        {
            _userAccess = userAccess ?? throw new ArgumentException("No connection to user db was given");
            _provider = provider ?? throw new ArgumentException("No provider was given");

            _maxLoginTries = maxLoginTries == 0 ? (byte)1 : maxLoginTries;
            _timoutInMinutes = timeoutInMinutes == 0 ? 1 : timeoutInMinutes;
        }

        /// <summary>
        /// Checks all credentials and whether the user can login.
        /// </summary>
        /// <returns>True if login is successfull, and false if not.</returns>
        public bool Login(string username, string password)
        {
            List<UserDto> users = _userAccess.ReadFromFile();
            UserDto curUser = GetUser(username, users);

            if (curUser is null)
                return false;

            if (IsAccountLocked(curUser))
                throw new AccessViolationException("The account is locked");

            if (!CheckPassword(curUser, password))
            {
                UpdateLoginTries(curUser);
                return false;
            }

            return true;
        }

        /// <summary>
        /// Creates a new user, from the given username and password
        /// </summary>
        /// <returns>True if successfull, false if not</returns>
        public bool CreateUser(string username, string password)
        {
            List<UserDto> users = _userAccess.ReadFromFile();

            if (String.IsNullOrEmpty(username) || String.IsNullOrEmpty(password))
                throw new ArgumentException("Username or password is empty");

            if (GetUser(username, users) is not null)
                throw new ArgumentException("User already exists");

            // Salt
            byte[] salt = _provider.GenerateSalt(saltSize);
            byte[] hashedPass = _provider.Hash(password, salt, hashIterations, hashSize);

            UserDto newUser = new UserDto(username, Convert.ToBase64String(hashedPass), 0, DateTime.Now, Convert.ToBase64String(salt));
            _userAccess.WriteToFile(newUser);

            return true;
        }

        /// <summary>
        /// Checks whether the given password matches the password in db.
        /// </summary>
        /// <returns>Whether the password matches</returns>
        private bool CheckPassword(UserDto usr, string password)
        {
            byte[] salt = Convert.FromBase64String(usr.Salt);
            byte[] pass = _provider.Hash(password, salt, hashIterations, hashSize);

            return usr.Password == Convert.ToBase64String(pass);
        }


        /// <summary>
        /// Checks whether the users account is still locked.
        /// And updates the tries if the time is past.
        /// </summary>
        private bool IsAccountLocked(UserDto usr)
        {
            if (usr.LoginTryCount >= _maxLoginTries)
                if (usr.LastModifyDate.AddMinutes(_timoutInMinutes) > DateTime.Now)
                    return true;
                else
                    ResetTriesForUser(usr);

            return false;
        }


        /// <summary>
        /// Updates the login tries for the given user  to be plus 1 try.
        /// </summary>
        private void UpdateLoginTries(UserDto usr)
        {
            usr.LastModifyDate = DateTime.Now;
            usr.LoginTryCount++;

            _userAccess.WriteToFile(usr);
        }

        /// <summary>
        /// Resets the given users login tries.
        /// </summary>
        private void ResetTriesForUser(UserDto usr)
        {
            usr.LastModifyDate = DateTime.Now;
            usr.LoginTryCount = 0;

            _userAccess.WriteToFile(usr);
        }

        /// <summary>
        /// Checks whether the user exists in the list
        /// </summary>
        /// <returns>an user if exists, else null</returns>
        private UserDto GetUser(string username, List<UserDto> users)
        {
            return users.Find(usr => usr.Username == username);
        }
    }
}
