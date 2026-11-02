import java.io.*;
import java.util.*;

public class J {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static final double eps = 1e-9;

    static void run() {
        int theta = in.nextInt(), n = in.nextInt();
        Citizen[] cts = new Citizen[n];
        for (int i = 0; i < n; i++) cts[i] = new Citizen(in.nextInt(), in.nextInt());

        Arrays.sort(cts, (a, b) -> Integer.compare(a.x, b.x));
        double tan = Math.tan(Math.PI * theta / 180);

        double ans = 0, max = -1;
        for (int i = 0; i < n; i++) {
            double dx = cts[i].h / tan;
            double xs = cts[i].x, xe = xs + dx;
            
            if (max > xs) {
                double aux = xe - max;
                ans += Math.max(0, aux);
            } else ans += dx;

            max = Math.max(max, xe);
        }

        out.println(ans);
    }

    static record Citizen(int x, int h) {}

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