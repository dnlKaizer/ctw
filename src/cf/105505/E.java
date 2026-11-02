import java.io.*;
import java.util.*;

public class E {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final int MOD = 1_000_000_007;
    static final int MOD9 = 998_244_353;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean[] visited = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            visited[a[i]] = true;
        }
        int[] pref = new int[n], suff = new int[n];
        pref[0] = -1;
        suff[n-1] = -1;
        for (int i = 1; i < n; i++) {
            if (a[i-1] == 0) pref[i] = pref[i-1];
            else pref[i] = i-1;
        }
        for (int i = n-2; i >= 0; i--) {
            if (a[i+1] == 0) suff[i] = suff[i+1];
            else suff[i] = i+1;
        }

        boolean possible = true;
        int l = 0, r = n-1;
        int curr = 1;
        while (l <= r) {
            if (a[l] == curr) {
                l++;
                curr++;
                continue;
            }
            if (a[r] == curr) {
                r--;
                curr++;
                continue;
            }
            
            if (visited[curr]) {
                possible = false;
                break;
            }

            boolean canL = a[l] == 0;
            boolean canR = a[r] == 0;

            if (!canL && !canR) {
                possible = false;
                break;
            }

            if (!canR) {
                a[l++] = curr++;
                continue;
            } else if (!canL) {
                a[r--] = curr++;
                continue;
            }

            int idxL = (suff[l] != -1 && suff[l] < r) ? suff[l] : -1;
            int idxR = (pref[r] != -1 && pref[r] > l) ? pref[r] : -1;

            if ((idxL == -1 && idxR == -1) || idxR == -1) {
                a[l++] = curr++;
                continue;
            } else if (idxL == -1) {
                a[r--] = curr++;
                continue;
            }

            int valL = a[idxL];
            int valR = a[idxR];

            if (valL < valR) {
                a[l++] = curr++;
            } else if (valR < valL) {
                a[r--] = curr++;
            } else {
                if (idxL - l < r - idxR) {
                    a[l++] = curr++;
                } else {
                    a[r--] = curr++;
                }
            }
        }

        if (!possible) out.print("*");
        else for (int i = 0; i < n; i++) out.print(a[i] + " ");

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