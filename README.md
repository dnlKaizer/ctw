# Competitive Programming Training Workflow (C++)

Ambiente local para resolução, execução e depuração de problemas de programação competitiva em **C++**, configurado para o VS Code e Git Bash no Windows.

O projeto permite gerar automaticamente a estrutura de pastas e arquivos `.cpp` vazios para cada contest/problema em `src/`, compilar os binários `.exe` de forma espelhada e isolada em `bin/`, executar direto pelo terminal (com suporte a arquivos de teste) ou via atalho `F5`, e debugar via `Ctrl + F5` com breakpoints usando `g++` e `gdb`.

---

## Estrutura do Repositório

```text
.
├── .vscode/
│   ├── launch.json     # Configuração do debugger C++ via GDB (Ctrl + F5)
│   ├── settings.json   # Configuração do IntelliSense C++ (g++ / C++20) e ocultação de binários
│   └── tasks.json      # Tasks de compilação espelhada em bin/ e execução rápida (F5)
├── src/                # Código-fonte (.cpp) e editoriais (.md) organizados por plataforma
│   ├── cf/             # Problemas do Codeforces (ex: src/cf/908/C/main.cpp e README.md)
│   ├── bee/            # Problemas do Beecrowd (ex: src/bee/1938/main.cpp e README.md)
│   ├── atc/            # Problemas do AtCoder (ex: src/atc/abc300/A/main.cpp e README.md)
│   └── gen/            # Testes genéricos e algoritmos (ex: src/gen/geometry/ConvexHull/main.cpp)
├── bin/                # Executáveis compilados (.exe) espelhando a estrutura de src/ (ignorado pelo Git)
├── cli.py              # Script unificado de geração dos diretórios, main.cpp, README.md e casos de teste
├── template.md         # Template base para o README.md de cada problema
└── README.md
```

> **Nota:** Os executáveis (`.exe`) são gerados de forma espelhada dentro de `bin/<caminho>/` (por exemplo, `src/cf/2044/A.cpp` gera `bin/cf/2044/A.exe`).

---

## Configuração Inicial

### 1. Pré-requisitos
* Compilador **G++ (MinGW-w64 / MSYS2 UCRT64)** e **GDB** adicionados ao `PATH` (`C:\msys64\ucrt64\bin`).
* Python 3.x instalado.
* VS Code com a extensão **C/C++ (`ms-vscode.cpptools`)**.
* Git Bash.

### 2. Configurações do VS Code

#### `.vscode/settings.json`
```json
{
  "files.exclude": {
    "**/*.exe": true,
    "**/*.o": true,
    "**/*.class": true
  },
  "C_Cpp.default.compilerPath": "C:/msys64/ucrt64/bin/g++.exe",
  "C_Cpp.default.cppStandard": "c++20",
  "C_Cpp.default.intelliSenseMode": "windows-gcc-x64"
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
      "command": "FILE=$(cygpath -m '${file}'); REL_DIR=$(dirname \"$FILE\"); REL_DIR=${REL_DIR#*src/}; OUT_DIR=\"bin/$REL_DIR\"; EXE=\"$OUT_DIR/${fileBasenameNoExtension}.exe\"; PCH=\"bin/_pch/bits/stdc++.h.gch\"; if [ ! -f \"$PCH\" ]; then mkdir -p bin/_pch/bits && printf '#pragma once\\n#include_next <bits/stdc++.h>\\n' > bin/_pch/bits/stdc++.h && g++ -std=c++20 -g -O0 -Wall -x c++-header bin/_pch/bits/stdc++.h -o \"$PCH\"; fi; mkdir -p \"$OUT_DIR\" bin/_debug && if [ ! -f \"$EXE\" ] || [ \"$FILE\" -nt \"$EXE\" ]; then g++ -Ibin/_pch -std=c++20 -g -O0 -Wall \"$FILE\" -o \"$EXE\" || exit 1; fi && cp \"$EXE\" bin/_debug/debug.exe",
      "options": {
        "cwd": "${workspaceFolder}",
        "shell": {
          "executable": "C:/Program Files/Git/bin/bash.exe",
          "args": ["--norc", "--noprofile", "-c"]
        }
      },
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "presentation": {
        "reveal": "silent"
      },
      "problemMatcher": "$gcc"
    },
    {
      "label": "run-current-file",
      "type": "shell",
      "command": "FILE=$(cygpath -m '${file}'); REL_DIR=$(dirname \"$FILE\"); REL_DIR=${REL_DIR#*src/}; OUT_DIR=\"bin/$REL_DIR\"; EXE=\"$OUT_DIR/${fileBasenameNoExtension}.exe\"; PCH=\"bin/_pch/bits/stdc++.h.gch\"; if [ ! -f \"$PCH\" ]; then mkdir -p bin/_pch/bits && printf '#pragma once\\n#include_next <bits/stdc++.h>\\n' > bin/_pch/bits/stdc++.h && g++ -std=c++20 -g -O0 -Wall -x c++-header bin/_pch/bits/stdc++.h -o \"$PCH\"; fi; mkdir -p \"$OUT_DIR\" && if [ ! -f \"$EXE\" ] || [ \"$FILE\" -nt \"$EXE\" ]; then g++ -Ibin/_pch -std=c++20 -g -O0 -Wall \"$FILE\" -o \"$EXE\" || exit 1; fi && \"$EXE\"",
      "options": {
        "cwd": "${workspaceFolder}",
        "shell": {
          "executable": "C:/Program Files/Git/bin/bash.exe",
          "args": ["--norc", "--noprofile", "-c"]
        }
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
      "problemMatcher": "$gcc"
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
      "name": "Debug Current Problem (C++)",
      "type": "cppdbg",
      "request": "launch",
      "program": "${workspaceFolder}/bin/_debug/debug.exe",
      "args": [],
      "stopAtEntry": false,
      "cwd": "${fileDirname}",
      "environment": [],
      "externalConsole": false,
      "MIMode": "gdb",
      "miDebuggerPath": "C:/msys64/ucrt64/bin/gdb.exe",
      "setupCommands": [
        {
          "description": "Enable pretty-printing for gdb",
          "text": "-enable-pretty-printing",
          "ignoreFailures": true
        }
      ],
      "preLaunchTask": "build-current-file"
    }
  ]
}
```

#### Atalhos de Teclado do VS Code (`keybindings.json`)
Para usar **`F5`** como Run (`runc`) e **`Ctrl + F5`** como Debug:
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
# Localiza a raiz do projeto (onde está o cli.py) mesmo se o terminal estiver em uma subpasta
_ctw_root() {
    local dir="$PWD"
    while [ "$dir" != "/" ] && [ -n "$dir" ]; do
        if [ -f "$dir/cli.py" ]; then
            echo "$dir"
            return 0
        fi
        local parent=$(dirname "$dir")
        [ "$parent" = "$dir" ] && break
        dir="$parent"
    done
    git rev-parse --show-toplevel 2>/dev/null || echo "."
}

# Gerar arquivos para plataformas (Codeforces, Beecrowd, AtCoder, Genérico) e casos de teste
cf() {
    python "$(_ctw_root)/cli.py" cf "$@"
}

bee() {
    python "$(_ctw_root)/cli.py" bee "$@"
}

atc() {
    python "$(_ctw_root)/cli.py" atc "$@"
}

gen() {
    python "$(_ctw_root)/cli.py" gen "$@"
}

tests() {
    python "$(_ctw_root)/cli.py" tests "$@"
}

# Compilar C++ (em bin/<caminho_relativo>) e executar uma solução
runc() {
    if [ -z "$1" ]; then
        echo "Uso: runc <caminho_ou_pasta> [input_file]"
        echo "Exemplo: runc cf/2044/A"
        echo "Exemplo: runc src/cf/2044/A"
        echo "Exemplo: runc bee/1938 in1.txt"
        echo "Exemplo: runc bee/1938/Main in1.txt"
        return 1
    fi

    local ROOT_DIR=$(cygpath -m "$(_ctw_root)")
    local TARGET=$(cygpath -m "$1")
    TARGET="${TARGET%/}"
    TARGET="${TARGET%.cpp}"
    local FILE=""

    if [ ! -f "${TARGET}.cpp" ] && [ ! -d "$TARGET" ]; then
        if [ -f "$ROOT_DIR/${TARGET}.cpp" ] || [ -d "$ROOT_DIR/$TARGET" ]; then
            TARGET="$ROOT_DIR/$TARGET"
        elif [ -f "$ROOT_DIR/src/${TARGET}.cpp" ] || [ -d "$ROOT_DIR/src/$TARGET" ]; then
            TARGET="$ROOT_DIR/src/$TARGET"
        fi
    fi

    if [ -f "${TARGET}.cpp" ]; then
        FILE="${TARGET}.cpp"
    elif [ -d "$TARGET" ]; then
        if [ -f "$TARGET/main.cpp" ]; then
            FILE="$TARGET/main.cpp"
            TARGET="$TARGET/main"
        elif [ -f "$TARGET/Main.cpp" ]; then
            FILE="$TARGET/Main.cpp"
            TARGET="$TARGET/Main"
        elif [ -f "$TARGET/$(basename "$TARGET").cpp" ]; then
            FILE="$TARGET/$(basename "$TARGET").cpp"
            TARGET="$TARGET/$(basename "$TARGET")"
        else
            for f in "$TARGET"/*.cpp; do
                if [ -f "$f" ]; then
                    FILE="$f"
                    TARGET="${f%.cpp}"
                    break
                fi
            done
        fi
    fi

    if [ -z "$FILE" ] || [ ! -f "$FILE" ]; then
        echo "Erro: Arquivo .cpp não encontrado para '$1'!"
        return 1
    fi

    FILE=$(cygpath -m "$(cd "$(dirname "$FILE")" && pwd)/$(basename "$FILE")")
    local SRC_DIR=$(dirname "$FILE")
    local BIN_NAME=$(basename "$TARGET")

    local REL_DIR="$SRC_DIR"
    if [[ "$REL_DIR" == */src/* ]]; then
        REL_DIR="${REL_DIR#*src/}"
    elif [[ "$REL_DIR" == src/* ]]; then
        REL_DIR="${REL_DIR#src/}"
    elif [[ "$REL_DIR" == "src" ]]; then
        REL_DIR=""
    fi

    local OUT_DIR="$ROOT_DIR/bin${REL_DIR:+/$REL_DIR}"
    mkdir -p "$OUT_DIR"

    local EXE_PATH="$OUT_DIR/${BIN_NAME}.exe"
    local PCH_DIR="$ROOT_DIR/bin/_pch"
    local PCH_PATH="$PCH_DIR/bits/stdc++.h.gch"

    if [ ! -f "$PCH_PATH" ]; then
        mkdir -p "$PCH_DIR/bits"
        printf '#pragma once\n#include_next <bits/stdc++.h>\n' > "$PCH_DIR/bits/stdc++.h"
        g++ -std=c++20 -g -O0 -Wall -x c++-header "$PCH_DIR/bits/stdc++.h" -o "$PCH_PATH" || return 1
    fi

    if [ ! -f "$EXE_PATH" ] || [ "$FILE" -nt "$EXE_PATH" ]; then
        g++ -I"$PCH_DIR" -std=c++20 -g -O0 -Wall "$FILE" -o "$EXE_PATH" || return 1
    fi

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
        "$EXE_PATH" < "$INPUT_FILE"
    else
        "$EXE_PATH"
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

O projeto aceita comandos dedicados para cada plataforma (`cf`, `bee`, `atc` e `gen`), criando uma pasta isolada para cada problema com `main.cpp` (vazio) e `README.md` (preenchido a partir do `template.md`, substituindo automaticamente `[ID]` e `[URL do problema]`):

* **Links gerados automaticamente no `README.md`:**
  * **Codeforces (`cf`):**
    * Contest (`id <= 100000`): `https://codeforces.com/contest/<id>/problem/<letter>`
    * Gym (`id > 100000`): `https://codeforces.com/gym/<id>/problem/<letter>`
  * **Beecrowd (`bee`):** `https://judge.beecrowd.com/pt/problems/view/<id>`
  * **AtCoder (`atc`):** `https://atcoder.jp/contests/<id>/tasks/<id>_<letter>`
  * **Genérico (`gen`):** `[COLOQUE O LINK AQUI]`

#### **Codeforces (`cf`)**
* Gerar problemas padrão de um contest (de **A** até **F**):
  ```bash
  cf 908
  ```
  *(Cria `src/cf/908/A/main.cpp` e `README.md` até `src/cf/908/F/`)*

* Gerar problemas específicos:
  ```bash
  cf 908 C
  ```
  *(Cria `src/cf/908/C/main.cpp` e `src/cf/908/C/README.md`)*

---

#### **AtCoder (`atc`)**
* Gerar problemas padrão de um contest (de **A** até **F**):
  ```bash
  atc abc300
  ```
  *(Cria `src/atc/abc300/A/main.cpp` e `README.md` até `F/`)*

* Gerar problemas específicos:
  ```bash
  atc abc300 A B C
  ```

---

#### **Beecrowd (`bee`)**
* Criar a pasta do problema diretamente pelo ID:
  ```bash
  bee 1938
  ```
  *(Cria `src/bee/1938/main.cpp` e `src/bee/1938/README.md`)*

* Agrupar vários problemas por categoria/tópico:
  ```bash
  bee math 1028 1161
  ```
  *(Cria `src/bee/math/1028/main.cpp` e `README.md`, etc.)*

---

#### **Genérico / Testes / Algoritmos (`gen`)**
* Criar pastas de estudo, algoritmos ou testes em subpastas:
  ```bash
  gen geometry ConvexHull
  ```
  *(Cria `src/gen/geometry/ConvexHull/main.cpp` e `src/gen/geometry/ConvexHull/README.md`)*

* Criar múltiplos tópicos de uma vez:
  ```bash
  gen graphs Dijkstra BFS DFS
  ```
  *(Cria `src/gen/graphs/Dijkstra/main.cpp` e `README.md`, etc.)*

---

#### **Gerar Arquivos de Entrada (`tests`)**
* Criar $N$ arquivos de teste numerados (`in1.txt`, `in2.txt`, ..., `inN.txt`) dentro da pasta desejada:
  ```bash
  tests bee/1938 3
  tests cf/908/C 2
  ```
  *(Cria `in1.txt`, `in2.txt`, etc. dentro da pasta do problema)*

---

### 2. Executar soluções pelo terminal (`runc`)

O comando `runc` aceita o caminho da pasta do problema (com ou sem `src/`), encontra automaticamente o `main.cpp`, compila com `g++ -std=c++20 -O2 -Wall` no diretório espelhado dentro de `bin/` e executa o binário:

* **Codeforces:**
  ```bash
  runc cf/908/C
  runc src/cf/908/C
  ```

* **AtCoder:**
  ```bash
  runc atc/abc300/A
  ```

* **Beecrowd:**
  ```bash
  runc bee/1938
  ```

* **Genérico:**
  ```bash
  runc gen/geometry/ConvexHull
  ```

* **Execução passando arquivo de entrada (redirecionamento de input):**
  Pode passar o caminho da pasta (ou do arquivo) e o nome do arquivo de entrada (com ou sem `.txt`):
  ```bash
  runc cf/908/C in1.txt               # executa src/cf/908/C/main.cpp com src/cf/908/C/in1.txt
  runc cf/908/C in1                   # também encontra in1.txt automaticamente
  runc bee/1938 in1                   # executa src/bee/1938/main.cpp com src/bee/1938/in1.txt
  ```

---

### 3. Executar (`F5`) e Debugar (`Ctrl + F5`) no VS Code

#### **Execução Rápida (`F5`)**
1. Com o arquivo `.cpp` aberto (dentro de `src/`), pressione **`F5`**.
2. A task `run-current-file` executará o comando `runc` automaticamente para o arquivo atual (compilando em `bin/<caminho_relativo>`), limpará a tela e focará o cursor no terminal integrado.
3. Cole (`Ctrl + V`) ou digite o caso de teste diretamente no terminal.

#### **Debug com Breakpoints (`Ctrl + F5`)**
1. Abra qualquer arquivo `.cpp` em `src/`.
2. Clique ao lado esquerdo da numeração da linha para marcar um **breakpoint** (ponto vermelho).
3. Pressione **`Ctrl + F5`**.
4. A task `build-current-file` compilará o código com flags de debug (`-g -O0`) em `bin/_debug/debug.exe` e o **GDB** pausará na linha marcada.
5. Use `F10` para avançar linha a linha (ou `F5` para continuar até o próximo breakpoint) e inspecione variáveis/vetores/containers STL no painel à esquerda. Se o código solicitar entrada de dados, digite no terminal integrado.
