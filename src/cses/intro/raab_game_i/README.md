# Raab Game I

- **Link:** https://cses.fi/problemset/task/3399/
- **Tópicos:** Constructive Algorithms, Math
- **Ideia central:** Dados $n, a, b$, construir duas permutações de $\{1, \dots, n\}$ representando as cartas jogadas por dois jogadores em $n$ rodadas, de modo que o primeiro jogador vença exatamente $a$ rodadas, o segundo vença exatamente $b$ rodadas e as $n - a - b$ restantes terminem em empate (ou determinar que é impossível).
- **Insight:** Para que seja possível, é necessário que $a + b \le n$ e que, se $a + b > 0$, ambos $a > 0$ e $b > 0$ (pois a soma das cartas dos dois jogadores é igual, então um jogador não pode vencer todas as rodadas não empatadas). Fixando a sequência do primeiro jogador como $1, 2, \dots, n$, basta fazer os últimos $n - (a + b)$ elementos empatarem ($a+b+1, \dots, n$) e rotacionar ciclicamente os primeiros $a + b$ elementos: o segundo jogador joga $a+1, \dots, a+b$ (vencendo $b$ rodadas contra $1, \dots, b$) seguido de $1, \dots, a$ (perdendo $a$ rodadas contra $b+1, \dots, a+b$).
- **Complexidade:** Tempo $O(n)$, Espaço $O(1)$
- **Pontos de atenção:** Caso de borda $a = 0$ e $b = 0$ é válido (todas as rodadas empatam), enquanto $a + b > 0$ com $a = 0$ ou $b = 0$ é impossível.