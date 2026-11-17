# Minimal Grid Path

- **Link:** https://cses.fi/problemset/task/3359/
- **Tópicos:** BFS, Strings
- **Ideia central:** Dada uma matriz $N \mult N$ de letras de $A-Z$, encontrar o caminho com a string lexicograficamente mínima.
- **Insight:** Fazer uma BFS que caminha na diagonal da matriz. Analisar o melhor caractere em questão e adicinonar os vizinhos que possuem esse caracter na fila.
- **Complexidade:** Tempo $O(N^2)$, Espaço $O(N^2)$
- **Pontos de atenção:** Nenhum