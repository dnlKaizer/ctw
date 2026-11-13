#include <bits/stdc++.h>
#define pb push_back
#define f first
#define s second

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;

void solve() {
    int n; cin >> n;
    vector<int> w(n+1);
    for (int i = 1; i <= n; i++) cin >> w[i];

    vector<vector<int>> adj(n+1);
    for (int i = 1; i < n; i++) {
        int u, v; cin >> u >> v;
        adj[u].pb(v);
        adj[v].pb(u);
    }

    vector<int> cnt(n+1, 0);
    vector<int> dist(n+1, -1);
    deque<int> deque;
    for (int i = 1; i <= n; i++) {
        if (w[i]) {
            deque.pb(i);
            dist[i] = 0;
            cnt[i] = 1;
        }
    }
    
    ll odd_count = 0;
    int d = INF;
    while (!deque.empty()) {
        int u = deque.front();
        deque.pop_front();

        if (dist[u] >= (d+1) / 2) continue;

        for (const int &v : adj[u]) {
            if (dist[v] == -1) {
                dist[v] = dist[u] + 1;
                cnt[v] = cnt[u];
                deque.pb(v);
            } else if (dist[v] == dist[u] + 1) { // Distância Par
                d = min(d, 2 * dist[v]);
                cnt[v]++;
            } else if (dist[v] == dist[u]) { // Distância Ímpar
                d = min(d, 2 * dist[v] + 1);
                if (u < v) odd_count++;
            }
        }
    }

    ll ans = 0;
    if (d % 2 == 1) {
        ans = odd_count;
    } else {
        for (int i = 1; i <= n && d % 2 == 0; i++) {
            if (dist[i] == d/2) {
                ans += 1LL * (cnt[i] - 1) * cnt[i] / 2;
            }
        }
    }

    cout << d << " " << ans << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}