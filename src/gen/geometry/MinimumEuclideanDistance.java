import java.io.*;
import java.util.*;

public class MinimumEuclideanDistance {
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(in.nextInt(), in.nextInt());
        
        out.println(sweepLine(pts));
    }

    static long sweepLine(Point[] pts) {
        int n = pts.length;
        Arrays.sort(pts, Point::xCompareTo);

        long d2 = dist(pts[0], pts[1]);
        long d = (long) Math.ceil(Math.sqrt(d2));

        TreeSet<Point> set = new TreeSet<>(Point::yCompareTo);
        set.add(pts[0]);
        for (int l = 0, r = 1; r < n; r++) {
            Point p = pts[r];
            while (l < r) {
                long dx = p.x - pts[l].x;
                if (dx * dx < d2) break;
                set.remove(pts[l++]);
            }

            for (Point c : set.tailSet(new Point(-LINF, p.y - d), false)) {
                long dy = c.y - p.y;
                if (dy > 0 && dy * dy >= d2) break;

                long dist = dist(p, c);
                if (d2 > dist) {
                    d2 = dist;
                    d = (long) Math.ceil(Math.sqrt(d2));
                }
            }

            set.add(p);
        }

        return d2;
    }

    static long dist(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx * dx + dy * dy; 
    }

    static class Point {
        final long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        int xCompareTo(Point p) {
            return x != p.x ? Long.compare(x, p.x) : Long.compare(y, p.y);
        }

        int yCompareTo(Point p) {
            return y != p.y ? Long.compare(y, p.y) : Long.compare(x, p.x);
        }
    }

    public static void main(String[] args) {
        run();    
        out.flush();
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() {
            if (ptr >= len) {
                try {
                    len = in.read(buffer);
                    ptr = 0;
                } catch (IOException e) {
                    return -1;
                }
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sgn;
        }
    }
}