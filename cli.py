import sys
import re
import json
from pathlib import Path

DEFAULT_PROBLEMS = ["A", "B", "C", "D", "E", "F"]
VALID_PLATFORMS = {"cf", "bee", "atc", "gen"}

def update_vscode_source_paths(base_dir: Path, rel_path: str):
    """Garante que a subpasta esteja registrada no java.project.sourcePaths para evitar erros de package."""
    settings_path = base_dir / ".vscode" / "settings.json"
    if not settings_path.is_file():
        return

    try:
        data = json.loads(settings_path.read_text(encoding="utf-8"))
        source_paths = data.get("java.project.sourcePaths", [])
        for legacy in ("gen", "src/gen"):
            if legacy in source_paths:
                source_paths.remove(legacy)
        if rel_path not in source_paths:
            source_paths.append(rel_path)
            data["java.project.sourcePaths"] = source_paths
            settings_path.write_text(json.dumps(data, indent=4, ensure_ascii=False) + "\n", encoding="utf-8")
    except Exception:
        pass

def sanitize_identifier(name: str) -> str:
    """Garante um identificador Java válido a partir de um nome de arquivo ou problema."""
    cleaned = re.sub(r'[^a-zA-Z0-9_]', '_', name)
    if cleaned and cleaned[0].isdigit():
        cleaned = f"_{cleaned}"
    return cleaned or "Solution"

def main():
    args = sys.argv[1:]
    script_stem = Path(sys.argv[0]).stem.lower()

    # Identifica plataforma inicial pelo nome do script (ex: se executado como cf.py, bee.py, etc.)
    platform = script_stem if script_stem in VALID_PLATFORMS else None

    # Se chamado como cli.py (ou genérico), o primeiro argumento pode ser a plataforma
    if args and args[0].lower() in VALID_PLATFORMS:
        platform = args.pop(0).lower()

    if not platform:
        platform = "cf"

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
            print("  gen tests ConvexHull")
            print("  gen graphs Dijkstra BFS DFS")
        sys.exit(1)

    group_id = args[0]
    raw_problems = args[1:]

    base_dir = Path(__file__).resolve().parent
    template_path = None
    for candidate in ["template.java", "Template.java"]:
        if (base_dir / candidate).is_file():
            template_path = base_dir / candidate
            break

    if not template_path:
        print(f"Erro: 'Template.java' não encontrado em {base_dir}")
        sys.exit(1)

    template_code = template_path.read_text(encoding="utf-8")

    # Define a lista de problemas
    if platform in {"cf", "atc"}:
        problems = [p.upper() for p in raw_problems] if raw_problems else DEFAULT_PROBLEMS
    elif platform == "bee":
        # Se passou 'bee 1001', cria bee/1001/Main.java (padrão do Beecrowd)
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

    if platform == "gen" or (platform == "bee" and not group_id.isdigit()):
        update_vscode_source_paths(base_dir, f"src/{platform}/{group_id}")

    for p in problems:
        class_name = sanitize_identifier(p)
        file_path = target_dir / f"{p}.java"

        if file_path.exists():
            print(f"[-] {file_path.name} já existe. Ignorado.")
            continue

        # Substitui a declaração de classe base do template por "public class <NomeDaClasse>"
        code = re.sub(
            r'^(public\s+)?class\s+\w+',
            f'public class {class_name}',
            template_code,
            count=1,
            flags=re.MULTILINE
        )
        file_path.write_text(code, encoding="utf-8")
        print(f"[+] Criado: {file_path.relative_to(base_dir)}")

if __name__ == "__main__":
    main()
