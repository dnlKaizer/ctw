# 2278A - Fraction and Six

- **Link:** https://codeforces.com/contest/2278/problem/A
- **Tópicos:** Implementation, Sorting, Strings
- **Ideia central:** Dadas duas strings $a$ e $b$, as quais são respectivamente um numerador e um denominador, descobrir se é possível simplificar a fração resultante para que o resultado seja $six$ 
- **Insight:** A string $a$ deve conter todos os caracteres da string $b$ mais os caracteres $s$, $i$ e $x$. Então, pode-se adicionar a string $six$ ao final da string $b$, ordenar ambas as strings e comparar se são iguais
- **Complexidade:** Tempo $O(N\log{N} + M\log{M})$, Espaço $O(N+M)$
- **Pontos de atenção:** Nenhum