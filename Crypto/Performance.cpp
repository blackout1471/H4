#include "Performance.h"
#include <random>
#include <iostream>
#include <chrono>

const long long Performance::GetTime(int(f))
{
    auto t1 = std::chrono::high_resolution_clock::now();
    (f);
    auto t2 = std::chrono::high_resolution_clock::now();
    return (t2 - t1).count();
}

void Performance::Start()
{
    std::random_device rd;

    std::cout << "random" << std::endl;

    for (int i = 0; i < 10; i++)
    {
        auto r = rand() % 10;

        std::cout << "result: " << r <<
            " time: " << GetTime(rand()) << "ms" <<
            std::endl;
    }


    std::cout << "crypto api" << std::endl;

    for (int i = 0; i < 10; i++)
    {
        auto r = rd() % 10;

        std::cout << "result: " << r <<
            " time: " << GetTime(rd()) << "ms" <<
            std::endl;
    }
}
