using System.Security.Cryptography;

namespace SymmetricalCrypto.Crypto
{
    public class RngHardwareGenerator : IGenerator
    {
        /// <inheritdoc />
        public byte[] CreateKey(uint size)
        {
            byte[] arr = new byte[size];

            using (RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider())
            {
                rng.GetBytes(arr);
            }
            return arr;
        }
    }
}
