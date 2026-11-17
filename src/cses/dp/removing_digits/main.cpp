#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;

void solve() {
    int n; cin >> n;
    vector<int> dp(n+1, INF);
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
        for (int v = i; v > 0; v /= 10) {
            dp[i] = min(dp[i], dp[i-(v%10)] + 1);
        }
    }
    cout << dp[n] << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}