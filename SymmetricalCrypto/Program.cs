using SymmetricalCrypto.Crypto;
using SymmetricalCrypto.Model;
using System;
using System.Linq;

namespace SymmetricalCrypto
{
    class Program
    {
        static uint desKeySize = 8;
        static uint desIvSize = 8;

        static uint aesKeySize = 32;
        static uint aesIvSize = 16;

        static void Main(string[] args)
        {
            IGenerator generator = new RngHardwareGenerator();
            SymmetricalKey desKey = new SymmetricalKey(generator.CreateKey(desKeySize), generator.CreateKey(desIvSize));
            SymmetricalKey aesKey = new SymmetricalKey(generator.CreateKey(aesKeySize), generator.CreateKey(aesIvSize));

            bool exit = false;

            while(!exit)
            {
                Console.Clear();

                Console.WriteLine("1. Generate keys");
                Console.WriteLine("2. DES");
                Console.WriteLine("3. AES");

                if (int.TryParse(Console.ReadLine(), out int c))
                    switch (c)
                    {
                        case 1:
                            desKey = new SymmetricalKey(generator.CreateKey(desKeySize), generator.CreateKey(desIvSize));
                            aesKey = new SymmetricalKey(generator.CreateKey(aesKeySize), generator.CreateKey(aesIvSize));
                            Console.WriteLine("New keys for all the algorithms has been generated");
                            break;
                        case 2:
                            CryptoScreen(new DesCrypto(desKey));
                            break;
                        case 3:
                            CryptoScreen(new AesCrypto(aesKey));
                            break;
                    }

                Console.ReadKey();
            }
        }

        /// <summary>
        /// The screen when a crypto mode has been choosen.
        /// </summary>
        /// <param name="crypto">The crypto algorithm to use.</param>
        static void CryptoScreen(ICrypto crypto)
        {
            Console.Clear();
            Console.WriteLine("1. Encrypt");
            Console.WriteLine("2. Decrypt");

            if (int.TryParse(Console.ReadLine(), out int c))
                switch (c)
                {
                    case 1:
                        Console.WriteLine("insert value to encrypt");
                        string val = Console.ReadLine();

                        byte[] enc = crypto.Encrypt(val);
                        Console.WriteLine("Unecrypted");
                        Console.WriteLine("-----------------------");
                        Console.WriteLine("ASCII: " + val);
                        Console.WriteLine("HEX: " + StringToHexString(val));

                        Console.WriteLine("\nEncrypted");
                        Console.WriteLine("-----------------------");
                        Console.WriteLine("ASCII: " + Convert.ToBase64String(enc));
                        Console.WriteLine("Hex: " + StringToHexString(Convert.ToBase64String(enc)));
                        break;
                    case 2:
                        Console.WriteLine("insert value to decrypt");
                        string val2 = Console.ReadLine();

                        string dec = crypto.Decrypt(Convert.FromBase64String(val2));
                        Console.WriteLine("Unecrypted");
                        Console.WriteLine("-----------------------");
                        Console.WriteLine("ASCII: " + dec);
                        Console.WriteLine("HEX: " + StringToHexString(dec));

                        Console.WriteLine("\nEncrypted");
                        Console.WriteLine("-----------------------");
                        Console.WriteLine("ASCII: " + val2);
                        Console.WriteLine("Hex: " + StringToHexString(val2));

                        break;
                    default:
                        break;
                }

            Console.ReadKey();

        }

        /// <summary>
        /// String to hex string
        /// </summary>
        /// <returns>A string representing the value given as hex</returns>
        static string StringToHexString(string val)
        {
            return string.Join("", val.Select(c => ((int)c).ToString("X2")));
        }
    }
}
