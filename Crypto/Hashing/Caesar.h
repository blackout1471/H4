#include <string>

#pragma once
class Caesar
{
public:
	Caesar();
	void Start();
	std::string EncryptKey(std::string& key);
	std::string DecryptKey(std::string& key);

private:
	char EncryptChar(char c);
	char DecryptChar(char c);
private:
	int m_Key;
};

