import java.io.*;
import java.util.*;

public class I {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        String s = in.next();
        long n = in.nextLong(), m = s.length();
        
        int[] letter = new int[26];
        long count = 0;
        for (int i = 0; i < m; i++) {
            int curr = s.charAt(i) - 97;
            letter[curr]++;
            for (int j = curr+1; j < 26; j++) {
                count += letter[j] % MOD;
                count = count % MOD;
            }
        }
        if (n == 1) {
            out.println(count);
            return;
        }
        
        long nxtCount = 0;
        for (int i = 0; i < m; i++) {
            int curr = s.charAt(i) - 97;
            letter[curr]++;
            for (int j = curr+1; j < 26; j++) {
                nxtCount += letter[j] % MOD;
                nxtCount = nxtCount % MOD;
            }
        }
        long b;
        if (n % 2 == 0) {
            b = (n / 2) % MOD;
            b *= (n-1) % MOD;
            b = b % MOD;
        } else {
            b = ((n-1) / 2) % MOD;
            b *= n % MOD;
            b = b % MOD;
        }

        n = n % MOD;
        long a = (n * count) % MOD;
        long c = (nxtCount - count + MOD) % MOD;

        long ans = (a + ((b * c) % MOD)) % MOD;
        // FORMULA = (n * count) + (n * (n-1) / 2) * (nxtCount - count)
        out.println(ans);
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