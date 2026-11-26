# Grid Coloring I

- **Link:** https://cses.fi/problemset/task/3311/
- **Tópicos:** Constructive Algorithms, Greedy
- **Ideia central:** Dada uma matriz $n \times m$ preenchida com caracteres de `{'A', 'B', 'C', 'D'}`, substituí-los por novos caracteres do mesmo conjunto de modo que cada célula mude de valor e nenhuma célula adjacente (ortogonalmente) tenha o mesmo caractere.
- **Insight:** Percorrendo a matriz em ordem de leitura (cima para baixo, esquerda para direita), cada célula $(i, j)$ precisa apenas ser diferente de no máximo 3 valores: seu valor original, o vizinho de cima $(i-1, j)$ e o vizinho da esquerda $(i, j-1)$. Como há 4 letras disponíveis (`A`, `B`, `C`, `D`), pelo princípio da casa dos pombos sempre existe ao menos uma letra válida, permitindo uma escolha gulosa imediata.
- **Complexidade:** Tempo $O(n \cdot m)$, Espaço $O(n \cdot m)$
- **Pontos de atenção:** Nenhum.