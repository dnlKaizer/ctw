# 2266E - Prime Destruction

- **Link:** https://codeforces.com/contest/2266/problem/E
- **Tópicos:** DP, Number Theory, Math
- **Ideia central:** Dado um multiset $a$ que contém $N$ inteiros positivos, determinar o valor de $f(k)$. Considere a operação que pode ser realizada quantas vezes forem necessárias: escolher um inteiro $x > k$ e um divisor primo $p$ de $x$, remover uma ocorrência de $x$ e substituir por $p$ ocorrências de $\frac{x}{p}$. Dado um valor $k$, tome $f(k)$ como o mínimo de operações possíveis até o multiset ter somente elementos menores ou iguais a $k$
- **Insight:** Considerando uma $dp[x]$ que representa o menor custo de transformar o número $x$ em um número menor que $k$, podemos definir que:
$$
dp(x) = \begin{cases} 
  0 & \text{se } x \leq k \\
  \min_{\substack{p \mid x \\ p \text{ primo}}} \left( 1 + p \times dp[x / p] \right) & \text{se } x > k 
\end{cases}
$$
ou seja, iterar sobre todos os divisores primos de $x$ e verificar qual vai resultar em um menor número de operações. Então, podemos pré-computar todos os valores da $dp$ até $N$ e somar o resultado de $dp[a_i]$ para todo $i$ do multiset
- **Complexidade:** Tempo $O(N \sqrt{N})$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum