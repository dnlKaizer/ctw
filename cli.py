import sys
from pathlib import Path

DEFAULT_PROBLEMS = ["A", "B", "C", "D", "E", "F"]
VALID_PLATFORMS = {"cf", "bee", "atc", "gen"}
VALID_COMMANDS = VALID_PLATFORMS | {"tests"}

def handle_tests(args: list[str], base_dir: Path):
    if not args:
        print("Uso: tests <caminho_da_pasta> [quantidade]")
        print("Exemplos:")
        print("  tests bee/1938 3")
        print("  tests cf/2044 2")
        sys.exit(1)

    target_rel = args[0]
    count = 1
    if len(args) > 1:
        try:
            count = int(args[1])
            if count < 1:
                raise ValueError
        except ValueError:
            print(f"Erro: A quantidade '{args[1]}' deve ser um número inteiro positivo.")
            sys.exit(1)

    target_path = base_dir / target_rel
    if not target_path.exists() and not target_rel.startswith("src"):
        target_path = base_dir / "src" / target_rel

    # Se o usuário passou o caminho de um arquivo (ex: bee/1938/Main.cpp), usa a pasta pai
    if target_path.suffix in {".cpp", ".java"} or target_path.with_suffix(".cpp").is_file() or target_path.with_suffix(".java").is_file():
        target_dir = target_path.parent
    else:
        target_dir = target_path

    target_dir.mkdir(parents=True, exist_ok=True)

    for i in range(1, count + 1):
        file_path = target_dir / f"in{i}.txt"
        if file_path.exists():
            print(f"[-] {file_path.relative_to(base_dir)} já existe. Ignorado.")
        else:
            file_path.write_text("", encoding="utf-8")
            print(f"[+] Criado: {file_path.relative_to(base_dir)}")

def main():
    args = sys.argv[1:]
    script_stem = Path(sys.argv[0]).stem.lower()

    # Identifica comando inicial pelo nome do script ou pelo primeiro argumento
    command = script_stem if script_stem in VALID_COMMANDS else None

    if args and args[0].lower() in VALID_COMMANDS:
        command = args.pop(0).lower()

    if not command:
        command = "cf"

    base_dir = Path(__file__).resolve().parent

    if command == "tests":
        handle_tests(args, base_dir)
        return

    platform = command

    if not args:
        print(f"Uso: {platform} <contest_ou_pasta> [problemas...]")
        print("Exemplos:")
        if platform in {"cf", "atc"}:
            print(f"  {platform} 2044")
            print(f"  {platform} 2044 A B C")
        elif platform == "bee":
            print("  bee 1001")
            print("  bee math 1028 1161 1163")
        else:
            print("  gen geometry ConvexHull")
            print("  gen graphs Dijkstra BFS DFS")
        sys.exit(1)

    group_id = args[0]
    raw_problems = args[1:]

    # Define a lista de problemas
    if platform in {"cf", "atc"}:
        problems = [p.upper() for p in raw_problems] if raw_problems else DEFAULT_PROBLEMS
    elif platform == "bee":
        # Se passou 'bee 1001', cria src/bee/1001/Main.cpp (ou problemas específicos se informados)
        if raw_problems:
            problems = raw_problems
        else:
            problems = ["Main"]
    else:  # gen
        if raw_problems:
            problems = raw_problems
        else:
            problems = [group_id]

    target_dir = base_dir / "src" / platform / group_id
    target_dir.mkdir(parents=True, exist_ok=True)

    for p in problems:
        file_name = p if p.endswith(".cpp") else f"{p}.cpp"
        file_path = target_dir / file_name

        if file_path.exists():
            print(f"[-] {file_path.name} já existe. Ignorado.")
            continue

        file_path.write_text("", encoding="utf-8")
        print(f"[+] Criado: {file_path.relative_to(base_dir)}")

if __name__ == "__main__":
    main()
