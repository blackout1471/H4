#include <Windows.h>
#include <conio.h>

#include "Performance.h"
#include "Caesar.h"
#include "Hasher.h"

int main()
{
    // Performance check.
    /*auto p = new Performance();
    p->Start();

    Sleep(5000);

    auto c = new Caesar();
    c->Start();

    Sleep(5000);*/

    auto h = new Hasher();
    h->Start();
}


