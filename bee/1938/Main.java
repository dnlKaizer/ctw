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

        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(in.nextInt(), in.nextInt());
        }
        Arrays.sort(pts);

        int[][] lim = new int[n][];
        for (int i = 0; i < n; i++) lim[i] = new int[]{-INF, INF};

        for (int i = 0; i < n-1; i++) {
            Point a = pts[i], b = pts[i+1];
            if (a.x == b.x) {
                lim[i][1] = b.y;
                lim[i+1][0] = a.y;
            }
        }

        long ans = 0; 
        for (int i = 0; i < n-1; i++) {
            Point curr = pts[i];
            for (int j = i + 1; j < n; j++) {
                Point next = pts[j];

                // Caso em que o próximo ponto tem altura maior que curr
                if (next.y > curr.y) {
                    if (next.y < lim[i][1]) {
                        ans++;
                        lim[i][1] = next.y;
                    }
                    while (j < n-1 && pts[j+1].x == next.x) j++;
                    continue;
                }

                // Caso em que o próximo ponto tem altura menor ou igual a curr
                while (j < n-1 && pts[j+1].x == next.x && pts[j+1].y < curr.y) next = pts[++j];
                if (next.y == curr.y) break;

                if (next.y > lim[i][0]) {
                    ans++;
                    lim[i][0] = next.y;
                }
            }
        }

        out.println(ans);
    }

    static record Point(int x, int y) implements Comparable<Point> {
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