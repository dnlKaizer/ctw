# Knight Moves Grid

- **Link:** https://cses.fi/problemset/task/3217/
- **Tópicos:** BFS, Shortest Path, Graphs
- **Ideia central:** Dado um tabuleiro $n \times n$, calcular para cada célula $(i, j)$ o número mínimo de movimentos que um cavalo de xadrez precisa para ir até a célula $(0, 0)$ (ou equivalentemente, de $(0, 0)$ até $(i, j)$).
- **Insight:** Como cada movimento do cavalo tem custo unitário (1), o tabuleiro pode ser modelado como um grafo não ponderado onde cada célula tem até 8 vizinhos válidos em "L". Uma busca em largura (BFS) a partir de $(0, 0)$ encontra a menor distância para todas as células em uma única passada.
- **Complexidade:** Tempo $O(n^2)$, Espaço $O(n^2)$
- **Pontos de atenção:** Nenhum.