#include "Hasher.h"
#include "Md5.h"

#include <iostream>
#include <functional>

Hasher::Hasher()
{
}

Hasher::~Hasher()
{
}

void Hasher::Start()
{
	char c = ChooseHashing();
	std::string res;

	switch (c)
	{
	case '1':
		res = Md5Choosen();
		break;
	case '2':
		break;
	default:
		c = ChooseHashing();
	}

	std::cout << "ASCII: " << res << " Hex: ";
	for (const auto& c : res)
		std::cout << std::hex << (int)c;

	std::cout << std::endl;
}

char Hasher::ChooseHashing()
{
	char c;

	std::cout << "Choose hashing alg: 1. MD5 2: SHA1" << std::endl;
	std::cin >> c;

	return c;
}

std::string Hasher::Md5Choosen()
{
	std::string c;
	std::cout << "Enter string: ";
	std::cin >> c;

	return md5(c);
}

std::string Hasher::SHA1Choosen()
{
	std::string c;
	std::cout << "Enter string: ";
	std::cin >> c;

	// SHA


	return md5(c);
}
