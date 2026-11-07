// PGCD par soustractions successives (algorithme d'Euclide)
a = 1071;
b = 462;
do {
    if (a > b) { a = a - b; } else { b = b - a; }
} while (!(a == b));
print(a);
