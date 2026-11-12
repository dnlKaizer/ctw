import sys
import re
from pathlib import Path

DEFAULT_PROBLEMS = ["A", "B", "C", "D", "E", "F"]
VALID_PLATFORMS = {"cf", "bee", "atc", "gen"}
VALID_COMMANDS = VALID_PLATFORMS | {"tests"}

def build_problem_url(platform: str, group_id: str, problem_id: str = "") -> str:
    """Constrói a URL do problema de acordo com a plataforma."""
    if platform == "cf":
        if group_id.isdigit() and int(group_id) > 100000:
            return f"https://codeforces.com/gym/{group_id}/problem/{problem_id}"
        return f"https://codeforces.com/contest/{group_id}/problem/{problem_id}"
    if platform == "bee":
        return f"https://judge.beecrowd.com/pt/problems/view/{problem_id}"
    if platform == "atc":
        contest = group_id.lower()
        return f"https://atcoder.jp/contests/{contest}/tasks/{contest}_{problem_id.lower()}"
    return "[COLOQUE O LINK AQUI]"

def get_editorial_content(base_dir: Path, problem_label: str, problem_url: str) -> str:
    """Lê o template.md da raiz (se existir) e substitui os marcadores do problema."""
    for candidate in ["template.md", "Template.md"]:
        template_path = base_dir / candidate
        if template_path.is_file():
            content = template_path.read_text(encoding="utf-8")
            content = re.sub(r"\[ID(?:/Nome do Problema)?\]", problem_label, content)
            content = re.sub(r"\[URL do problema\]", problem_url, content)
            return content
    return f"# {problem_label} - [Nome do Problema]\n\n- **Link:** {problem_url}\n"

def create_problem_dir(base_dir: Path, problem_dir: Path, problem_label: str, problem_url: str):
    """Cria a pasta do problema contendo main.cpp e README.md."""
    problem_dir.mkdir(parents=True, exist_ok=True)

    cpp_path = problem_dir / "main.cpp"
    if cpp_path.exists() or (problem_dir / "Main.cpp").exists():
        print(f"[-] {cpp_path.relative_to(base_dir)} já existe. Ignorado.")
    else:
        cpp_path.write_text("", encoding="utf-8")
        print(f"[+] Criado: {cpp_path.relative_to(base_dir)}")

    readme_path = problem_dir / "README.md"
    if readme_path.exists():
        print(f"[-] {readme_path.relative_to(base_dir)} já existe. Ignorado.")
    else:
        editorial_content = get_editorial_content(base_dir, problem_label, problem_url)
        readme_path.write_text(editorial_content, encoding="utf-8")
        print(f"[+] Criado: {readme_path.relative_to(base_dir)}")

def handle_tests(args: list[str], base_dir: Path):
    if not args:
        print("Uso: tests <caminho_da_pasta> [quantidade]")
        print("Exemplos:")
        print("  tests bee/1938 3")
        print("  tests cf/2044/A 2")
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

    # Se o usuário passou o caminho de um arquivo (ex: bee/1938/main.cpp), usa a pasta pai
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
            print("  bee 1001 1002 1003")
            print("  bee math 1028 1161")
        else:
            print("  gen geometry ConvexHull")
            print("  gen graphs Dijkstra BFS DFS")
        sys.exit(1)

    group_id = args[0]
    raw_problems = args[1:]

    if platform in {"cf", "atc"}:
        problems = [p.upper() for p in raw_problems] if raw_problems else DEFAULT_PROBLEMS
        for p in problems:
            clean_p = p[:-4] if p.endswith(".cpp") else p
            problem_dir = base_dir / "src" / platform / group_id / clean_p
            url = build_problem_url(platform, group_id, clean_p)
            create_problem_dir(base_dir, problem_dir, f"{group_id}{clean_p}", url)

    elif platform == "bee":
        if not raw_problems:
            # Ex: bee 1938 -> src/bee/1938/main.cpp e README.md
            clean_id = group_id[:-4] if group_id.endswith(".cpp") else group_id
            problem_dir = base_dir / "src" / "bee" / clean_id
            url = build_problem_url("bee", group_id, clean_id)
            create_problem_dir(base_dir, problem_dir, clean_id, url)
        elif group_id.isdigit() and all(p.isdigit() for p in raw_problems):
            # Ex: bee 1001 1002 1003 -> cria uma pasta para cada ID
            for pid in [group_id] + raw_problems:
                problem_dir = base_dir / "src" / "bee" / pid
                url = build_problem_url("bee", group_id, pid)
                create_problem_dir(base_dir, problem_dir, pid, url)
        else:
            # Ex: bee math 1028 1161 -> src/bee/math/1028/main.cpp e README.md
            for p in raw_problems:
                clean_p = p[:-4] if p.endswith(".cpp") else p
                problem_dir = base_dir / "src" / "bee" / group_id / clean_p
                url = build_problem_url("bee", group_id, clean_p)
                create_problem_dir(base_dir, problem_dir, clean_p, url)

    else:  # gen
        if not raw_problems:
            # Ex: gen ConvexHull -> src/gen/ConvexHull/main.cpp e README.md
            clean_id = group_id[:-4] if group_id.endswith(".cpp") else group_id
            problem_dir = base_dir / "src" / "gen" / clean_id
            url = build_problem_url("gen", group_id, clean_id)
            create_problem_dir(base_dir, problem_dir, clean_id, url)
        else:
            # Ex: gen geometry ConvexHull -> src/gen/geometry/ConvexHull/main.cpp e README.md
            for p in raw_problems:
                clean_p = p[:-4] if p.endswith(".cpp") else p
                problem_dir = base_dir / "src" / "gen" / group_id / clean_p
                url = build_problem_url("gen", group_id, clean_p)
                create_problem_dir(base_dir, problem_dir, clean_p, url)

if __name__ == "__main__":
    main()
