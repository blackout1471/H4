using System;
using System.IO;
using System.Security.Cryptography;

namespace AsymmetricalCrypto.Crypto
{
    public class RsaXml : IAsymmetricCrypto
    {
        /// <inheritdoc />
        public string PublicKeyPath { get; private set; }

        /// <inheritdoc />
        public string PrivateKeyPath { get; private set; }

        /// <summary>
        /// Event for key information.
        /// </summary>
        public Action<string> KeyInformationEvent { private get; set; }

        public RsaXml(string publicKeyPath, string privateKeyPath)
        {
            PublicKeyPath = publicKeyPath;
            PrivateKeyPath = privateKeyPath;
        }

        /// <inheritdoc />
        public string Decrypt(string value)
        {
            // decrypt
            using (var rsa = new RSACryptoServiceProvider())
            {
                rsa.PersistKeyInCsp = false;

                rsa.FromXmlString(File.ReadAllText(PrivateKeyPath));

                KeyInformationEvent?.Invoke(RsaParametersToString(rsa.ExportParameters(true)));

                return System.Text.ASCIIEncoding.UTF8.GetString(rsa.Decrypt(Convert.FromBase64String(value), true));
            }
        }

        /// <inheritdoc />
        public string Encrypt(string value)
        {
            using (var rsa = new RSACryptoServiceProvider())
            {
                rsa.PersistKeyInCsp = false;

                rsa.FromXmlString(File.ReadAllText(PublicKeyPath));
                KeyInformationEvent?.Invoke(RsaParametersToString(rsa.ExportParameters(false)));
                return Convert.ToBase64String(rsa.Encrypt(System.Text.ASCIIEncoding.UTF8.GetBytes(value), true));
            }
        }

        /// <inheritdoc />
        public void WriteNewKeys(uint size)
        {
            using (var rsa = new RSACryptoServiceProvider())
            {
                rsa.PersistKeyInCsp = false;

                if (!File.Exists(PublicKeyPath))
                {
                    FileStream fs = File.Create(PublicKeyPath);
                    fs.Close();
                }

                if (!File.Exists(PrivateKeyPath))
                {
                    FileStream fs = File.Create(PublicKeyPath);
                    fs.Close();
                }

                try
                {
                    File.WriteAllText(PublicKeyPath, rsa.ToXmlString(false)); // public key
                    File.WriteAllText(PrivateKeyPath, rsa.ToXmlString(true)); // private key
                }
                catch (Exception)
                {
                    throw;
                }  
            }
        }

        private string RsaParametersToString(RSAParameters p)
        {
            return $"m: {Convert.ToBase64String(p.Modulus)}\n" +
                $"p: {Convert.ToBase64String(p.P ?? new byte[0])}\n" +
                $"q: {Convert.ToBase64String(p.Q ?? new byte[0])}";
        }
    }
}
