import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int inst = 0;
        while (true) {
            inst++;
            int n = in.nextInt();
            if (n == 0) break;

            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(in.nextDouble(), in.nextDouble());
            }

            Center ans = new Center(pts[0], Double.MAX_VALUE);

            // 1. Testa pares de pontos (Círculos com diâmetro AB)
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Center C = find(pts[i], pts[j]);
                    if (C.r < ans.r - 1e-9 && isValid(C, pts)) {
                        ans = C;
                    }
                }
            }

            // 2. Testa trios de pontos (Apenas triângulos acutângulos)
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        Center C = find(pts[i], pts[j], pts[k]);
                        if (C.p != null && C.r < ans.r - 1e-9 && isValid(C, pts)) {
                            ans = C;
                        }
                    }
                }
            }

            out.println("Instancia " + inst);
            out.printf(Locale.US, "%.2f %.2f %.2f\n\n", ans.p.x, ans.p.y, ans.r);
        }
    }

    static boolean isValid(Center center, Point[] pts) {
        if (center.p == null) return false;
        for (Point p : pts) {
            if (p.dist(center.p) > center.r + eps) {
                return false;
            }
        }
        return true;
    }

    static Center find(Point A, Point B, Point P) {
        Point AB = B.sub(A);
        Point AC = P.sub(A);
        Point BC = P.sub(B);

        // Se o triângulo for obtusângulo ou retângulo, o círculo circunscrito não é mínimo
        // (o círculo mínimo já terá sido avaliado no teste de 2 pontos)
        if (AB.dot(AC) <= 0 || AB.scalar(-1).dot(BC) <= 0 || AC.scalar(-1).dot(BC.scalar(-1)) <= 0) {
            return new Center(null, Double.MAX_VALUE);
        }

        double det = AB.x * AC.y - AB.y * AC.x;
        if (Math.abs(det) < 1e-9) {
            return new Center(null, Double.MAX_VALUE);
        }

        double u2 = AB.dot(AB);
        double v2 = AC.dot(AC);

        double wx = (AC.y * u2 - AB.y * v2) / (2.0 * det);
        double wy = (AB.x * v2 - AC.x * u2) / (2.0 * det);

        Point center = new Point(A.x + wx, A.y + wy);
        double r = Math.sqrt(wx * wx + wy * wy);

        return new Center(center, r);
    }

    static Center find(Point A, Point B) {
        double x = (A.x + B.x) / 2;
        double y = (A.y + B.y) / 2;
        Point C = new Point(x, y);
        return new Center(C, C.dist(A));
    }
    
    static final double eps = 1e-7;

    static boolean dEquals(double x, double y) {
        return Math.abs(x - y) < eps;
    }

    static record Center(Point p, double r) {}

    static record Point(double x, double y) {

        Point perp() {
            return new Point(-y, x);
        }

        double dot(Point p) {
            return x * p.x + y * p.y;
        }

        Point scalar(double c) {
            return new Point(x * c, y * c);
        }

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        double dist(Point p) {
            double s = (x - p.x)*(x - p.x) + (y - p.y)*(y - p.y);
            return Math.sqrt(s);
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return dEquals(x, p.x) && dEquals(y, p.y);
        }
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