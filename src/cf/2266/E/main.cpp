#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const ll LINF = 0x3f3f3f3f3f3f3f3f;
const int MAX = 200'005;
ll dp[MAX];

int n, k;

void calc(int x) {
    int v = x;
    if (x <= k) return;
    
    dp[x] = LINF;
    for (int p = 2; p * p <= v; p++) {
        while (v % p == 0) {
            dp[x] = min(dp[x], 1 + p * dp[x / p]);
            v /= p;
        }
    }
    if (v > 1) dp[x] = min(dp[x], 1 + v * dp[x / v]);
}

void compute() {
    for (int i = 0; i <= k; i++) dp[i] = 0;
    
    for (int i = k + 1; i <= n; i++) calc(i);
    
}

void solve() {
    cin >> n >> k;
    compute();
    
    ll ans = 0;
    for (int i = 0; i < n; i++) {
        int v; cin >> v;
        ans += dp[v];
    }
    
    cout << ans << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}