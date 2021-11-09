#include <Windows.h>
#include <conio.h>

#include "Performance.h"
#include "Caesar.h"
#include "Hasher.h"

int main()
{
    //Performance check.
    auto p = new Performance();
    p->Start();

    Sleep(5000);

    // Caesar
    auto c = new Caesar();
    c->Start();

    Sleep(5000);

    // Hasher
    auto h = new Hasher();
    h->Start();

    Sleep(5000);
}


