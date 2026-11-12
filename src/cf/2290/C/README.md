# 2290C - Books on a Shelf

- **Link:** https://codeforces.com/contest/2290/problem/C
- **Tópicos:** Constructive Algorithms, Greedy, Sorting
- **Ideia central:** Encontrar o máximo de livros que são visíveis na prateleira pela lateral. Cada livro tem uma altura e, se olhado de lado, um livro é visível se não houver outro livro mais alto na frente dele.
- **Insight:** A ideia que resolve o problema é criar uma pilha de livros em que os menores estão nas pontas e os maiores no meio, ou seja, duas pilhas de livros, uma crescente e outra decrescente, que se encontram no livro mais alto. Em outras palavras, são duas listas de livros ordenadas. Tendo isso em mente, é possível perceber que dois livros de mesma altura, se estiverem na mesma pilha, apenas um deles será visível. Portanto, a solução é colocá-los em pilhas diferentes. Além disso, se houver mais de dois livros de mesma altura, no máximo dois deles serão visíveis, um em cada pilha. Assim, a solução é contar quantos livros existem de cada altura e somar o mínimo entre 2 e a quantidade de livros de cada altura. É possível usar uma deque para representar as pilhas de livros e usá-la para a impressão da resposta.
- **Complexidade:** Tempo $O(N\log{N})$, Espaço $O(N)$
- **Pontos de atenção:** Nenhum