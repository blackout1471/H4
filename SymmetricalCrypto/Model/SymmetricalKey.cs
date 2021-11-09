namespace SymmetricalCrypto.Model
{
    public class SymmetricalKey
    {
        /// <summary>
        /// byte array representing a key
        /// </summary>
        public byte[] Key { get; private set; }

        /// <summary>
        /// byte array representing the Iv
        /// </summary>
        public byte[] Iv { get; private set; }

        public SymmetricalKey(byte[] key, byte[] iv)
        {
            Key = key;
            Iv = iv;
        }
    }
}
