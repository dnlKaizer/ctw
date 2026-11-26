# Chessboard and Queens

- **Link:** https://cses.fi/problemset/task/1624/
- **Tópicos:** Backtracking, Complete Search
- **Ideia central:** Contar de quantas maneiras é possível posicionar 8 rainhas em um tabuleiro $8 \times 8$ sem que nenhuma ataque outra (mesma linha, coluna ou diagonal), respeitando células bloqueadas (`*`) onde não é permitido colocar rainhas.
- **Insight:** Como deve haver exatamente uma rainha por linha, podemos modelar a posição como uma permutação $v$ de $\{0, \dots, 7\}$, onde $v[i]$ é a coluna da rainha na linha $i$. Usando backtracking linha a linha, já garantimos linhas e colunas distintas (além de evitar células bloqueadas), restando apenas verificar ao final se nenhuma dupla $(i, j)$ está na mesma diagonal ($|v[j] - v[i]| = j - i$).
- **Complexidade:** Tempo $O(N! \cdot N^2)$, Espaço $O(N^2)$
- **Pontos de atenção:** Nenhum.