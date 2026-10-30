import java.io.*;
import java.util.*;

public class LineSegmentIntersection {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static final double eps = 1e-9;

    static void run() {
        int t = in.nextInt();
        while (t-- > 0) {
            Point a = new Point(in.nextInt(), in.nextInt());
            Point b = new Point(in.nextInt(), in.nextInt());
            Point c = new Point(in.nextInt(), in.nextInt());
            Point d = new Point(in.nextInt(), in.nextInt());

            out.println(intersect(a, b, c, d) ? "YES" : "NO");
        }
    }

    static boolean onSegment(Point a, Point b, Point p) {
        return p.x >= Math.min(a.x, b.x) && p.x <= Math.max(a.x, b.x) &&
               p.y >= Math.min(a.y, b.y) && p.y <= Math.max(a.y, b.y);
    }

    static boolean intersect(Point a, Point b, Point c, Point d) {
        int o1 = orient(a, b, c);
        int o2 = orient(a, b, d);
        int o3 = orient(c, d, a);
        int o4 = orient(c, d, b);

        // Caso general: se cruzan en un punto interior
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Casos particulares: un punto es colineal y está sobre el segmento
        if (o1 == 0 && onSegment(a, b, c)) return true;
        if (o2 == 0 && onSegment(a, b, d)) return true;
        if (o3 == 0 && onSegment(c, d, a)) return true;
        if (o4 == 0 && onSegment(c, d, b)) return true;

        return false;
    }

    static int orient(Point a, Point b, Point c) {
        return (b.sub(a)).vec(c.sub(b));
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