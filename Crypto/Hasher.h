#pragma once
#include <string>

class Hasher
{
public:
	Hasher();
	~Hasher();

public:
	void Start();
private:
	char ChooseHashing();
	std::string Md5Choosen();
	std::string SHA1Choosen();

};

