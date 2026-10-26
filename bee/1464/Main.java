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
            if (n == 0) return;

            Point[] pts = new Point[n];
            boolean[] outPts = new boolean[n];
            for (int i = 0; i < n; i++) pts[i] = new Point(in.nextInt(), in.nextInt());

            Arrays.sort(pts);
            
            int m = n, cnt = 0;
            while (m > 0) {
                m -= convex_hull(pts, outPts);
                cnt++;
            }
    
            if (cnt % 2 == 1) out.println("Take this onion to the lab!");
            else out.println("Do not take this onion to the lab!");
        }

    }

    static int convex_hull(Point[] pts, boolean[] outPts) {
        int n = pts.length;
        List<Integer> infIdx = new ArrayList<>(), supIdx = new ArrayList<>();
        List<Point> inf = new ArrayList<>(), sup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (outPts[i]) continue;
            Point p = pts[i];
            while(inf.size() >= 2 && !ccw(inf.get(inf.size()-2), inf.get(inf.size()-1), p)) {
                inf.remove(inf.size()-1);
                infIdx.remove(infIdx.size()-1);
            }
            inf.add(p);
            infIdx.add(i);
        }

        for (int i = n-1; i >= 0; i--) {
            if (outPts[i]) continue;
            Point p = pts[i];
            while(sup.size() >= 2 && !ccw(sup.get(sup.size()-2), sup.get(sup.size()-1), p)) {
                sup.remove(sup.size()-1);
                supIdx.remove(supIdx.size()-1);
            }
            sup.add(p);
            supIdx.add(i);
        }

        infIdx.remove(infIdx.size()-1);
        supIdx.remove(supIdx.size()-1);
        infIdx.addAll(supIdx);

        for (int i : infIdx) outPts[i] = true;

        return infIdx.size();
    }

    static boolean ccw(Point a, Point b, Point c) {
        Point ab = b.sub(a);
        Point bc = c.sub(b);
        return (ab).vec(bc) <= 0;
    }

    static record Point(int x, int y) implements Comparable<Point> {

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        int dot(Point p) {
            return x * p.x + y * p.y;
        }

        int vec(Point p) {
            return x * p.y - y * p.x;
        }

        public int compareTo(Point p) {
            int comp = Integer.compare(x, p.x);
            if (comp != 0) return comp;
            return Integer.compare(y, p.y);
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