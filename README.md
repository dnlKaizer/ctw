# Codeforces Training Workflow

Ambiente local para resolução, execução e depuração de problemas do Codeforces em Java, configurado para o VS Code e Git Bash no Windows.

O projeto permite gerar automaticamente a estrutura de pastas para cada contest, compilar e executar direto pelo terminal (com suporte a arquivos de teste) e debugar via `F5` com breakpoints sem conflito de pacotes (`package`).

---

## Estrutura do Repositório

```text
.
├── .vscode/
│   ├── launch.json     # Configuração do debugger (F5)
│   ├── settings.json   # Ocultação visual dos binários .class
│   └── tasks.json      # Compilação automática antes do debug
├── cf/                 # Problemas do Codeforces
├── bee/                # Problemas do Beecrowd
├── atc/                # Problemas do AtCoder
├── gen/                # Testes genéricos e algoritmos
├── cli.py              # Script unificado de geração dos arquivos
├── Template.java       # Código base com FastScanner, constantes e I/O
└── README.md
```

> **Nota:** Os arquivos compilados (`.class`) são gerados no mesmo diretório do arquivo `.java`, mas ficam ocultos na árvore de arquivos do VS Code para manter o ambiente limpo.

---

## Configuração Inicial

### 1. Pré-requisitos
* Java JDK (versão 11 ou superior recomendada) adicionado ao `PATH`.
* Python 3.x instalado.
* VS Code com a extensão **Extension Pack for Java**.
* Git Bash.

### 2. Configurações do VS Code

#### `.vscode/settings.json`
```json
{
  "files.exclude": {
    "**/*.class": true
  }
}
```

#### `.vscode/tasks.json`
```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "build-current-file",
      "type": "shell",
      "command": "javac",
      "args": [
        "-encoding",
        "UTF-8",
        "-g",
        "-d",
        ".",
        "${fileBasename}"
      ],
      "options": {
        "cwd": "${fileDirname}"
      },
      "group": "build",
      "presentation": {
        "reveal": "silent"
      },
      "problemMatcher": "$javac"
    }
  ]
}
```

#### `.vscode/launch.json`
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Debug Current Problem",
      "request": "launch",
      "mainClass": "${fileBasenameNoExtension}",
      "classPaths": [
        "${fileDirname}"
      ],
      "sourcePaths": [
        "${fileDirname}"
      ],
      "preLaunchTask": "build-current-file",
      "console": "integratedTerminal"
    }
  ]
}
```

### 3. Configurar atalhos no Git Bash (`~/.bashrc`)

Abra o arquivo no Git Bash:
```bash
notepad ~/.bashrc
```

Adicione o seguinte conteúdo ao final do arquivo:
```bash
# Gerar arquivos para plataformas (Codeforces, Beecrowd, AtCoder, Genérico)
cf() {
    python cli.py cf "$@"
}

bee() {
    python cli.py bee "$@"
}

atc() {
    python cli.py atc "$@"
}

gen() {
    python cli.py gen "$@"
}

# Compilar e executar uma solução
runj() {
    if [ -z "$1" ]; then
        echo "Uso: runj <caminho_sem_extensao_ou_com_java> [input_file]"
        echo "Exemplo: runj cf/2044/A"
        echo "Exemplo: runj gen/tests/ConvexHull"
        return 1
    fi

    local TARGET="${1%.java}"
    local FILE="${TARGET}.java"

    if [ ! -f "$FILE" ]; then
        echo "Erro: '$FILE' não encontrado!"
        return 1
    fi

    local DIR=$(dirname "$FILE")
    local CLASS_NAME=$(basename "$TARGET")

    javac -encoding UTF-8 -g -d "$DIR" "$FILE" || return 1

    if [ -n "$2" ] && [ -f "$2" ]; then
        java -cp "$DIR" "$CLASS_NAME" < "$2"
    else
        java -cp "$DIR" "$CLASS_NAME"
    fi
}
```

Salve e recarregue o terminal:
```bash
source ~/.bashrc
```

---

## Como Usar

### 1. Criar estrutura de arquivos por plataforma

O projeto aceita comandos dedicados para cada plataforma (`cf`, `bee`, `atc` e `gen`):

#### **Codeforces (`cf`)**
* Gerar problemas padrão de um contest (de **A** até **F**):
  ```bash
  cf 2044
  ```
  *(Cria `cf/2044/A.java` até `F.java` com as classes `public class A`, etc.)*

* Gerar problemas específicos:
  ```bash
  cf 2044 A B C
  ```

---

#### **AtCoder (`atc`)**
* Gerar problemas padrão de um contest (de **A** até **F**):
  ```bash
  atc abc300
  ```
  *(Cria `atc/abc300/A.java` até `F.java`)*

* Gerar problemas específicos:
  ```bash
  atc abc300 A B C
  ```

---

#### **Beecrowd (`bee`)**
* Criar pasta e arquivo diretamente pelo ID do problema:
  ```bash
  bee 1001
  ```
  *(Cria `bee/1001/1001.java` com a classe `public class _1001` pronta para execução local)*

* Agrupar vários problemas por categoria/tópico:
  ```bash
  bee math 1028 1161 1163
  ```
  *(Cria `bee/math/1028.java`, `bee/math/1161.java`, etc.)*

---

#### **Genérico / Testes / Algoritmos (`gen`)**
* Criar arquivos avulsos de estudo, algoritmos ou testes em subpastas:
  ```bash
  gen tests ConvexHull
  ```
  *(Cria `gen/tests/ConvexHull.java`)*

* Criar múltiplos tópicos de uma vez:
  ```bash
  gen graphs Dijkstra BFS DFS
  ```
  *(Cria `gen/graphs/Dijkstra.java`, `gen/graphs/BFS.java`, etc.)*

---

### 2. Executar soluções pelo terminal (`runj`)

O comando `runj` funciona de forma transparente para **qualquer pasta** do projeto:

* **Codeforces:**
  ```bash
  runj cf/2044/A
  ```

* **AtCoder:**
  ```bash
  runj atc/abc300/A
  ```

* **Beecrowd:**
  ```bash
  runj bee/1001/1001
  runj bee/math/1028
  ```

* **Genérico:**
  ```bash
  runj gen/tests/ConvexHull
  runj gen/graphs/Dijkstra
  ```

* **Execução passando arquivo de entrada (redirecionamento de input):**
  ```bash
  runj cf/2044/A in.txt
  runj bee/1001/1001 in.txt
  ```

---

### 3. Debugar no VS Code (`F5`)

1. Abra qualquer arquivo `.java` (seja em `cf/`, `atc/`, `bee/` ou `gen/`).
2. Clique ao lado esquerdo da numeração da linha para marcar um **breakpoint** (ponto vermelho).
3. Pressione **`F5`**.
4. A compilação prévia será acionada automaticamente e a execução pausará na linha marcada.
5. Use `F10` para avançar linha a linha e inspecione as variáveis locais no painel à esquerda. Se o código solicitar entrada de dados, digite diretamente no terminal integrado.

---

## Submissão nas Plataformas

Como os arquivos **não possuem declaração de `package`**, você pode submeter o código diretamente:
* **Codeforces / AtCoder:** Copie e cole todo o código do arquivo `.java` correspondente.
* **Beecrowd:** Lembre-se de renomear a classe principal para `public class Main` no editor do Beecrowd caso esteja submetendo em Java.