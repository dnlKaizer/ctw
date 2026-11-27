# Maximum Subarray Sum

- **Link:** https://cses.fi/problemset/task/1643/
- **Tópicos:** Prefix Sums, Greedy, DP
- **Ideia central:** Dado um array de $n$ inteiros (podendo conter valores negativos), encontrar a maior soma possível de um subarray contíguo não vazio.
- **Insight:** A soma de qualquer subarray $[l, r]$ é dada por $\text{pref}[r] - \text{pref}[l-1]$. Percorrendo os prefixos de trás para frente ($i = n-1$ até $0$), mantemos o maior prefixo visto à direita (`big`) e atualizamos a resposta com $\max(\text{ans}, \text{big} - \text{pref}[i])$, garantindo que o subarray escolhido seja sempre não vazio.
- **Complexidade:** Tempo $O(n)$, Espaço $O(n)$
- **Pontos de atenção:** Usar `long long` para as somas de prefixo e a resposta, pois os valores podem chegar a $\pm 10^9$ e a soma acumulada ultrapassa o limite de `int`. Como o subarray deve ser não vazio, `ans` deve ser inicializado com um valor suficientemente pequeno (negativo) em vez de `0`.