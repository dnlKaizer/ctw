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
            for (int i = 0; i < n; i++) pts[i] = new Point(in.nextDouble(), in.nextDouble());

            double ans = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    ans = Math.min(ans, dist(pts[i], pts[j]));
                }
            }

            if (ans < 10000) out.println(String.format("%.4f", ans));
            else out.println("INFINITY");
        }
    }

    static double dist(Point a, Point b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    } 

    static record Point(double x, double y) {}

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