# Book Shop

- **Link:** https://cses.fi/problemset/task/1158/
- **Tópicos:** DP
- **Ideia central:** Dado um orçamente de valor $X$ e $N$ livros, cada um com preço $h_i$ e $s_i$ páginas, determinar o número máximo de páginas que podem ser compradas sem ultrapassar o orçamento
- **Insight:** Considerando uma $dp[i][j]$ como o número máximo de páginas que podem ser compradas com orçamento $j$ usando os primeiros $i$ livros, podemos atualizar a cada livro $i$ usando apenas a linha anterior $i-1$. A transição é dada por: $dp[i][j] = max(dp[i-1][j], dp[i-1][j-h_i] + s_i)$, onde $dp[i-1][j]$ representa o número máximo de páginas sem comprar o livro atual e $dp[i-1][j-h_i] + s_i$ representa o número máximo de páginas comprando o livro atual. O caso inicial é $dp[0][j] = 0$, pois não há livros para comprar. Como o estado atual $i$ depende apenas do estado anterior $i-1$, podemos otimizar o espaço usando apenas um vetor unidimensional $dp[j]$ e atualizando-o de trás para frente.
- **Complexidade:** Tempo $O(N \times X)$, Espaço $O(X)$
- **Pontos de atenção:** Lembrar de atualizar o vetor $dp[j]$ de trás para frente para evitar sobrescrever os valores que ainda serão usados na transição.