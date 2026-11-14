# Coin Combinations I

- **Link:** https://cses.fi/problemset/task/1635
- **Tópicos:** DP, Combinatorics
- **Ideia central:** Dados dois inteiros positivos $N$ e $X$, e um array de $N$ valores de moedas distintas, determinar o número de maneiras de formar o valor $X$ usando as moedas disponíveis. Cada moeda pode ser usada infinitas vezes
- **Insight:** Utilizar programação dinâmica, na qual $dp[i]$ representa o número de maneiras de formar o valor $i$ usando as moedas disponíveis. Inicializar $dp[0] = 1$ e os outros valores como $0$. Iterar sobre os valores de $1$ a $X$, e para cada valor, iterar sobre as moedas disponíveis, atualizando $dp[i] = dp[i] + dp[i-c]$, onde $i$ é o valor atual e $c$ é o valor da moeda. O resultado final será armazenado em $dp[X]$
- **Complexidade:** Tempo $O(N\times{X})$, Espaço $O(X)$
- **Pontos de atenção:** Note que é essencial instanciar a $dp$ com $0s$ para caso não existam combinações possíveis