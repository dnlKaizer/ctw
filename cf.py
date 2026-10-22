import sys
import re
from pathlib import Path

DEFAULT_PROBLEMS = ["A", "B", "C", "D", "E", "F"]

def main():
    if len(sys.argv) < 2:
        print("Uso: python cf.py <contest_id> [problemas]")
        print("Exemplos:")
        print("  python cf.py 2044")
        print("  python cf.py 2044 A B C")
        sys.exit(1)

    contest_id = sys.argv[1]
    problems = [p.upper() for p in sys.argv[2:]] if len(sys.argv) > 2 else DEFAULT_PROBLEMS

    base_dir = Path(__file__).resolve().parent
    template_path = base_dir / "template.java"

    if not template_path.is_file():
        print(f"Erro: 'template.java' não encontrado em {base_dir}")
        sys.exit(1)

    template_code = template_path.read_text(encoding="utf-8")
    target_dir = base_dir / "src" / "cf" / contest_id
    target_dir.mkdir(parents=True, exist_ok=True)

    for p in problems:
        file_path = target_dir / f"{p}.java"
        if file_path.exists():
            print(f"[-] {p}.java já existe. Ignorado.")
            continue

        # Substitui a primeira classe do topo (ex: "public class Main" ou "class Solution") por "public class <Letra>"
        code = re.sub(r'^(public\s+)?class\s+\w+', f'public class {p}', template_code, count=1, flags=re.MULTILINE)
        
        file_path.write_text(code, encoding="utf-8")
        print(f"[+] Criado: {file_path.relative_to(base_dir)}")

if __name__ == "__main__":
    main()