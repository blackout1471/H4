using AsymmetricalCrypto.Crypto;
using System;
using System.IO;
using System.Security.Cryptography;

namespace AsymmetricalCrypto
{
    class Program
    {
        static void Main(string[] args)
        {
            const string pPath = @"C:\Users\eadr\Desktop\";
            const string privatePath = pPath + "private.xml";
            const string publicPath = pPath + "public.xml";

            IAsymmetricCrypto ac = new RsaXml(publicPath, privatePath);
            ((RsaXml)ac).KeyInformationEvent = str => Console.WriteLine(str);
            ac.WriteNewKeys(2048);

            Console.WriteLine("Encryption");
            Console.WriteLine("---------------------");
            Console.Write("Enter message to encrypt: ");
            string message = Console.ReadLine();
            string encryptedMessage = ac.Encrypt(message);
            Console.WriteLine("Encrypted: " + encryptedMessage);

            ac = new RsaXml(publicPath, privatePath);
            ((RsaXml)ac).KeyInformationEvent = str => Console.WriteLine(str);
            Console.WriteLine("Decryption");
            Console.WriteLine("---------------------");
            Console.WriteLine("Encryption to decrypt: " + encryptedMessage);
            string decryptedMessage = ac.Decrypt(encryptedMessage);
            Console.WriteLine("Decrypted message: " + decryptedMessage);

            Console.ReadKey();

        }
    }
}
