#include "Caesar.h"
#include <iostream>

Caesar::Caesar() : m_Key(1)
{}


void Caesar::Start()
{
	std::cout << "Caesar App" << std::endl;

	std::string p = "Hello";
	std::string e = EncryptKey(p);
	std::string d = DecryptKey(e);

	std::cout << "first: " << p << std::endl
		<< "encrypt: " << e << std::endl
		<< "decrypt: " << d << std::endl;
}

std::string Caesar::EncryptKey(std::string& key)
{
	std::string n;

	for (int i = 0; i < key.size(); i++)
		n += EncryptChar(key[i]);

	return n;
}

std::string Caesar::DecryptKey(std::string& key)
{
	std::string n;

	for (int i = 0; i < key.size(); i++)
		n += DecryptChar(key[i]);

	return n;
}

char Caesar::EncryptChar(char c)
{
	return (char)((int)c + m_Key);
}

char Caesar::DecryptChar(char c)
{
	return (char)((int)c - m_Key);
}
