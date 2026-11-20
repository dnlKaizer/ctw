# 2200A - Eating Game

- **Link:** https://codeforces.com/contest/2200/problem/A
- **Tópicos:** Greedy
- **Ideia central:** Dados $N$ jogadores, cada um com $a_i$ refeições para comer, determinar quantos podem ser os últimos a finalizarem suas refeições, considerando que eles comem em ordem.
- **Insight:** Os jogadores que tiverem o maior número de refeições sempre serão possíveis vencedores, dependendo apenas da ordem de início. Logo, basta guardar uma variável com o $a_i$ máximo e um contador e iterar pelo array
- **Complexidade:** Tempo $O(1)$, Espaço $O(1)$
- **Pontos de atenção:** Nenhum