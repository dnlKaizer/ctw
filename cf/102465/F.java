import java.io.*;
import java.util.*;

public class F {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(in.nextLong(), in.nextLong(), in.nextLong());

        long ans = LINF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, calc(pts[i], pts));
        }
        out.println(ans);
        
    }

    static long calc(Point o, Point[] points) {
        int n = points.length;
        Point ref = o.equals(points[0]) ? points[1] : points[0];
        Point vRef = ref.sub(o);
        long total = 0;
        
        int m = n-1;
        Point[] pts = new Point[m];
        for (int i = 0, k = 0; i < n; i++) {
            Point p = points[i];
            if (p.equals(o)) continue;
            pts[k++] = p;
            total += p.g;
        }

        Arrays.sort(pts, (a, b) -> {
            if (a == b) return 0;

            Point vA = a.sub(o);
            Point vB = b.sub(o);

            long crossA = vRef.vec(vA);
            long crossB = vRef.vec(vB);

            // Semiplano 0: lado esquerdo de vR, ou colinear no mesmo sentido (0°)
            // Semiplano 1: lado direito de vR, ou colinear no sentido oposto (180°)
            int halfA = (crossA > 0 || (crossA == 0 && vRef.dot(vA) > 0)) ? 0 : 1;
            int halfB = (crossB > 0 || (crossB == 0 && vRef.dot(vB) > 0)) ? 0 : 1;

            if (halfA != halfB) {
                return Integer.compare(halfA, halfB);
            }

            return Long.compare(vB.vec(vA), 0);
        });

        // Array duplicado para facilitar a rotação de 360 graus
        Point[] pts2 = new Point[2 * m];
        for (int i = 0; i < m; i++) {
            pts2[i] = pts[i];
            pts2[i + m] = pts[i];
        }

        long side = 0;
        int r = 0;
        long ans = LINF;

        for (int l = 0; l < m; l++) {
            Point vL = pts2[l].sub(o);

            // Garante que r avance pelo menos até l + 1
            if (r < l + 1) {
                r = l + 1;
            }

            // Avança r enquanto o ponto estiver estritamente à esquerda do vetor vL (semiplano de 180°)
            while (r < l + m) {
                Point vR = pts2[r].sub(o);
                if (vL.vec(vR) > 0) {
                    side += pts2[r].g;
                    r++;
                } else {
                    break;
                }
            }

            // side = soma estrita dos pontos à esquerda da reta o -> pts[l]
            // otherSide = soma estrita dos pontos à direita
            long otherSide = (total - pts2[l].g) - side;
            ans = Math.min(ans, Math.abs(side - otherSide));

            // Ao avançar l no próximo passo, remove-se pts[l + 1] que estava em side (se r > l + 1)
            if (r > l + 1) {
                side -= pts2[l + 1].g;
            }
        }
        
        return ans;
    }

    static record Point(long x, long y, long g) {

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y, LINF);
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y, LINF);
        }

        long dot(Point p) {
            return x * p.x + y * p.y;
        }

        long vec(Point p) {
            return x * p.y - y * p.x;
        }
    }

    static record Decimal(long num, long den) implements Comparable<Decimal> {
        public Decimal(long num, long den) {
            long g = gcd(num, den);
            num /= g;
            den /= g;

            if (den < 0) {
                num *= -1;
                den *= -1;
            }

            this.num = num;
            this.den = den;
        }

        public int compareTo(Decimal d) {
            long a = num * d.den;
            long b = d.num * den;
            return Long.compare(a, b);
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        run();    
        out.flush();
    }

    static class FastScanner {
        private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = in.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt(int radix) { return Integer.parseInt(next(), radix); }
        int nextInt() { return nextInt(10); }

        long nextLong(int radix) { return Long.parseLong(next(), radix);}
        long nextLong() { return Long.parseLong(next(), 10);}

        double nextDouble() { return Double.parseDouble(next()); }
    }

}