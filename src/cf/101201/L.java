import java.io.*;
import java.util.*;

public class L {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        Point[] pts = new Point[n];
        Point p = new Point(INF, INF, INF);
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(in.nextInt(), in.nextInt(), i);
            if (pts[i].x < p.x || (pts[i].x == p.x && pts[i].y < p.y)) p = pts[i];
        }
        String s = in.next();

        boolean[] visited = new boolean[n];
        List<Integer> ans = new ArrayList<>();
        ans.add(p.i);
        visited[p.i] = true;

        for (int k = 0; k < s.length(); k++) {
            int next = s.charAt(k) == 'L' ? 1 : -1;
            for (int i = 0; i < n; i++) {
                Point a = pts[i];
                if (visited[i]) continue;

                Point vA = a.sub(p);
                boolean isBorder = true;
                for (int j = 0; j < n; j++) {
                    Point b = pts[j];
                    if (visited[j] || b.equals(a)) continue;
                    
                    Point vB = b.sub(p);
                    if (vA.vec(vB) != next) {
                        isBorder = false;
                        break;
                    }
                }
                if (!isBorder) continue;

                p = a;
                ans.add(p.i);
                visited[p.i] = true;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) ans.add(i);
        }

        for (int i : ans) {
            out.print((i+1) + " ");
        }
        out.println();

    }

    static int signal(int x) {
        if (x == 0) return 0;
        return x < 0 ? -1 : 1;
    }

    static record Point(int x, int y, int i) implements Comparable<Point> {

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y, INF);
        }

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y, INF);
        }

        int dot(Point p) {
            return x * p.x + y * p.y;
        }

        int vec(Point p) {
            return signal(x * p.y - y * p.x);
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