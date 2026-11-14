# Grid Paths I

- **Link:** https://cses.fi/problemset/task/1638/
- **Tópicos:** DP, Combinatorics
- **Ideia central:** Dado um grid de tamanho $N \times N$, queremos contar o número de caminhos do canto superior esquerdo ao canto inferior direito, movendo-se apenas para a direita ou para baixo.
- **Insight:** Considerando uma $dp[j]$ como o número de caminhos para chegar à célula $(i, j)$, podemos atualizar a cada linha $i$ usando apenas a linha anterior $i-1$. A transição é dada por: $dp[j] = dp[j] + dp[j-1]$, onde $dp[j]$ representa o número de caminhos para a célula atual e $dp[j-1]$ representa o número de caminhos para a célula à esquerda. Perceba que $dp[i-1][j]$ é igual a $dp[j]$ antes da atualização, então podemos usar apenas um vetor unidimensional para armazenar os resultados. O caso inicial é $dp[0] = 1$, pois há apenas um caminho para a célula inicial.
- **Complexidade:** Tempo $O(N^2)$, Espaço $O(N)$
- **Pontos de atenção:** Lembre-se de zerar o valor da $dp[j]$ caso a célula $(i, j)$ seja um obstáculo.