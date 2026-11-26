# Distinct Numbers

- **Link:** https://cses.fi/problemset/task/1621/
- **Tópicos:** Sorting
- **Ideia central:** Dado um array de $n$ inteiros positivos, determinar quantos valores distintos existem nele.
- **Insight:** Após ordenar o array, elementos iguais ficam adjacentes. Basta percorrê-lo linearmente contando quantas vezes o elemento atual difere do anterior (`v[i] != bef`).
- **Complexidade:** Tempo $O(n \log n)$, Espaço $O(n)$
- **Pontos de atenção:** Inicializar `bef = -1` funciona pois os valores de entrada são positivos ($x_i \ge 1$). Evitar `unordered_set` sem custom hash no CSES/Codeforces devido a possíveis colisões (anti-hash tests).