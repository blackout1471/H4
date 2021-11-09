using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;

namespace SecurePassword.Utility
{
    public class FileUtility : IReadWriteFile<UserDto>
    {
        private readonly string _fileDestination;

        public FileUtility(string fileDestination)
        {
            _fileDestination = fileDestination ?? throw new ArgumentException("No filedestination was given");
            if (!File.Exists(_fileDestination))
            {
                FileStream stream = File.Create(_fileDestination);
                stream.Close();
            }
        }

        /// <summary>
        /// Reads all contents from the user file.
        /// </summary>
        /// <returns>All the users</returns>
        public List<UserDto> ReadFromFile()
        {
            string json;

            try
            {
                json = File.ReadAllText(_fileDestination);
            }
            catch (Exception)
            {
                throw;
            }
            

            if (String.IsNullOrEmpty(json))
                return new List<UserDto>();

            return JsonSerializer.Deserialize<List<UserDto>>(json);
        }

        /// <summary>
        /// Writes all the users to the file.
        /// Overwrites if there is content.
        /// </summary>
        public void WriteAllToFile(UserDto[] obj)
        {
            string fileData = JsonSerializer.Serialize(obj);

            try
            {
                File.WriteAllText(_fileDestination, fileData);
            }
            catch (Exception)
            {
                throw;
            }  
        }


        /// <summary>
        /// If the given user exists, it will be updated in the user list and written,
        /// else it will be added as new.
        /// </summary>
        public void WriteToFile(UserDto obj)
        {
            List<UserDto> users = ReadFromFile();
            int index = users.FindIndex(obj => obj.Username == obj.Username);

            if (index != -1)
                users[index] = obj;
            else
                users.Add(obj);

            try
            {
                WriteAllToFile(users.ToArray());
            }
            catch (Exception)
            {
                throw;
            }      
        }
    }
}
