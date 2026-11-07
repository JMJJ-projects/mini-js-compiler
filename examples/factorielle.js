/* Factorielle de 10 avec une boucle do-while */
n = 10;
resultat = 1;
i = 1;
do {
    resultat = resultat * i;
    i = i + 1;
} while (i <= n);
print(resultat);
