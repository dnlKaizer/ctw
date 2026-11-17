# Rectangle Cutting

- **Link:** https://cses.fi/problemset/task/1744/
- **Tópicos:** DP, Math
- **Ideia central:** Dado um retângulo de dimensões $a \times b$, determinar o número mínimo de cortes necessários para dividi-lo em quadrados
- **Insight:** Usar uma $dp[i][j]$ que representa o número mínimo de cortes necessários para um retângulo de dimensões $i \times j$. Se $i$ for divisor de $j$, então $dp[i][j] = j / i$, caso contrário, precisamos avaliar todos os outros cortes possíveis. Um retângulo de dimensões $i \times j$ terá exatamente o mesmo número mínimo de cortes que um retângulo de dimensões $j \times i$, logo, essa simetria pode reduzir o número de cortes possíveis pela metade. Então, incializando $dp[i][j]$ com um número suficientemente grande e para um valor $1 \leq k \leq \lfloor \frac{i}{2} \rfloor$, temos que $dp[i][j] = \min(dp[i][j], dp[i-k][j] + dp[k][j])$. Além disso, para $1 \leq k \leq \lfloor \frac{j}{2} \rfloor$, também calculamos $dp[i][j] = \min(dp[i][j], dp[i][j-k] + dp[i][k])$
- **Complexidade:** Tempo $O(a \times b \times (a+b))$, Espaço $O(a \times b)$
- **Pontos de atenção:** Inicializar a $dp$ com o valor máximo possível, independente dos valores de $a$ e $b$, é uma boa prática para garantir confiabilidade e velocidade para o código