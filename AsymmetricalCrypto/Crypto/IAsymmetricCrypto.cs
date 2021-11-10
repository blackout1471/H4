namespace AsymmetricalCrypto.Crypto
{
    interface IAsymmetricCrypto
    {
        /// <summary>
        /// The path to the public key
        /// </summary>
        string PublicKeyPath { get;}

        /// <summary>
        /// The path to the private key
        /// </summary>
        string PrivateKeyPath { get; }

        /// <summary>
        /// Generates new keys to the path from public and private.
        /// </summary>
        /// <param name="size">The size of the keys in bits.</param>
        void WriteNewKeys(uint size);

        /// <summary>
        /// Encrypt a message with the current crypto algorithm.
        /// </summary>
        /// <param name="value">The plain text value to be encrypted.</param>
        /// <returns>The encrypted data as Base64</returns>
        string Encrypt(string value);

        /// <summary>
        /// Takes the given base64 value and decrypts it to plain text.
        /// </summary>
        /// <param name="value">The encrypted data in base 64</param>
        /// <returns>The decrypted data as plain text.</returns>
        string Decrypt(string value);
    }
}
