using SymmetricalCrypto.Model;
using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace SymmetricalCrypto.Crypto
{
    public class AesCrypto : ICrypto
    {
        /// <inheritdoc />
        public SymmetricalKey Key { get; private set; }


        public AesCrypto(SymmetricalKey key)
        {
            Key = key;
        }

        /// <inheritdoc />
        public string Decrypt(byte[] value)
        {
            using (var des = new AesCryptoServiceProvider())
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

                        return Encoding.Default.GetString(memoryStream.ToArray());
                    }
                }
            }
        }

        /// <inheritdoc />
        public byte[] Encrypt(string value)
        {
            byte[] byteArr = Encoding.ASCII.GetBytes(value);

            using (var des = new AesCryptoServiceProvider())
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
