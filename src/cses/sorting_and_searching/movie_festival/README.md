# Movie Festival

- **Link:** https://cses.fi/problemset/task/1629/
- **Tópicos:** Greedy, Sorting
- **Ideia central:** Dados $n$ intervalos $[a_i, b_i]$, encontrar o tamanho máximo de um subconjunto de intervalos que não se sobrepõem (isto é, onde $a_j \ge b_i$ para intervalos consecutivos).
- **Insight:** É o problema clássico de *Interval Scheduling*. Ordenando os intervalos pelo início, ao percorrê-los mantemos o menor tempo de término (`end`) do último intervalo escolhido: se o intervalo atual começa após ou exatamente em `end` (`s >= end`), incrementamos a resposta e atualizamos `end = e`; caso contrário, atualizamos `end = min(end, e)`, o que equivale a trocar o intervalo anterior por um que termina mais cedo para liberar espaço para os próximos.
- **Complexidade:** Tempo $O(n \log n)$, Espaço $O(n)$
- **Pontos de atenção:** Nenhum.