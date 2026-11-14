# Dice Combinations

- **Link:** https://cses.fi/problemset/task/1633
- **Tópicos:** DP, Combinatorics
- **Ideia central:** Contar o número de maneiras de obter uma soma $N$ com dados de 1 a 6
- **Insight:** Tomamos uma $dp[i]$ que representa o número de maneiras de obter a soma $i$. Dessa forma, calculamos as possibilidades a partir do último dado que for jogado, ou seja, $dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + dp[i-4] + dp[i-5] + dp[i-6]$
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Lembrar de preencher o caso base $dp[0] = 1$ e de tomar o módulo $10^9 + 7$ a cada iteração para evitar overflow.