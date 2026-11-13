# L - Lookout Towers

- **Link:** https://moj.naquadah.com.br/treino/problema/?id=icpc-latam-2026%23l-watchtowers
- **Tópicos:** Graphs
- **Ideia central:** Dada uma árvore de $N$ nós e $N-1$ arestas e um conjunto $K$ de torres de observação, determinar a menor distância entre duas torres de observação e quantos pares de nós satisfazem essa distância mínima.
- **Insight:** Usar uma BFS Multisource até o ponto médio entre duas torres. Se a distância for ímpar, basta contar quantos nós estão a uma distância $\lfloor d/2 \rfloor$ de cada torre. Se a distância for par, é necessário contar quantas torres alcançam no nó central e fazer uma combinação de quantos pares de torres podem ser formados a partir desses nós centrais, ou seja, $\binom{c}{2} = \frac{c \times (c-1)}{2}$, onde $c$ é o número de torres que alcançam o nó central.
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Usar $long long$ para evitar overflow ao calcular o número de pares de torres.