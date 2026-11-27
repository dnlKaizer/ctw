# Ferris Wheel

- **Link:** https://cses.fi/problemset/task/1090/
- **Tópicos:** Sorting, Two Pointers, Greedy
- **Ideia central:** Dados $n$ pesos $p_i$ e uma capacidade máxima $x$, particionar os $n$ elementos no menor número possível de grupos de tamanho no máximo 2 cuja soma não ultrapasse $x$.
- **Insight:** Ordenando os pesos, a escolha gulosa ótima é tentar parear o elemento mais pesado restante ($p_j$) com o mais leve restante ($p_i$): se $p_i + p_j \le x$, ambos vão juntos (avança `i` e recua `j`); caso contrário, o mais pesado vai sozinho (recua apenas `j`).
- **Complexidade:** Tempo $O(n \log n)$, Espaço $O(n)$
- **Pontos de atenção:** Nenhum.