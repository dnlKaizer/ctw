# 2200B - Deletion Sort

- **Link:** https://codeforces.com/contest/2200/problem/B
- **Tópicos:** Greedy
- **Ideia central:** Dado um array $a$ de tamanho $N$, simular o seguinte jogo: (1) se o array é crescente, encerra, (2) se não, pode deletar qualquer elemento do array. Encontrar o valor mínimo de elementos do array ao final do jogo. 
- **Insight:** Se o array for crescente, a resposta é $N$, pois não é possível alterá-lo. Caso contrário, a resposta sempre será $1$, pois, se acharmos dois elementos tais que $a_i > a_{i+1}$, podemos simplesmente deletar todos os outros elementos até sobrar apenas esses e dois e, então, deletar qualquer um deles. Logo, podemos manter uma variável única que armazena o valor do elemento anterior, compará-la com o atual e depois atualizá-la
- **Complexidade:** Tempo $O(N)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum