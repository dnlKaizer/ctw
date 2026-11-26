# Digit Queries

- **Link:** https://cses.fi/problemset/task/2431/
- **Tópicos:** Math, Implementation
- **Ideia central:** Dada uma sequência infinita formada pela concatenação dos inteiros positivos (`123456789101112...`), responder a consultas que pedem o $k$-ésimo dígito (indexado em 1) dessa sequência.
- **Insight:** Os números com $d$ dígitos formam um bloco com $9 \cdot 10^{d-1}$ números, ocupando $9 \cdot 10^{d-1} \cdot d$ posições na sequência. Subtraindo o tamanho de cada bloco enquanto $k$ for maior que ele, descobrimos quantos dígitos $d$ tem o número alvo. Em seguida, o número exato é $\text{start} + \lfloor (k - 1) / d \rfloor$, e o dígito desejado está no índice $(k - 1) \bmod d$ desse número.
- **Complexidade:** Tempo $O(\log_{10} k)$ por consulta, Espaço $O(\log_{10} k)$ (para converter o número em string)
- **Pontos de atenção:** Usar `long long` para $k$, `count`, `start` e `num`, pois $k$ pode chegar a $10^{18}$. Além disso, usar `k - 1` para indexação baseada em 0 ao calcular tanto o deslocamento do número quanto o índice do dígito.