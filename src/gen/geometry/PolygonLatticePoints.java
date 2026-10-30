import java.io.*;
import java.util.*;

public class PolygonLatticePoints {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(in.nextInt(), in.nextInt());

        long b = findBoundary(pts);
        out.println(findInside(pts, b) + " " + b);        
    }

    static long findInside(Point[] pts, long b) {
        int n = pts.length;
        long a = 0;
        for (int i = 0, j = n-1; i < n; j = i++) {
            a += pts[i].vec(pts[j]);
        }
        a = Math.abs(a);
        return (a - b + 2) / 2;
    }

    static long findBoundary(Point[] pts) {
        int n = pts.length;
        long count = 0;
        for (int i = 0, j = n-1; i < n; j = i++) {
            Point v = pts[j].sub(pts[i]);
            long g = Math.abs(gcd(v.x, v.y));
            count += g - 1;
        }
        return count + n;
    }

    static class Point {
        final long x;
        final long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y);
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

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return x == p.x && y == p.y;
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