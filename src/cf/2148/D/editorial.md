# 2148D - Destruction of the Dandelion Fields

- **Link:** https://codeforces.com/contest/2148/problem/D
- **Tópicos:** Constructive Algorithms, Greedy, Sortings
- **Ideia central:** Contar o máximo de flores que podem ser destruídas, considerando o estado do cortador de grama.
- **Insight:** Um campo com uma quantidade ímpar de flores inverte o estado do cortador de grama, logo, para conseguir cortar alguma flor, é necessário pelo menos um ímpar no array. Como um campo com número par de flores não altera o estado do cortador de grama, basta ligar o cortador de grama no começo com um campo ímpar e cortar todas as flores de campos pares. Quando sobrar só campos ímpares, o cortador vai ficar alternando entre ligado e desligado, ou seja, o primeiro campo ímpar vai ser cortado, o segundo não, o terceiro sim, e assim por diante. Então, basta ordenar os campos ímpares, cortar os maiores e descartar os menores.
- **Complexidade:** Tempo $O(N\log{N})$, Espaço $O(N)$
- **Pontos de atenção:** Se o número de campos ímpares $K$ for par, serão cortados $K/2$ campos e os outros serão descartados. Se $K$ for ímpar, serão cortados $(K+1)/2$ campos e os outros $(K-1)/2$ serão descartados.