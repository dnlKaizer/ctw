# 2285B - Diverse String

- **Link:** https://codeforces.com/contest/2285/problem/B
- **Tópicos:** Combinatorics, Math, Strings
- **Ideia central:** Dada uma string binária $s$, determinar o número máximo de strings distintas que podem ser formadas com a operação: escolher um valor de $0 \leq x \leq 1$ e um intervalo $1 \leq l \leq r \leq N$ e setar o valor $s_i = x$, para todo $l \leq i \leq r$. Você pode inverter até $k$ bits da string incial para aumentar o número de combinações possíveis
- **Insight:** A ordem dos bits em si não importa, mas sim a quantidade e seu valor na string. Analisando manualmente, percebe-se que uma sequência de $m$ zeros (ou uns) permite criar $m + m-1 + m-2 + ... + 1$ combinações diferentes além da inicial, ou seja, $\frac{(m+1) \cdot m}{2}$. Logo, a melhor opção é inverter o máximo de bits do número que tem menos ocorrências na string. Então, calcular o valor final, considerando $x$ e $y$ o número uns e zeros após a inversão, temos que a resposta é $\frac{(x+1) \cdot x}{2} + \frac{(y+1) \cdot y}{2} + 1$
- **Complexidade:** Tempo $O(N)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum