# 2185D - OutOfMemoryError

- **Link:** https://codeforces.com/contest/2185/problem/D
- **Tópicos:** Data Structures, Implementation, Math
- **Ideia central:** Encontrar o estado final de um array após uma série de operações do tipo $a_{b_i} = a_{b_i} + c_i$, nas quais se o novo valor $a_{b_i} > h$ o array voltava ao estado inicial.
- **Insight:** Não é necessário reiniciar o array a cada overflow, pode-se manter um array auxiliar que guarda o índice da último reset daquele elemento e uma variável que armazena o índice do último reset. Assim, ao atualizar um elemento, basta verificar se ele foi resetado desde o último reset global. 
- **Complexidade:** Tempo $O(N)$, Espaço auxiliar $O(N)$
- **Pontos de atenção:** Lembrar de atualizar o array ao final antes de imprimir o resultado.