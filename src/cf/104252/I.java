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
        int r = in.nextInt(), c = in.nextInt();
        int[][] mat = new int[r][c];
        Dish[] dishes = new Dish[r * c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int v = in.nextInt();
                mat[i][j] = v;
                dishes[i * c + j] = new Dish(v, i, j);
            }
        }

        Arrays.sort(dishes);
        boolean[][] visitedEarly = new boolean[r][c];

        int ans = 0;
        for (Dish d : dishes) {
            int count = 0, currV = d.v;
            if (visitedEarly[d.i][d.j]) continue;

            boolean[][] visited = new boolean[r][c];
            PriorityQueue<Dish> pq = new PriorityQueue<>();
            pq.add(new Dish(d.v, d.i, d.j));

            while (!pq.isEmpty()) {
                Dish curr = pq.poll();
                if (curr.v < currV) continue;

                currV = curr.v;
                count++;
                for (Dish next : neighbors(mat, visited, curr)) {
                    pq.add(next);
                    visited[next.i][next.j] = true;
                    visitedEarly[next.i][next.j] = true;
                }
            }

            ans = Math.max(ans, count);
        }
        out.println(ans);
    }

    static List<Dish> neighbors(int[][] mat, boolean[][] visited, Dish d) {
        int v = d.v;
        int i = d.i;
        int j = d.j;

        int r = mat.length;
        int c = mat[0].length;

        List<Dish> ret = new ArrayList<>();
        for (int k = -1; k <= 1; k+=2) {
            for (int l = 0; l <= 1; l++) {
                int ni = i + (k * (l^1));
                int nj = j + (k * l);

                if (ni < 0 || ni >= r || nj < 0 || nj >= c || mat[ni][nj] < v || visited[ni][nj]) continue;

                ret.add(new Dish(mat[ni][nj], ni, nj));
            }
        }

        return ret;
    } 

    static record Dish(int v, int i, int j) implements Comparable<Dish> {
        public int compareTo(Dish d) {
            return Integer.compare(v, d.v);
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