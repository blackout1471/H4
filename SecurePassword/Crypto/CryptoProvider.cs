using System.Security.Cryptography;

namespace SecurePassword.Crypto
{
    public class CryptoProvider : ICryptoProvider
    {
        public CryptoProvider()
        {}

        /// <summary>
        /// Generates a salt from a given size.
        /// </summary>
        /// <param name="size"></param>
        /// <returns>A byte array with random values</returns>
        public byte[] GenerateSalt(int size)
        {
            byte[] salt = new byte[size];

            using (RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider())
            {
                rng.GetBytes(salt);
            }

            return salt;
        }

        /// <summary>
        /// Hashes the value given, and inserts salt with the given iterations and size.
        /// </summary>
        /// <returns>The value hashed, in a byte array.</returns>
        public byte[] Hash(string value, byte[] salt, int iterations, int hashSize)
        {
            using (Rfc2898DeriveBytes hashGenerator = new Rfc2898DeriveBytes(value, salt))
            {
                hashGenerator.IterationCount = iterations;
                return hashGenerator.GetBytes(hashSize);
            }
        } 
    }
}
