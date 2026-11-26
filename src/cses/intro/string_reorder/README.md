# String Reorder

- **Link:** https://cses.fi/problemset/task/1743/
- **Tópicos:** Greedy, Strings
- **Ideia central:** Dada uma string de tamanho $n$ com letras maiúsculas (`A`–`Z`), reordenar seus caracteres de modo que nenhum par de caracteres adjacentes seja igual e que a string resultante seja a menor possível lexicograficamente (ou imprimir `-1` se for impossível).
- **Insight:** Uma reordenação válida existe se e somente se a frequência máxima de qualquer caractere for no máximo $\lceil n / 2 \rceil = (n + 1) / 2$. Para obter a menor string lexicográfica, construímos a resposta posição por posição escolhendo sempre a menor letra disponível (`'A'` a `'Z'`) diferente da anterior (`last`) tal que, após decrementar sua frequência, o restante dos caracteres ainda satisfaça o invariante de viabilidade: $\max(\text{freq}) \le \lfloor (n - i) / 2 \rfloor$.
- **Complexidade:** Tempo $O(n \cdot \Sigma^2)$ onde $\Sigma = 26$ (ou seja, $O(n)$), Espaço $O(n + \Sigma)$
- **Pontos de atenção:** Verificar a condição de impossibilidade ($\max(\text{freq}) > (n + 1) / 2$) logo no início e manter o invariante $\max(\text{freq}) \le \lfloor \text{restante} / 2 \rfloor$ ao testar cada candidato.