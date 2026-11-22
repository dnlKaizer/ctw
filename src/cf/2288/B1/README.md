# 2288B1 - Yukari Yakumo and the Boundary Stones (Easy Version)

- **Link:** https://codeforces.com/contest/2288/problem/B1
- **Tópicos:** Greedy
- **Ideia central:** Dados dois valores $x$ e $y$, que representam respectivamente a quantidade de pedras de poder $1$ e $2$, determinar o número máximo de pedras a partir de uma lista de operações determinadas por uma string de tamanho $M$. (1) Se $s_i = +$, pode-se aumentar o poder de uma pedra de $1$ para $2$ ou, se já existir alguma pedra de poder $2$, adicionar uma nova pedra de poder $2$. (2) Se $s_i = -$, pode-se reduzir o poder de uma pedra de $2$ para $1$, se existir, ou remover uma pedra de poder $1$
- **Insight:** Analisando as operações, percebe-se que vale muito a pena adicionar uma nova pedra de poder $2$ sempre e, consequentemente, vale a pena manter pelo menos uma pedra de poder $2$. Então, é possível usar uma abordagem gulosa que testa todas as operações tomando as melhores decisões e, no meio do processo, ver qual delas maximiza o número de pedras. Se $s_i = +$: (1) adiciona uma pedra de poder $2$ se uma dessa existir ou (2) aumenta o poder de uma pedra de $1$ para $2$. Se $s_i = -$: (1) se $y > 1$ ou $x = 0$, reduz o poder de uma pedra de poder $2$ para $1$, (2) caso contrário, remove uma pedra de poder $1$
- **Complexidade:** Tempo $O(M)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum