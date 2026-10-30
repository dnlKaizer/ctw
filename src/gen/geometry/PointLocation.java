import java.io.*;
import java.util.*;

public class PointLocation {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int t = in.nextInt();
        while (t-- > 0) {
            Point a = new Point(in.nextInt(), in.nextInt());
            Point b = new Point(in.nextInt(), in.nextInt());
            Point c = new Point(in.nextInt(), in.nextInt());

            int ans = (b.sub(a)).vec(c.sub(b));

            if (ans == 0) out.println("TOUCH");
            else if (ans == 1) out.println("LEFT");
            else out.println("RIGHT");
        }

    }

    static class Point {
        final long x;
        final long y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point sub(Point p) {
            return new Point(x-p.x, y-p.y);
        }

        int vec(Point p) {
            long res = x * p.y - y * p.x;
            return res == 0 ? 0 : res > 0 ? 1 : -1;
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