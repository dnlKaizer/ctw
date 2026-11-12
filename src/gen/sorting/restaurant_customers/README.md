# Restaurant Customers

- **Link:** https://cses.fi/problemset/task/1619/
- **Tópicos:** Sorting, Array Diff, Prefix Sums
- **Ideia central:** Determinar o máximo de clientes simultâneos no restaurante, dado o horário de chegada e saída de cada cliente.
- **Insight:** Usar um array diff para marcar os horários de chegada e saída, e então calcular a soma de prefixos desse array. O resultado é o valor máximo encontrado durante a soma. Como os horários podem ser muito grandes, é necessário usar um map para marcar os intervalos de tempo
- **Complexidade:** Tempo $O(N\log{N})$, Espaço $O(N)$
- **Pontos de atenção:** Como os horários de chegada e saída são diferentes, não é necessário nenhum tratamento especial