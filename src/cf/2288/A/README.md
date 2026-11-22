# 2288A - Youmu Konpaku and the Duel

- **Link:** https://codeforces.com/contest/2288/problem/A
- **Tópicos:** Brute Force, Math
- **Ideia central:** Dado um valor $x$ que representa a vida de um youkai, descobrir o menor número de movimentos necessários para derrotá-lo. Há dois movimentos possíveis: (1) Carregar: aumenta o ataque em 1, (2) Atacar: causa dano igual ao ataque atual no youkai, mas perde 1 de dano no processo
- **Insight:** Podemos carregar nos primeiros movimentos e atacar com tudo nos últimos. Então, podemos analisar para cada $1 \leq i \leq x$, o mínimo entre carregar até o movimento $i$ e atacar até acabar a vida do youkai ou aumentar o $i$
- **Complexidade:** Tempo $O(x \log{x})$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum