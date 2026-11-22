# 2285A - Composite Permutation

- **Link:** https://codeforces.com/contest/2285/problem/A
- **Tópicos:** Constructive Algorithms, Math
- **Ideia central:** Construir uma permutação $p$ de tamanho $N$ tal que $i \cdot p_i$ não é primo ou igual a $1$ para todo $1 \leq i \leq N$, ou determinar que ela não existe
- **Insight:** A única forma de $i \times p_i$ ser um número primo é se $i = 1$ e $p_i$ for primo ou for igual a $1$. Logo, para $N \leq 3$ ela nunca existe, pois tanto $1$, $2$ ou $3$ multiplicados por $i = 1$ ferem a regra do problema. Para $N > 3$, podemos forçar o $p_1 = 4$ e $p_4 = 1$, já o resto dos números pode ser $p_i = i$
- **Complexidade:** Tempo $O(N)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum