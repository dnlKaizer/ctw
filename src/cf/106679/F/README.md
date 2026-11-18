# 106679F - Flower

- **Link:** https://codeforces.com/gym/106679/problem/F
- **Tópicos:** Math
- **Ideia central:** Dado um inteiro $N$ e as seguintes regras: (1) a flor só opera com valores inteiros, (2) oferendas maiores resultam estritamente a em retornos maiores, (3) se você pegar todas as sementes devolvidas pela flor após a oferenda de $K$ sementes e depositá-las novamente na flor, ela vai retornar exatamente $3K$ sementes
- **Insight:** Queremos determinar o $f(x)$, onde $x$ é a oferenda e $f(x)$ o retorno. Ao colocar os valores iniciais no papel, percebe-se um padrão que "força" alguns retornos por causa da combinação das 3 regras, o que torna possível determinar:
$$f(x) = \begin{cases} 
v + x, & \text{se } x \le 2v \\
3(x - v), & \text{se } x > 2v
\end{cases} \quad \text{onde } v = 3^{\lfloor \log_3 x \rfloor}$$
- **Complexidade:** Tempo $O(\log_3{N})$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum