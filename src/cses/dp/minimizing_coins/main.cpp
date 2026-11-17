#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;

void solve() {
    int n, x; cin >> n >> x;
    vector<int> coins(n);
    for (auto &c : coins) cin >> c;

    vector<int> dp(x+1, INF);
    dp[0] = 0;

    for (int c : coins) {
        for (int i = c; i <= x; i++) {
            dp[i] = min(dp[i], dp[i - c] + 1);
        }
    }

    cout << (dp[x] == INF ? -1 : dp[x]) << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}