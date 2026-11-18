# 106679K - Contingency

- **Link:** https://codeforces.com/gym/106679/problem/K
- **Tópicos:** Greedy
- **Ideia central:** Dados dois arrays de inteiros positivos: $c$ (capacidade) e $k$ (meta) de tamanho $N$, onde cada índice representa um componente diferente, encontrar o número mínimo de componentes que vão passar na esteira no pior caso. Em outras palavras, os componentes podem passar na esteira em ordens e quantidades diferentes, logo, se considerarmos a pior ordem possível, qual o número mínimo de componentes que passam pela esteira nesse caso.
- **Insight:** O pior caso sempre envolve passar todos os componentes de todas as capacidades antes do último. Para verificar qual deles é o pior, podemos fazer a soma $s$ de todas as capacidades e, para cada componente $i$, fazemos $ans = \min(ans, s - c_i + k_i)$
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Lembre-se de verificar se existe algum $k_i < c_i$, o que se configura como um caso inválido e a resposta será $-1$