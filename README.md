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
├── cf/
│   ├── 1199/
│   │   ├── A.java
│   │   └── F.java
│   └── 2044/
│       ├── A.java
│       └── B.java
├── cf.py               # Script de geração dos arquivos do contest
├── template.java       # Código base com FastScanner, constantes e I/O
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
# Gerar arquivos para um contest
cf() {
    python cf.py "$@"
}

# Compilar e executar uma solução
runj() {
    if [ -z "$1" ]; then
        echo "Uso: runj cf/<contest>/<letra> [input_file]"
        echo "Exemplo: runj cf/1199/F"
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

### 1. Criar novo contest
Para gerar os arquivos padrão (de **A** até **F**):
```bash
cf 2044
```

Para gerar apenas problemas específicos:
```bash
cf 2044 A B C
```

O script lê o arquivo `template.java`, cria a pasta `cf/2044/` e gera cada arquivo nomeado com a respectiva classe (`public class A` em `A.java`, etc.).

---

### 2. Executar soluções pelo terminal (`runj`)

* **Execução interativa (digitando pelo terminal):**
  ```bash
  runj cf/2044/A
  ```

* **Execução passando arquivo de entrada:**
  ```bash
  runj cf/2044/A in.txt
  ```

---

### 3. Debugar no VS Code (`F5`)

1. Abra o arquivo do problema (ex: `cf/2044/A.java`).
2. Clique ao lado esquerdo da numeração da linha para marcar um **breakpoint** (ponto vermelho).
3. Pressione **`F5`**.
4. A compilação prévia será acionada automaticamente e a execução pausará na linha marcada.
5. Use `F10` para avançar linha a linha e inspecione as variáveis locais no painel à esquerda. Se o código solicitar entrada de dados, digite diretamente no terminal integrado que abrirá na parte inferior.

---

## Submissão no Codeforces

Como os arquivos **não possuem declaração de `package`**, basta copiar e colar todo o código do arquivo `.java` (ou fazer o upload direto do arquivo) no formulário de submissão do Codeforces.