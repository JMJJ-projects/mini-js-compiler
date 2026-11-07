// Les 12 premiers termes de la suite de Fibonacci
a = 0;
b = 1;
k = 0;
do {
    print(a);
    t = a + b;
    a = b;
    b = t;
    k = k + 1;
} while (k < 12);
