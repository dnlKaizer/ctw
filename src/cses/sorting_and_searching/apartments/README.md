# Apartments

- **Link:** https://cses.fi/problemset/task/1084/
- **Tópicos:** Sorting, Two Pointers, Greedy
- **Ideia central:** Dados $n$ desejos de tamanho $a_i$, $m$ apartamentos de tamanho $b_j$ e uma tolerância $k$, maximizar o número de pareamentos $(i, j)$ distintos tais que $|a_i - b_j| \le k$.
- **Insight:** Ordenando ambos os vetores $a$ e $b$, podemos usar dois ponteiros ($i$ para candidatos e $j$ para apartamentos) de forma gulosa: se $b_j \in [a_i - k, a_i + k]$, pareamos ambos e avançamos $i$ e $j$; se $b_j > a_i + k$ (ou seja, $a_i - k < b_j$ fora do intervalo), o candidato $i$ não serve para nenhum apartamento seguinte, então avançamos $i$; caso contrário, o apartamento $j$ é pequeno demais, então avançamos $j$.
- **Complexidade:** Tempo $O(n \log n + m \log m)$, Espaço $O(n + m)$
- **Pontos de atenção:** Nenhum.