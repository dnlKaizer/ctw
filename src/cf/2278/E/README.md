# 2278E - Swap and Negate

- **Link:** https://codeforces.com/contest/2278/problem/E
- **Tópicos:** Constructive Algorithms, Sorting, Greedy
- **Ideia central:** Dado um array de $N$ inteiros e uma operação que permite trocar elementos adjacentes invertendo o sinal deles, determinar a maior soma possível do array após fazer as operações de forma ótima
- **Insight:** Analisando as operações, percebe-se que os números podem ser rearranjados para qualquer índice do array, mas, dependendo da sua paridade, o sinal é invertido ou não. Então, a solução envolve separar números de índice par em um array $a$ e ímpar em um array $b$ e ordená-los. Feito isso, deve-se percorrer o array e analisar o índice atual em ambos: (1) se os dois forem negativos, a solução é trocar a paridade dos dois, (2) se os dois forem positivos, não faz sentido trocar a paridade deles, (3) se um for positivo e outro negativo, analisar o valor absoluto de cada um e, se o maior for o negativo, fazer a troca de paridade. Se $-(a_i + b_i) > a_i + b_i$ então deve ser feita uma troca, pois essa equação verifica os três casos acima. Como os arrays estão ordenados, é possível percorrer ambos de forma linear e decidir a melhor ação a cada passo
- **Complexidade:** Tempo $O(N\log{N})$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum