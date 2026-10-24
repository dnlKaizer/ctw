import java.io.*;
import java.util.*;

public class C {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt(), r = in.nextInt();
        int[] vet = new int[n];
        for (int i = 0; i < n; i++) vet[i] = in.nextInt();

        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            int a = vet[i];
            double b = r;
            for (int j = i-1; j >= 0; j--) {
                Point p = pts[j];
                if (p.x <= (a+2*r) && p.x >= (a-2*r)) {
                    b = Math.max(b, p.y + Math.sqrt(4*r*r - (a-p.x)*(a-p.x)));
                }
            }

            pts[i] = new Point(a, b);
        }

        for (int i = 0; i < n; i++) {
            out.print(pts[i].y + " ");
        }
        out.println();
    }

    static record Point(int x, double y) {}

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