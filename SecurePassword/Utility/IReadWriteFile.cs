using System.Collections.Generic;

namespace SecurePassword.Utility
{
    public interface IReadWriteFile<T>
    {
        List<T> ReadFromFile();
        void WriteToFile(T obj);
        void WriteAllToFile(T[] obj);
    }
}
