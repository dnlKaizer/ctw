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
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;

            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) pts[i] = new Point(in.nextLong(), in.nextLong());

            if (n <= 2) {
                out.println(n);
                continue;
            }

            long ans = 2;
            for (int i = 0; i < n; i++) {
                Point a = pts[i];
                for (int j = i+1; j < n; j++) {
                    Point b = pts[j];
                    Map<Center, Integer> map = new HashMap<>();
                    for (int k = j+1; k < n; k++) {
                        Point c = pts[k];
                        long d = 2 * ((b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x));
                        if (d == 0) continue;

                        long x = (c.y - a.y)*(b.x*b.x + b.y*b.y - a.x*a.x - a.y*a.y) -
                                (b.y - a.y)*(c.x*c.x + c.y*c.y - a.x*a.x - a.y*a.y);

                        long y = (b.x - a.x)*(c.x*c.x + c.y*c.y - a.x*a.x - a.y*a.y) -
                                (c.x - a.x)*(b.x*b.x + b.y*b.y - a.x*a.x - a.y*a.y);

                        map.merge(new Center((double) x / d, (double) y / d), 1, Integer::sum);
                    }

                    for (int cnt : map.values()) ans = Math.max(ans, cnt+2);
                }
            }

            out.println(ans);
        }
    }

    static record Point(long x, long y) {}

    static record Center(double x, double y) {
        private static final double eps = 1e-9;

        @Override
        public boolean equals(Object o) {
            Center c = (Center) o;
            return Math.abs(x - c.x) < eps && Math.abs(y - c.y) < eps;
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