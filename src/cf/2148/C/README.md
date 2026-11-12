# 2148C - Pacer

- **Link:** https://codeforces.com/contest/2148/problem/C
- **Tópicos:** Greedy, Math
- **Ideia central:** Contar o máximo de vezes que FJ consegue correr de uma ponta do ginásio a outra, dados os tempos em que ele deve estar em um lado do ginásio em momentos específicos.
- **Insight:** Analisando os intervalos de tempo, se $t_i - t_{i-1}$ é ímpar, então ele trocaria a posição atual, e se for par, ele permaneceria na mesma posição. Então, para cada intervalo, basta analisar se ele consegue fazer o máximo de corridas possíveis ($t_i - t_{i-1}$) e somar na variável de resposta, se não, ele pode deixar de fazer uma corrida no pior caso ($t_i - t_{i-1} - 1$).
- **Complexidade:** Tempo $O(N)$, Espaço $O(1)$
- **Pontos de atenção:** Os momentos passados pelo problema não necessariamente cobrem todo o intervalo $m$, logo, deve-se lembrar de adicionar o intervalo final $m - t_n$ na resposta, caso seja possível fazer corridas nesse intervalo.