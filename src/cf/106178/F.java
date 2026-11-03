import java.io.*;
import java.util.*;

public class F {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final long MOD = 1_000_000_007L;
    static final long MOD9 = 998_244_353L;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));


    public static void main(String[] args) {

        String s = in.next();
        int n = Math.min(s.length(), 12);

        long pow = 1;
        for (int i = 1; i < n; i++) pow *= 10;
        
        long num = 0;
        for (int i = 0; i < n; i++) {
            num += (s.charAt(i) - 48) * (pow);
            pow /= 10;
        }

        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < Math.max(0, s.length() - n); i++) {
            map.merge(2L, 1, Integer::sum);
            map.merge(5L, 1, Integer::sum);
        }

        for (int i = 2; (long) i * i <= num; i++) {
            while (num % i == 0) {
                map.merge((long) i, 1, Integer::sum);
                num /= i;
            }
        }
        if (num != 1) {
            map.merge(num, 1, Integer::sum);
            num /= num;
        }

        out.println(map.size());
        for (long i : map.keySet()) {
            out.println(i + " " + map.get(i));
        }

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

        double nextDouble(int radix) { return Double.parseDouble(next()); }
    }
}