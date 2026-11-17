# Minimizing Coins

- **Link:** https://cses.fi/problemset/task/1634/
- **Tópicos:** DP
- **Ideia central:** Dado um inteiro $X$ e um conjunto de $N$ moedas de valores $c_1, c_2, ..., c_n$, determinar o número mínimo de moedas necessárias para formar o valor $X$. Se não for possível formar o valor, retornar -1.
- **Insight:** Usar uma $dp$ array onde $dp[i]$ representa o número mínimo de moedas necessárias para formar o valor $i$. Inicializar $dp[0] = 0$ e para cada valor de $1$ a $X$, atualizar $dp[i]$ considerando todas as moedas disponíveis. Como $1 \leq N \leq 100$ e $1 \leq X \leq 10^6$, daria $10^8$ operações no pior caso, o que passa em menos de 1 segundo
- **Complexidade:** Tempo $O(N\times{X})$, Espaço $O(X)$
- **Pontos de atenção:** Nenhum