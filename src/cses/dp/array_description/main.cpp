#include <bits/stdc++.h>

using namespace std;

const int MOD = 1'000'000'007;

typedef long long ll;

void solve() {
    int n, m; cin >> n >> m;
    vector<int> x(n);
    for (int i = 0; i < n; i++) { cin >> x[i]; }

    vector<vector<int>> dp(n, vector<int>(m+2, 0));
    if (x[0] != 0) {
        dp[0][x[0]] = 1;
    } else for (int v = 1; v <= m; v++) {
        dp[0][v] = 1;
    }

    for (int i = 1; i < n; i++) {
        int l = x[i] == 0 ? 1 : x[i];
        int r = x[i] == 0 ? m : x[i];
        for (int v = l; v <= r; v++) {
            dp[i][v] = ((ll)dp[i-1][v-1] + dp[i-1][v] + dp[i-1][v+1]) % MOD;
        }
    }

    ll ans = 0;
    for (int v = 1; v <= m; v++) {
        ans = (ans + dp[n-1][v]) % MOD;
    }
    
    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}