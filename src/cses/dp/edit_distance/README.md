# edit_distance - Edit Distance

- **Link:** https://cses.fi/problemset/task/1639/
- **Tópicos:** DP, Strings
- **Ideia central:** Dadas duas strings $a$ e $b$, determinar o número mínimo de operações (inserção, deleção ou substituição: custo 1) necessárias para transformar $a$ em $b$
- **Insight:** Criar uma $dp[i][j]$ que representa o número mínimo de operações para transformar os primeiros $i$ caracteres de $a$ nos primeiros $j$ caracteres de $b$. A transição é dada por: (1) Se $a[i-1] == b[j-1]$, então $dp[i][j] = dp[i-1][j-1]$, (2) Caso contrário, $dp[i][j] = 1 + \min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])$. É de suma importância incializar os casos base, que são quando uma das strings é vazia, ou seja, $dp[0][j] = j$ e $dp[i][0] = i$
- **Complexidade:** Tempo $O(N \times M)$, Espaço $O(N \times M)$
- **Pontos de atenção:** É possível otimizar o espaço para $O(\min(N, M))$ utilizando apenas duas linhas da matriz $dp$ por vez, já que a transição depende apenas da linha anterior e da linha atual