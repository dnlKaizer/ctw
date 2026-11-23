# C - Clumsy Courier

- **Link:** https://moj.naquadah.com.br/treino/problema/?id=icpc-latam-2026%23c-second_guessing
- **Tópicos:** Combinatorics, DP, Math
- **Ideia central:** Dados os inteiros positivos $R$, $C$, $U$ e $L$, que indicam o número de linhas e colunas do grid e a quantidade obrigatória de movimentos para cima e para a esquerda, determinar o número de sequências válidas de movimentos de $(1, 1)$ até $(R, C)$ sem sair dos limites do grid, módulo $998244353$.
- **Insight:**

Os movimentos verticais (`U`, `D`) e horizontais (`L`, `R`) são completamente independentes entre si. Para sair de $(1, 1)$ e chegar em $(R, C)$ fazendo exatamente $U$ passos para cima e $L$ passos para a esquerda, são necessários $V = R - 1 + 2U$ passos verticais e $H = C - 1 + 2L$ passos horizontais no total.

Calculamos separadamente o número de sequências 1D válidas na vertical ($n$) e na horizontal ($m$) usando uma DP simples onde `dp[x][i]` representa o número de formas de chegar na posição $i$ usando $x$ passos reversos (`U` ou `L`), com transição:
$$\text{dp}[x][i] = \text{dp}[x][i-1] + \text{dp}[x-1][i+1]$$
Utilizando *rolling array* *in-place*, atualizamos `dp[i] = (dp[i-1] + dp[i+1]) % MOD`, pois `dp[i-1]` já corresponde à camada $x$ e `dp[i+1]` ainda guarda o valor da camada $x-1$.

Com as duas sequências independentes contadas, basta intercalá-las em uma lista final de tamanho $V + H$ mantendo a ordem relativa de cada eixo. Isso equivale a escolher quais $V$ posições receberão os movimentos verticais: $\binom{V+H}{V}$. Pelo princípio multiplicativo, a resposta é:
$$\text{ans} = n \cdot m \cdot \binom{V+H}{V} \pmod{998244353}$$

- **Complexidade:** Tempo: $O(R \cdot U + C \cdot L + \log(\text{MOD}))$, Espaço: $O(R + C + U + L)$
- **Pontos de atenção:** Usar `1LL` em todas as multiplicações antes de aplicar `% MOD` ou usar `long long` em tudo