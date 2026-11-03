import java.util.*;
import java.io.*;

public class J {
    static final int INF = 0x3f3f3f3f;

    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String s = in.next();
        int n = s.length();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i+2 <= n && s.substring(i, i+2).equals("ha")) ans++;
            if (i+5 <= n && s.substring(i, i+5).equals("boooo")) ans--;
            if (i+5 <= n && s.substring(i, i+5).equals("bravo")) ans+=3;
        }
        out.println(ans);

        out.flush();
    }

    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}