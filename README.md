# Codeforces Training Workflow

Ambiente local para resolução, execução e depuração de problemas de programação competitiva em Java, configurado para o VS Code e Git Bash no Windows.

O projeto permite gerar automaticamente a estrutura de pastas para cada contest/problema em `src/`, compilar os binários `.class` de forma espelhada e isolada em `bin/`, executar direto pelo terminal (com suporte a arquivos de teste) ou via atalho `F5`, e debugar via `Ctrl + F5` com breakpoints sem conflito de pacotes (`package`).

---

## Estrutura do Repositório

```text
.
├── .vscode/
│   ├── java.prefs      # Preferências do compilador Java (ignorar avisos de type safety)
│   ├── launch.json     # Configuração do debugger (Ctrl + F5)
│   ├── settings.json   # Configuração de outputPath (bin) e sourcePaths (src/...)
│   └── tasks.json      # Tasks de compilação espelhada em bin/ e execução rápida (F5)
├── src/                # Código-fonte (.java) organizado por plataforma
│   ├── cf/             # Problemas do Codeforces (ex: src/cf/2044/A.java)
│   ├── bee/            # Problemas do Beecrowd (ex: src/bee/1938/Main.java)
│   ├── atc/            # Problemas do AtCoder (ex: src/atc/abc300/A.java)
│   └── gen/            # Testes genéricos e algoritmos (ex: src/gen/geometry/ConvexHull.java)
├── bin/                # Bytecodes compilados (.class) espelhando a estrutura de src/ (ignorado pelo Git)
├── cli.py              # Script unificado de geração dos arquivos e registro de sourcePaths
├── Template.java       # Código base com FastScanner, constantes e I/O
└── README.md
```

> **Nota:** Os arquivos compilados (`.class`, incluindo classes internas como `FastScanner` ou `Point`) são gerados de forma espelhada dentro de `bin/<caminho>/` (por exemplo, `src/cf/2044/A.java` gera `bin/cf/2044/A.class`). Cada subpasta de problema é adicionada automaticamente ao `java.project.sourcePaths` do VS Code para funcionar no pacote padrão (*default package*) sem necessidade de declarar `package`.

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
  },
  "java.project.outputPath": "bin",
  "java.project.sourcePaths": [
    "src/cf",
    "src/bee",
    "src/atc",
    "src/gen/geometry"
  ],
  "java.settings.url": ".vscode/java.prefs",
  "java.debug.settings.onBuildFailureProceed": true,
  "java.errors.incompleteClasspath.severity": "ignore"
}
```

#### `.vscode/java.prefs`
```properties
eclipse.preferences.version=1
org.eclipse.jdt.core.compiler.problem.uncheckedTypeOperation=ignore
org.eclipse.jdt.core.compiler.problem.rawTypeReference=ignore
```

#### `.vscode/tasks.json`
```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "build-current-file",
      "type": "shell",
      "command": "REL_DIR=$(cygpath -m '${relativeFileDirname}'); REL_DIR=${REL_DIR#src/}; FILE=$(cygpath -m '${file}'); OUT_DIR=bin/$REL_DIR; mkdir -p $OUT_DIR bin/_debug && rm -f bin/_debug/*.class && javac -encoding UTF-8 -g -d $OUT_DIR $FILE && cp $OUT_DIR/*.class bin/_debug/",
      "options": {
        "cwd": "${workspaceFolder}"
      },
      "group": "build",
      "presentation": {
        "reveal": "silent"
      },
      "problemMatcher": "$javac"
    },
    {
      "label": "run-current-file",
      "type": "shell",
      "command": "runj '${file}'",
      "options": {
        "cwd": "${workspaceFolder}"
      },
      "group": {
        "kind": "test",
        "isDefault": true
      },
      "presentation": {
        "reveal": "always",
        "panel": "shared",
        "focus": true,
        "clear": true
      },
      "problemMatcher": []
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
        "${workspaceFolder}/bin/_debug"
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

#### Atalhos de Teclado do VS Code (`keybindings.json`)
Para usar **`F5`** como Run (`runj`) e **`Ctrl + F5`** como Debug:
1. Pressione `Ctrl + Shift + P` e selecione **`Preferences: Open Keyboard Shortcuts (JSON)`**.
2. Adicione os atalhos no final do array `[ ... ]`:
```json
[
  {
    "key": "f5",
    "command": "workbench.action.tasks.runTask",
    "args": "run-current-file",
    "when": "!inDebugMode"
  },
  {
    "key": "ctrl+f5",
    "command": "workbench.action.debug.start",
    "when": "!inDebugMode"
  }
]
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

# Compilar (em bin/<caminho_relativo>) e executar uma solução
runj() {
    if [ -z "$1" ]; then
        echo "Uso: runj <caminho_ou_pasta> [input_file]"
        echo "Exemplo: runj cf/2044/A"
        echo "Exemplo: runj src/cf/2044/A"
        echo "Exemplo: runj bee/1938 in1.txt"
        echo "Exemplo: runj bee/1938/Main in1.txt"
        return 1
    fi

    local TARGET=$(cygpath -m "$1")
    TARGET="${TARGET%/}"
    TARGET="${TARGET%.java}"
    local FILE=""

    if [ ! -f "${TARGET}.java" ] && [ ! -d "$TARGET" ]; then
        if [ -f "src/${TARGET}.java" ] || [ -d "src/$TARGET" ]; then
            TARGET="src/$TARGET"
        fi
    fi

    if [ -f "${TARGET}.java" ]; then
        FILE="${TARGET}.java"
    elif [ -d "$TARGET" ]; then
        if [ -f "$TARGET/Main.java" ]; then
            FILE="$TARGET/Main.java"
            TARGET="$TARGET/Main"
        elif [ -f "$TARGET/$(basename "$TARGET").java" ]; then
            FILE="$TARGET/$(basename "$TARGET").java"
            TARGET="$TARGET/$(basename "$TARGET")"
        else
            for f in "$TARGET"/*.java; do
                if [ -f "$f" ]; then
                    FILE="$f"
                    TARGET="${f%.java}"
                    break
                fi
            done
        fi
    fi

    if [ -z "$FILE" ] || [ ! -f "$FILE" ]; then
        echo "Erro: Arquivo .java não encontrado para '$1'!"
        return 1
    fi

    local SRC_DIR=$(dirname "$FILE")
    local CLASS_NAME=$(basename "$TARGET")

    local REL_DIR="$SRC_DIR"
    if [[ "$REL_DIR" == */src/* ]]; then
        REL_DIR="${REL_DIR#*src/}"
    elif [[ "$REL_DIR" == src/* ]]; then
        REL_DIR="${REL_DIR#src/}"
    elif [[ "$REL_DIR" == "src" ]]; then
        REL_DIR=""
    fi

    local OUT_DIR="bin${REL_DIR:+/$REL_DIR}"
    mkdir -p "$OUT_DIR"

    javac -encoding UTF-8 -g -d "$OUT_DIR" "$FILE" || return 1

    local INPUT_FILE=""
    if [ -n "$2" ]; then
        if [ -f "$2" ]; then
            INPUT_FILE="$2"
        elif [ -f "$SRC_DIR/$2" ]; then
            INPUT_FILE="$SRC_DIR/$2"
        elif [ -f "${2}.txt" ]; then
            INPUT_FILE="${2}.txt"
        elif [ -f "$SRC_DIR/${2}.txt" ]; then
            INPUT_FILE="$SRC_DIR/${2}.txt"
        else
            echo "Erro: Arquivo de entrada '$2' não encontrado (nem em ./ nem em $SRC_DIR/)!"
            return 1
        fi
    fi

    if [ -n "$INPUT_FILE" ]; then
        java -cp "$OUT_DIR" "$CLASS_NAME" < "$INPUT_FILE"
    else
        java -cp "$OUT_DIR" "$CLASS_NAME"
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

O projeto aceita comandos dedicados para cada plataforma (`cf`, `bee`, `atc` e `gen`), criando os arquivos dentro de `src/` e registrando a pasta automaticamente no `.vscode/settings.json`:

#### **Codeforces (`cf`)**
* Gerar problemas padrão de um contest (de **A** até **F**):
  ```bash
  cf 2044
  ```
  *(Cria `src/cf/2044/A.java` até `F.java` com as classes `public class A`, etc.)*

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
  *(Cria `src/atc/abc300/A.java` até `F.java`)*

* Gerar problemas específicos:
  ```bash
  atc abc300 A B C
  ```

---

#### **Beecrowd (`bee`)**
* Criar pasta e arquivo `Main.java` diretamente pelo ID do problema:
  ```bash
  bee 1938
  ```
  *(Cria `src/bee/1938/Main.java` com a classe `public class Main`, já pronta para submissão no Beecrowd)*

* Agrupar vários problemas por categoria/tópico:
  ```bash
  bee math 1028 1161 1163
  ```
  *(Cria `src/bee/math/1028.java`, `src/bee/math/1161.java`, etc.)*

---

#### **Genérico / Testes / Algoritmos (`gen`)**
* Criar arquivos avulsos de estudo, algoritmos ou testes em subpastas:
  ```bash
  gen tests ConvexHull
  ```
  *(Cria `src/gen/tests/ConvexHull.java`)*

* Criar múltiplos tópicos de uma vez:
  ```bash
  gen graphs Dijkstra BFS DFS
  ```
  *(Cria `src/gen/graphs/Dijkstra.java`, `src/gen/graphs/BFS.java`, etc.)*

---

### 2. Executar soluções pelo terminal (`runj`)

O comando `runj` aceita caminhos **com ou sem** o prefixo `src/`, compila os `.class` no diretório espelhado dentro de `bin/` e executa a solução:

* **Codeforces:**
  ```bash
  runj cf/2044/A
  runj src/cf/2044/A
  ```

* **AtCoder:**
  ```bash
  runj atc/abc300/A
  runj src/atc/abc300/A
  ```

* **Beecrowd:**
  ```bash
  runj bee/1938
  runj src/bee/1938/Main
  ```

* **Genérico:**
  ```bash
  runj gen/geometry/ConvexHull
  runj src/gen/graphs/Dijkstra
  ```

* **Execução passando arquivo de entrada (redirecionamento de input):**
  Pode passar o caminho da pasta ou do arquivo, e o nome do arquivo de entrada (com ou sem `.txt`):
  ```bash
  runj bee/1938 in1.txt               # executa src/bee/1938/Main.java com src/bee/1938/in1.txt
  runj bee/1938 in1                   # também encontra in1.txt automaticamente
  runj src/bee/1938/Main in1.txt      # especificando o caminho com src/ e a classe explicitamente
  runj cf/2044/A in.txt               # busca no diretório atual ou dentro de src/cf/2044/in.txt
  ```

---

### 3. Executar (`F5`) e Debugar (`Ctrl + F5`) no VS Code

#### **Execução Rápida (`F5`)**
1. Com o arquivo `.java` aberto (dentro de `src/`), pressione **`F5`**.
2. A task `run-current-file` executará o comando `runj` automaticamente para o arquivo atual (compilando em `bin/<caminho_relativo>`), limpará a tela e focará o cursor no terminal integrado.
3. Cole (`Ctrl + V`) ou digite o caso de teste diretamente no terminal.

#### **Debug com Breakpoints (`Ctrl + F5`)**
1. Abra qualquer arquivo `.java` em `src/` (seja em `src/cf/`, `src/atc/`, `src/bee/` ou `src/gen/`).
2. Clique ao lado esquerdo da numeração da linha para marcar um **breakpoint** (ponto vermelho).
3. Pressione **`Ctrl + F5`**.
4. A task `build-current-file` compilará os `.class` para a pasta espelhada em `bin/<caminho_relativo>` (e atualizará `bin/_debug` para isolamento de classpath) e a execução pausará na linha marcada.
5. Use `F10` para avançar linha a linha (ou `F5` para continuar até o próximo breakpoint) e inspecione as variáveis locais no painel à esquerda. Se o código solicitar entrada de dados, digite diretamente no terminal integrado.

---

### 4. Submissão nas Plataformas

Como os arquivos **não possuem declaração de `package`**, você pode submeter o código diretamente:
* **Codeforces / AtCoder / CSES:** Copie e cole todo o código do arquivo `.java` correspondente.
* **Beecrowd:** Lembre-se de garantir que a classe principal se chame `public class Main` no editor do Beecrowd caso esteja submetendo em Java.
