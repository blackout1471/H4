using SymmetricalCrypto.Model;
namespace SymmetricalCrypto.Crypto
{
    public interface ICrypto
    {
        /// <summary>
        /// The key used to encrypt or decrypt
        /// </summary>
        public SymmetricalKey Key { get; }

        /// <summary>
        /// Encrypts the string given.
        /// </summary>
        /// <returns>The encrypted data as byte array.</returns>
        public byte[] Encrypt(string value);

        /// <summary>
        /// Decrypts the data given.
        /// </summary>
        /// <returns>The decrypted data, decoded to string.</returns>
        public string Decrypt(byte[] value);

    }
}
