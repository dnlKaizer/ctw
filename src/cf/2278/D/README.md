# 2278D - Lock and Unlock

- **Link:** https://codeforces.com/contest/2278/problem/D
- **Tópicos:** Greedy, Sorting
- **Ideia central:** Dado um vetor de $N$ inteiros, determinar o número mínimo de operações necessárias para que todos os elementos do vetor sejam iguais, onde cada operação consiste em escolher um conjunto de elementos e incrementar ou decrementar todos que não pertencem a esse conjunto.
- **Insight:** A princípio, ordenar o vetor. Para cada elemento $a_i$ e $a_{i+1}$ do vetor, precisamos de $a_{i+1} - a_i$ operações para igualar esses dois elementos. A ideia é que podemos tanto incrementar $a_i$ quanto decrementar $a_{i+1}$, então escolhemos aquele em que seja necessário fixar uma menor quantidade de elementos, ou seja, $min(i-1, N-i-1)$. Logo, para cada par de elementos consecutivos, calculamos $(a_{i+1} - a_i) * min(i, N-i)$ e somamos todos esses valores para obter o número mínimo de operações necessárias. Além disso, o número mínimo de operações necessárias vai ser sempre igual a $a_{N-1} - a_0$, que é o valor máximo menos o valor mínimo do vetor.
- **Complexidade:** Tempo $O(N\log{N})$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum