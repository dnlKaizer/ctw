# Apple Division

- **Link:** https://cses.fi/problemset/task/1623/
- **Tópicos:** Complete Search, Bitmask
- **Ideia central:** Dado um conjunto de $n$ pesos, particioná-lo em dois subconjuntos disjuntos de forma a minimizar a diferença absoluta entre a soma dos elementos de cada subconjunto.
- **Insight:** Como $n$ é pequeno ($n \le 20$), há no máximo $2^n$ partições possíveis. Podemos usar uma bitmask de $n$ bits para representar todas as combinações, onde o $j$-ésimo bit indica a qual dos dois grupos o $j$-ésimo elemento pertence, e tomar o mínimo de $|S_0 - S_1|$.
- **Complexidade:** Tempo $O(n \cdot 2^n)$, Espaço $O(n)$
- **Pontos de atenção:** Usar `long long` para armazenar as somas dos grupos e a resposta, pois os pesos podem chegar a $10^9$ e a soma total ultrapassa o limite de `int` de 32 bits.