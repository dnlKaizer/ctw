import java.io.*;
import java.util.*;

public class PolygonArea {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        Point[] pts = new Point[n+1];
        for (int i = 0; i < n; i++) pts[i] = new Point(in.nextInt(), in.nextInt());
        pts[n] = pts[0];
        
        long ans = 0;
        for (int i = 0; i < n; i++) {
            Point curr = pts[i];
            Point next = pts[i + 1];
            ans += curr.vec(next);
        }

        out.println(Math.abs(ans));
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