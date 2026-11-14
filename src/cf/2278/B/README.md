# 2278B - Stakes and Shapes

- **Link:** https://codeforces.com/contest/2278/problem/B
- **Tópicos:** Constructive Algorithms, Greedy
- **Ideia central:** Dados três inteiros positivos $x$, $y$ e $z$, os quais representam respectivamente a quantidade de Pedra, Tesoura e Papel jogadas pelo jogador $A$, determinar qual a ordem de jogadas que garante ao jogador $B$ um número de vitórias maior ou igual ao número de vitórias do jogador $A$.
- **Insight:** Se escolhermos uma única jogada e repetirmos para todas as rodadas, é possível chegar a um número de vitórias maior ou igual ao do jogador $A$. Por exemplo, se Pedra for o maior valor (ou empatar com qualquer outro), escolhemos jogar sempre Papel, pois isso garante $x$ vitórias e $z$ empates. Como $x \leq y \leq z$, temos que $x + z \geq y$, garantindo que o jogador $B$ vença ou empate com o jogador $A$.
- **Complexidade:** Tempo $O(1)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum