import java.io.*;
import java.util.*;

public class M {
    static final int INF = 0x3f3f3f3f;
    static final long LINF = 0x3f3f3f3f3f3f3f3fL;
    static final long MOD = 1_000_000_007L;
    static final long MOD9 = 998_244_353L;

    static FastScanner in = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void run() {
        int n = in.nextInt(), m = in.nextInt();
        int p = in.nextInt(), g = in.nextInt();

        List<Edge>[] adj = new List[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt(), d = in.nextInt();
            adj[u].add(new Edge(v, d));
            adj[v].add(new Edge(u, d));
        }

        List<Integer> ans = dijkstra(n, adj, p, g);
        if (ans == null || ans.size() == 0) {
            out.println("*");
        } else {
            for (int u : ans) {
                out.print(u + " ");
            }
            out.println();
        }

    }

    static List<Integer> dijkstra(int n, List<Edge>[] adj, int p, int g) {
        long[] dist = new long[n+1];
        Arrays.fill(dist, LINF);

        boolean[] visitG = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.add(new Node(p, 0));
        dist[p] = 0;
        visitG[g] = true;

        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int u = temp.i;
            long d = temp.d;

            if (d > dist[u]) continue;

            for (Edge e : adj[u]) {
                int v = e.i;
                
                if (d + e.d < dist[v]) {
                    dist[v] = d + e.d;
                    pq.add(new Node(v, d + e.d));
                    if (v != g) visitG[v] = visitG[u];
                } else if (d + e.d == dist[v] && v != g) {
                    visitG[v] &= visitG[u];
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == dist[g] * 2 && visitG[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    static record Edge(int i, int d) {}

    static record Node(int i, long d) implements Comparable<Node> {
        public int compareTo(Node o) {
            return Long.compare(d, o.d);
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

        double nextDouble(int radix) { return Double.parseDouble(next()); }
    }
}