import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static Point E;

    static void run() {
        int t = in.nextInt();
        while (t-- > 0) {
            E = new Point(in.nextInt(), in.nextInt());

            int P = in.nextInt();
            Decimal[] min = new Decimal[P];
            Decimal[] max = new Decimal[P];
            for (int i = 0; i < P; i++) {
                Point a = new Point(0, in.nextInt());
                Point b = new Point(0, in.nextInt());

                if (a.y > b.y) {
                    Point temp = a; a = b; b = temp;
                }

                min[i] = a.ang();
                max[i] = b.ang();
            }

            Arrays.sort(min);
            Arrays.sort(max);

            int ans = 0;
            int G = in.nextInt();
            for (int i = 0; i < G; i++) {
                Point p = new Point(in.nextInt(), in.nextInt());
                Decimal a = p.ang();
                int idx = Arrays.binarySearch(min, a);
                if (idx > 0) continue;

                idx = idx * (-1) - 2;
                if (idx < 0) continue;

                int comp = max[idx].compareTo(a);
                if (comp == 1) ans++;
            }

            out.println(ans);
        }
    }

    static record Point(int x, int y) {
        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        Decimal ang() {
            return new Decimal(y - E.y, x - E.x);
        }
    }

    static record Decimal(int num, int den) implements Comparable<Decimal> {
        public Decimal(int num, int den) {
            int g = gcd(num, den);
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
            int a = num * d.den;
            int b = d.num * den;
            return Integer.compare(a, b);
        }
    }

    static int gcd(int a, int b) {
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