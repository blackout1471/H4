namespace SecurePassword
{
    public interface ICryptoProvider
    {
        byte[] GenerateSalt(int size);

        byte[] Hash(string value, byte[] salt, int iterations, int hashSize);
    }
}
