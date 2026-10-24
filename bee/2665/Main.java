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
        int n = in.nextInt();
        Point A = new Point(in.nextInt(), 0);
        Point B = new Point(in.nextInt(), 0);

        Point[] pts = new Point[n];
        int[][] countIn = new int[n][];
        List<Integer>[] inPts = new List[n];

        for (int i = 0; i < n; i++) {
            pts[i] = new Point(in.nextInt(), in.nextInt());
            countIn[i] = new int[]{0, i};
            inPts[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (isIn(pts[i], pts[j], A, B)) {
                    countIn[i][0]++;
                    inPts[i].add(j);
                }
            }
        }

        Arrays.sort(countIn, (a, b) -> Integer.compare(a[0], b[0]));
        int[] best = new int[n];
        int ans = 0;
        for (int k = 0; k < n; k++) {
            int i = countIn[k][1];
            int count = 0;
            for (int j : inPts[i]) count = Math.max(best[j], count);
            best[i] = 1 + count;
            ans = Math.max(best[i], ans);
        }

        out.println(ans);

    }

    static boolean isIn(Point o, Point p, Point A, Point B) {
        Point ao = o.sub(A);
        Point ap = p.sub(A);
        boolean l = (ao).vec(ap) < 0;
        
        Point bo = o.sub(B);
        Point bp = p.sub(B);
        boolean r = (bo).vec(bp) > 0;

        return l && r;
    }

    static record Point(int x, int y) {

        int vec(Point p) {
            return (x * p.y) - (y * p.x);
        }

        Point sum(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
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