import java.io.*;
import java.util.*;

public class D {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        String sec = in.next();
        Map<String, Integer> map = new HashMap<>();
        map.put("*****", 1);
        for (int i = 1; i < n; i++) {
            String nxt = in.next();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nxt.length(); j++) {
                if (nxt.charAt(j) == sec.charAt(j)) sb.append('*');
                else if (contains(nxt.charAt(j), sec)) sb.append('!');
                else sb.append('X');
            }
            map.merge(sb.toString(), 1, Integer::sum);
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            String s = in.next();
            out.println(map.getOrDefault(s, 0));
        }

    }

    static boolean contains(char c, String s) {
        for (char cs : s.toCharArray()) {
            if (cs == c) return true;
        }
        return false;
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