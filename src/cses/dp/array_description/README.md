# Array Description

- **Link:** https://cses.fi/problemset/task/1746/
- **Tópicos:** DP, Combinatorics
- **Ideia central:** Dado um valor $M$ e uma array (com elementos fixos e desconhecidos) de $N$ elementos que variam de $1$ a $M$, contar o número de arrays válidas que satisfazem as condições dadas (diferença entre elementos adjacentes é no máximo 1)
- **Insight:** Criar uma $dp[i][v]$ que representa o número de arrays válidas de tamanho $i$ que terminam com o valor $v$. A transição é feita somando os valores de $dp[i-1][v-1]$, $dp[i-1][v]$ e $dp[i-1][v+1]$, considerando os limites do array e os elementos fixos. Como o estado atual $i$ depende apenas do estado anterior $i-1$, podemos otimizar o espaço usando apenas dois arrays de tamanho $M$ (mas eu não fiz), o que reduz o espaço para $O(M)$
- **Complexidade:** Tempo $O(N \times M)$, Espaço $O(N \times M)$
- **Pontos de atenção:** Lembrar de inicializar o caso base como $1$ para todo $v$ e, caso o primeiro elemento seja fixo, inicializar apenas o valor correspondente a ele. Além disso, é importante considerar os limites do array ao acessar $dp[i-1][v-1]$ e $dp[i-1][v+1]$ para evitar acessar índices inválidos, o que pode ser resolvido usando um array de tamanho $M+2$