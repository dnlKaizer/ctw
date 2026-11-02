import java.io.*;
import java.util.*;

public class K {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int k = in.nextInt();
        String s = in.next();

        if (k == 2) resolveK2(s);
        else resolve(s, k);
    }

    static void resolveK2(String s) {
        int n = s.length();
        int cnt1 = 0, cnt2 = 0, signal = 0;
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - 48;
            if (curr != signal) cnt1++;
            signal ^= 1;
        }
        signal = 1;
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - 48;
            if (curr != signal) cnt2++;
            signal ^= 1;
        }
        signal = 0;
        if (cnt1 > cnt2) signal = 1;

        out.print(Math.min(cnt1, cnt2) + " ");
        for (int i = 0; i < n; i++) {
            out.print(signal);
            signal ^= 1;
        }
        out.println();
    }

    static void resolve(String s, int k) {
        int n = s.length();
        List<Integer> count = new ArrayList<>();
        int first = s.charAt(0) - 48;
        char last = s.charAt(0);
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c != last) {
                count.add(cnt);
                cnt = 1;
                last = c;
            } else cnt++;
        }
        count.add(cnt);

        StringBuilder sb = new StringBuilder();
        cnt = 0;
        int curr = first;
        for (int c : count) {
            int idx = -1;
            if (c >= k) idx = k-1;
            for (int i = 0; i < c; i++) {
                if (i == c-2 && idx == c-1) idx--;

                if (i == idx) {
                    cnt++;
                    sb.append(curr ^ 1);
                    idx += k;
                } else {
                    sb.append(curr);
                }
            }
            curr ^= 1;
        }

        out.println(cnt + " " + sb.toString());
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