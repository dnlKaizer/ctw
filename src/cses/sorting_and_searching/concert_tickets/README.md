# Concert Tickets

- **Link:** https://cses.fi/problemset/task/1091/
- **Tópicos:** Data Structures, Binary Search, Greedy
- **Ideia central:** Dados $n$ ingressos com preços $h_i$ e $m$ clientes em ordem de chegada com orçamento máximo $t_j$, atribuir a cada cliente o ingresso disponível mais caro que não ultrapasse $t_j$ (ou `-1` se não houver nenhum), removendo o ingresso após a compra.
- **Insight:** Armazenando os preços dos ingressos em um `multiset<int>`, podemos consultar o maior preço $\le t_j$ em $O(\log n)$ usando `h.upper_bound(t[i])` e recuando o iterador em uma posição (`--it`). Se o iterador já estiver em `h.begin()`, não existe ingresso válido (`-1`); caso contrário, imprimimos `*it` e removemos apenas essa ocorrência com `h.erase(it)`.
- **Complexidade:** Tempo $O((n + m) \log n)$, Espaço $O(n + m)$
- **Pontos de atenção:** Usar `h.erase(it)` (passando o iterador) em vez de `h.erase(*it)` (que removeria todas as cópias daquele preço no `multiset`).