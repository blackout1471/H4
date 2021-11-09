using SymmetricalCrypto.Model;
using System.IO;
using System.Security.Cryptography;

namespace SymmetricalCrypto.Crypto
{
    public class DesCrypto : ICrypto
    {
        /// <inheritdoc />
        public SymmetricalKey Key { get; private set; }

        public DesCrypto(SymmetricalKey key)
        {
            Key = key;
        }

        /// <inheritdoc />
        public string Decrypt(byte[] value)
        {
            using (var des = new DESCryptoServiceProvider())
            {
                des.Mode = CipherMode.CBC;
                des.Padding = PaddingMode.PKCS7;

                des.Key = Key.Key;
                des.IV = Key.Iv;

                using (var memoryStream = new MemoryStream())
                {
                    using (var cs = new CryptoStream(memoryStream, des.CreateDecryptor(), CryptoStreamMode.Write))
                    {
                        cs.Write(value, 0, value.Length);
                        cs.FlushFinalBlock();

                        return System.Text.Encoding.Default.GetString(memoryStream.ToArray());
                    }        
                }
            }
        }

        /// <inheritdoc />
        public byte[] Encrypt(string value)
        {
            byte[] byteArr = System.Text.Encoding.ASCII.GetBytes(value);

            using (var des = new DESCryptoServiceProvider())
            {
                des.Mode = CipherMode.CBC;
                des.Padding = PaddingMode.PKCS7;

                des.Key = Key.Key;
                des.IV = Key.Iv;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, des.CreateEncryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(byteArr, 0, byteArr.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }
    }
}
