import java.io.*;
import java.util.*;

public class ConvexHull {
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

            Set<Point> set = new HashSet<>();
            List<Point> pts = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Point p = new Point(in.nextInt(), in.nextInt());
                if (set.add(p)) pts.add(p);
            }
            
            List<Point> ans = convexHull(pts);
            out.println(ans.size());
            ans.forEach(p -> out.println(p.x + " " + p.y));
        }
    }

    static boolean ccw(Point a, Point b, Point c) {
        return ((b).sub(a)).vector((c).sub(b)) > 0;
    }
    
    static List<Point> convexHull(List<Point> pts) {
        if (pts.size() <= 2) return pts;

        Collections.sort(pts);
        List<Point> inf = new ArrayList<>(), sup = new ArrayList<>();

        for (int i = 0; i < pts.size(); i++) {
            while (inf.size() >= 2 && !ccw(inf.get(inf.size() - 2), inf.get(inf.size() - 1), pts.get(i))) {
                inf.remove(inf.size() - 1);
            }
            inf.add(pts.get(i));
        }
        
        for (int i = pts.size() - 1; i >= 0; i--) {
            while (sup.size() >= 2 && !ccw(sup.get(sup.size() - 2), sup.get(sup.size() - 1), pts.get(i))) {
                sup.remove(sup.size() - 1);
            }
            sup.add(pts.get(i));
        }

        inf.remove(inf.size() - 1);
        sup.remove(sup.size() - 1);

        List<Point> ans = new ArrayList<>(inf);
        ans.addAll(sup);

        return ans;
    }

    static record Point(int x, int y) implements Comparable<Point> {

        public int vector(Point p) {
            return (x * p.y) - (y * p.x);
        }

        public int scalar(Point p) {
            return (x * p.x) + (y * p.y);
        }

        public Point scalar(int c) {
            return new Point(x * c, y * c);
        }

        public Point sum(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        public Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        @Override
        public int compareTo(Point p) {
            int ret = Integer.compare(x, p.x);
            if (ret != 0) return ret;
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