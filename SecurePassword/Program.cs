using SecurePassword.Crypto;
using SecurePassword.Utility;
using System;

namespace SecurePassword
{
    class Program
    {
        static void Main(string[] args)
        {
            const string dest = "userdb.txt";
            FileUtility dbContext = new FileUtility(dest);
            UserLogin usrLogin = new UserLogin(dbContext, new CryptoProvider(), 3, 1);
            bool hasClosed = false;

            while (!hasClosed)
            {
                Console.Clear();

                Console.WriteLine("1. Login");
                Console.WriteLine("2. Create User");
                Console.WriteLine("3. Exit");
                string key = Console.ReadLine();

                if (Int32.TryParse(key, out int ans))
                    switch (ans)
                    {
                        case 1:
                            LoginScreen(usrLogin);
                            break;
                        case 2:
                            CreateUserScreen(usrLogin);
                            break;
                        case 3:
                            hasClosed = true;
                            break;
                        default:
                            break;
                    }
            }

            
        }

        static void LoginScreen(UserLogin usrLogin)
        {
            Console.Clear();

            bool isTrying = true;
            while (isTrying)
            {
                Console.Write("Enter username: ");
                string username = Console.ReadLine();

                Console.Write("Enter Password: ");
                string pass = Console.ReadLine();

                try
                {
                    if (usrLogin.Login(username, pass))
                    {
                        Console.WriteLine("You have been logged in");
                        isTrying = false;
                    }
                    else
                        Console.WriteLine("Credentials were not correct");
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }

                Console.ReadKey();
            }
        }

        static void CreateUserScreen(UserLogin usrLogin)
        {
            Console.Clear();

            Console.Write("Enter username: ");
            string username = Console.ReadLine();

            Console.Write("Enter Password: ");
            string pass = Console.ReadLine();

            try
            {
                if (usrLogin.CreateUser(username, pass))
                    Console.WriteLine("User has been created");
                else
                    Console.WriteLine("User has not been created");
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Console.ReadKey();
            }

            Console.ReadKey();
        }
    }
}
