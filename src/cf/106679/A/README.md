# 106679A - High Frequency

- **Link:** https://codeforces.com/gym/106679/problem/A
- **Tópicos:** Math, Queries
- **Ideia central:** Dados dois vetores de inteiros positivos $C$ e $V$, responder $Q$ queries com base na fórmula:
$$I_n = \frac{C_n - V_n}{C_n + V_n}, \quad \text{where } C_n = \sum_{i=1}^{n} c_i \text{ and } V_n = \sum_{i=1}^{n} v_i.$$
O valor de $I_n$ é interpretado como: $(1)$ se $I_n > 0$, então o sinal é de COMPRA, $(2)$ se $I_n < 0$, então o sinal é de VENDA, $(3)$ se $I_n = 0$, então o sinal é de NEUTRO.

- **Insight:** Pela definição de $C_n$ e $V_n$, eles são a soma dos prefixos dos vetores $C$ e $V$. Além disso, não precisamos fazer a divisão por $C_n + V_n$, pois queremos analisar apenas o sinal do resultado. Então, podemos armazenar em um vetor um sinal 1, 0 ou -1 para os respectivos casos de $C_n > V_n$, $C_n = V_n$ e $C_n < V_n$, permitindo cada consulta em tempo constante
- **Complexidade:** Tempo $O(N + Q)$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum