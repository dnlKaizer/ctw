# Removing Digits

- **Link:** https://cses.fi/problemset/task/1637/
- **Tópicos:** DP
- **Ideia central:** Dado um inteiro positivo $N$, determinar o número mínimo de passos para reduzir $N$ a $0$. Em cada passo, você pode subtrair de $N$ qualquer dígito presente nele.
- **Insight:** Criar um array $dp$ de tamanho $N+1$, onde $dp[i]$ representa o número mínimo de passos para reduzir $i$ a $0$. Inicializar $dp[0] = 0$ e os outros valores como infinito. Para cada número de $1$ a $N$, iterar sobre seus dígitos e atualizar $dp[i] = min(dp[i], dp[i - d] + 1)$, onde $d$ é um dígito de $i$. O resultado final será armazenado em $dp[N]$.
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum