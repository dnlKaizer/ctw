# 2290D - Letters Down

- **Link:** https://codeforces.com/contest/2290/problem/D
- **Tópicos:** String, Implementation
- **Ideia central:** Achar o mínimo de operações para transformar a string em um palíndromo, considerando que cada operação consiste em mudar um caractere para outro 1 unidade abaixo.
- **Insight:** Vale mais a pena fazer a operação com as letras maiores primeiro. Exemplo: para transformar $abcz$ em $abba$ operando em $c$ primeiro, serão gastas $26$ operações, enquanto operando em $z$ primeiro, serão gastas apenas $25$. Levando isso em consideração, podemos perceber que ao transformar uma letra em outra menor, todas aquelas entre elas também serão transformadas. Logo, basta ter um vetor auxiliar booleano que indica se uma letra foi transformada ou não. Então, começamos a verificar a string usando um ponteiro no início e outro no final, e a cada iteração, verificamos se os caracteres são diferentes. Assim, basta marcar todas as letras entre eles como transformadas, incluindo o próprio caractere maior, e contar quantos $true$ haverão no vetor
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum