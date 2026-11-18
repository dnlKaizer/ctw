# 106679C - Exchange Rate

- **Link:** https://codeforces.com/gym/106679/problem/C
- **Tópicos:** Math
- **Ideia central:** Dados 3 inteiros positivos $T$, $C$ e $K$, representando, respectivamente, a taxa de câmbio, o preço do produto no Brasil e o
preço do produto no exterior, determinar qual menor valor em reais que pode ser pago
- **Insight:** Para converter $K$ para reais, basta multiplicar pelo câmbio $T$. Logo, é preciso somente fazer $\min(C, K \times T)$
- **Complexidade:** Tempo $O(1)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum