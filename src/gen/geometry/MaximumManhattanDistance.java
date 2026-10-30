import java.io.*;
import java.util.*;

public class MaximumManhattanDistance {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        long xMax = -LINF, xMin = LINF;
        long yMax = -LINF, yMin = LINF;
        for (int i = 0; i < n; i++) {
            Point p = new Point(in.nextInt(), in.nextInt());
            xMax = Math.max(p.x, xMax);
            xMin = Math.min(p.x, xMin);
            yMax = Math.max(p.y, yMax);
            yMin = Math.min(p.y, yMin);
            out.println(Math.max(xMax - xMin, yMax - yMin));
        }

    }

    static class Point { 
        final long x;
        final long y;

        Point(long x, long y) {
            this.x = x - y;
            this.y = x + y;
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