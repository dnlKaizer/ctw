# 2185A - Perfect Root

- **Link:** https://codeforces.com/problemset/problem/2185/A
- **Tópicos:** Math, Constructive Algorithms
- **Ideia central:** Construir uma sequência de $n$ números inteiros distintos tais que cada $a_i$ possa ser escrito como $\sqrt{k}$ para algum $k$ inteiro positivo
- **Insight:** Inverter a condição: $x = \sqrt{y} \iff y = x^2$. Como para qualquer inteiro positivo $x$ o valor $x^2$ é sempre inteiro, todo número natural é uma "raiz perfeita". Basta imprimir os primeiros $n$ inteiros positivos ($1, 2, \dots, n$)
- **Complexidade:** Tempo $O(n)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum