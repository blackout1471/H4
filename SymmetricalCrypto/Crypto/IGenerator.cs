namespace SymmetricalCrypto.Crypto
{
    public interface IGenerator
    {
        /// <summary>
        /// Generates a random byte array from the size given.
        /// </summary>
        byte[] CreateKey(uint size);
    }
}
