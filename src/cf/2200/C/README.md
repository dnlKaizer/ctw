# 2200C - Specialty String

- **Link:** https://codeforces.com/contest/2200/problem/C
- **Tópicos:** Strings, Greedy
- **Ideia central:** Dada uma string de tamanho $N$, escolher um par $(i, j)$ tal que: (1) $1 \leq i \lt j \leq N$, (2) $s_i = s_j \ne *$, (3) $s_k=*, para todo i < k < j$. Então, atribui $s_i:=*$ e $s_j:=*$. Se não tiver mais nenhum par que satisfaz a operação, o jogo termina. O objetivo é determinar se a string final pode ser formada apenas por caracteres $*$
- **Insight:** A primeira dupla de $s_i$ e $s_j$ deve ser formada por $j=i+1$, pois não existe nenhum $*$ na string original. Então, o problema se resume a verificar aberturas e fechamentos. Pode-se utilizar uma pilha para armazenar o último elemento que não é um $*$ e ir iterando a string para todo índice $1 \leq i \leq N$. Se o último caracter da pilha for igual a $s_i$, então remove ele, se não, adiciona $s_i$ na pilha. Para verificar a resposta, basta analisar se ainda existe pelo menos um caracter na pilha
- **Complexidade:** Tempo $O(N)$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum