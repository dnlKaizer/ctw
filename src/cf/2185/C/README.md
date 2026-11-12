# 2185C - Shifted MEX

- **Link:** https://codeforces.com/contest/2185/problem/C
- **Tópicos:** Implementation, Sortings
- **Ideia central:** Encontrar o maior $MEX$ de um array, podendo incrementar/decrementar todos os elementos do array em $k$ unidades
- **Insight:** Encontrar a maior subsequência crescente contígua no array, já que podemos "indexar" o primeiro elemento da sequência zerando ele com o $k$. Para fazer isso, basta ordenar o array e contar a quantidade máxima de números consecutivos
- **Complexidade:** Tempo $O(N)$, Espaço auxiliar $O(N)$
- **Pontos de atenção:** Verificar cegamente se o elemento $a_i = a_{i-1} + 1$ não é suficiente, pois pode haver elementos repetidos. A solução correta é verificar se o elemento atual é maior que o anterior e se a diferença entre eles é menor ou igual a $k$.