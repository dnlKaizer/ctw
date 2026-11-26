# Mex Grid Construction

- **Link:** https://cses.fi/problemset/task/3419/
- **Tópicos:** Constructive Algorithms, Bitwise, Math
- **Ideia central:** Dado um inteiro $n$, construir uma matriz $n \times n$ preenchida de forma que cada célula $(i, j)$ contenha o menor inteiro não negativo (MEX) que não aparece à esquerda na mesma linha nem acima na mesma coluna.
- **Insight:** O valor $\text{mex}(\{a_{i, 0}, \dots, a_{i, j-1}\} \cup \{a_{0, j}, \dots, a_{i-1, j}\})$ coincide exatamente com a soma de Nim (XOR bit a bit) $i \oplus j$. Isso ocorre porque, para qualquer $x < i \oplus j$, o bit mais significativo em que $x$ e $i \oplus j$ diferem pertence a $i$ ou a $j$, garantindo que $x$ já tenha aparecido antes na mesma linha ou na mesma coluna, e nenhum elemento anterior pode ser igual a $i \oplus j$ pois o XOR é injetivo em cada linha e coluna.
- **Complexidade:** Tempo $O(n^2)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum.