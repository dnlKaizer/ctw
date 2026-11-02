import java.io.*;
import java.util.*;

public class B {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int k = in.nextInt(), n = in.nextInt();
        if (3 * k - n < 0 || 2 * k - n > 1) {
            out.println('*');
            return;
        }

        int three = 0, two = 0;
        int t = n;
        while (k > 0 && 2 * k < t) {
            t -= 3;
            k--;
            three++;
        }
        two = k;

        boolean one = false;
        if (three * 3 + two * 2 > n) {
            two--;
            one = true;
        }

        while (three-- > 0) out.print("-X-");
        while (two-- > 0) out.print("X-");
        if (one) out.print('X');
        out.println();
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