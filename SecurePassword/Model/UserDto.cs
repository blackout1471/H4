using System;

namespace SecurePassword.Utility
{
    public class UserDto
    {
        public string Username { get; private set; }

        public string Password { get; private set; }

        public byte LoginTryCount { get; set; }

        public DateTime LastModifyDate { get; set; }

        public string Salt { get; private set; }

        public UserDto(string username, string password, byte loginTryCount, DateTime lastModifyDate, string salt)
        {
            Username = username;
            Password = password;
            LoginTryCount = loginTryCount;
            LastModifyDate = lastModifyDate;
            Salt = salt;
        }
    }
}
