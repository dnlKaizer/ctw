import java.io.*;
import java.util.*;

public class PointInPolygon {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt(), m = in.nextInt();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(in.nextInt(), in.nextInt());

        for (int i = 0; i < m; i++) {
            Point p = new Point(in.nextInt(), in.nextInt());

            if (isBorder(p, pts)) out.println("BOUNDARY");
            else if (isInside(p, pts)) out.println("INSIDE");
            else out.println("OUTSIDE");
        }

    }

    static boolean isBorder(Point p, Point[] pts) {
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            Point a = pts[i];
            Point b = pts[(i+1) % n];

            Point AB = b.sub(a);
            Point AP = p.sub(a);
            Point BP = p.sub(b);

            // Produto Vetorial igual a zero
            boolean isCollinear = AB.vec(AP) == 0;

            // Produto escalar igual a -1 (Sentidos Opostos)
            // Produto escalar igual a  0 (P coincide com A ou B)
            boolean isBetween = AP.dot(BP) <= 0;

            if (isCollinear && isBetween) return true;
        }
        return false;
    }

    static boolean isInside(Point P, Point[] pts) {
        int count = 0;
        int n = pts.length;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Point A = pts[i];
            Point B = pts[j];

            if (A.y > B.y) {
                Point temp = A;
                A = B;
                B = temp;
            }

            //         *B\
            // *P----------\-----------
            //             *A\

            // Verifica se a aresta AB cruza a linha horizontal y = p.y
            boolean straddlesY = (A.y > P.y) != (B.y > P.y);
            if (!straddlesY) continue;

            // Verifica se o ponto de interseção está à direita de P
            Point AB = B.sub(A);
            Point AP = P.sub(A);
            boolean intersectsToTheRight = AB.vec(AP) > 0;
            
            if (intersectsToTheRight) count++;
        }

        return count % 2 == 1;
    }

    static class Decimal {
        final long num;
        final long den;

        Decimal(long num, long den) {
            long g = gcd(num, den);
            num /= g;
            den /= g;
            if (den < 0) {
                den *= -1;
                num *= -1;
            }
            this.num = num;
            this.den = den;
        }

        boolean isBiggerThan(long x) {
            x *= den;
            return x > num;
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static class Point {
        final long x;
        final long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        long dot(Point p) {
            return x * p.x + y * p.y; 
        }

        long vec(Point p) {
            return x * p.y - y * p.x; 
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