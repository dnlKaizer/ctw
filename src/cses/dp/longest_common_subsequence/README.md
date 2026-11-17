# Longest Common Subsequence

- **Link:** https://cses.fi/problemset/task/3403/
- **Tópicos:** DP
- **Ideia central:** Dados dois arrays $a$ e $b$ de inteiros, encontrar a maior subsequência comum entre eles
- **Insight:** Usar uma $dp[i][j]$ que representa o tamanho da maior subsequência comum entre os prefixos dos arrays até os índices $i$ e $j$. A transição consiste em comparar os elementos $a_i$ e $b_j$: (1) se forem iguais, então $dp[i][j] = dp[i-1][j-1] + 1$; (2) caso contrário, $dp[i][j] = \max(dp[i-1][j], dp[i][j-1])$, pois teremos que ignorar um dos elementos
- **Complexidade:** Tempo $O(N \times M)$, Espaço $O(N \times M)$
- **Pontos de atenção:** Daria para resolver o problema com espaço $O(\min(N, M))$ se o problema fosse apenas calcular o tamanho da maior subsequência comum, mas como precisamos reconstruir a subsequência, precisamos manter toda a matriz de $dp$