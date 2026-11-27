# Sum of Two Values

- **Link:** https://cses.fi/problemset/task/1640/
- **Tópicos:** Sorting, Two Pointers
- **Ideia central:** Dado um array de $n$ inteiros e um alvo $x$, encontrar duas posições distintas (indexadas em 1) cujos valores somam exatamente $x$, ou imprimir `IMPOSSIBLE` caso não existam.
- **Insight:** Ordenando uma cópia do array, podemos usar dois ponteiros nas extremidades ($l = 0$ e $r = n - 1$): se $a[l] + a[r] < x$, avançamos $l$; se $a[l] + a[r] > x$, recuamos $r$; se forem iguais, encontramos o par de valores e recuperamos seus índices originais percorrendo o array original (`aux`).
- **Complexidade:** Tempo $O(n \log n)$, Espaço $O(n)$
- **Pontos de atenção:** Ao recuperar os índices originais em `aux`, garantir que o mesmo índice não seja escolhido duas vezes quando $a[l] = a[r]$ (usando `if / else if` para `f` e `s`).