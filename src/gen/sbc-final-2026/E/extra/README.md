Ficou bem enxuta mesmo — e o truque de aproveitar $P_i < i$ para eliminar a DFS deixa o código super limpo.

---

# E - Escape Plan (Solução Extra — Bottom-Up)

* **Link:** https://moj.naquadah.com.br/treino/problema/?id=icpc-latam-2026%23e-escape_plan
* **Tópicos:** Graphs, Greedy
* **Ideia central:** Dada uma árvore direcionada enraizada no nó $1$ (com $P_i < i$), atribuir a cada folha $s$ exatamente uma aresta de retorno para um ancestral $e(s)$, de modo que cada nó receba no máximo uma aresta de retorno e todas as folhas consigam alcançar a raiz $1$.
* **Insight:** Resolver a árvore de baixo para cima (*bottom-up*), propagando em cada nó $u$ a lista de folhas ainda sem túnel de sua subárvore. Para não formar ciclos isolados, toda subárvore $u \neq 1$ precisa deixar pelo menos $1$ folha escapar para cima; logo, sempre que a lista de $u$ tiver $2$ ou mais folhas, é guloso e ótimo ligar uma delas ao próprio $u$ e subir o restante. Como o enunciado garante $P_i < i$, iterar de $N$ até $1$ já fornece a ordem topológica reversa (dispensando DFS), e `std::list::splice` une as listas dos filhos em $O(1)$.
* **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
* **Pontos de atenção:** (1) Usar `std::list::splice` evita cópias lineares; concatenar `std::vector` diretamente causaria TLE ($O(N^2)$) sem *small-to-large*. (2) Ao chegar na raiz, deve sobrar **exatamente** $1$ folha (`curr.size() == 1`). Se sobrar mais de uma, a resposta é `*` (isso já cobre automaticamente o caso de o nó $1$ ter mais de $1$ filho)