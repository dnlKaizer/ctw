#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int MOD = 1'000'000'007;

void solve() {
    int n, x; cin >> n >> x;
    vector<int> coins(n);
    for (auto &c : coins) cin >> c;

    vector<ll> dp(x+1, 0);
    dp[0] = 1;
    for (int i = 1; i <= x; i++) {
        for (int &c : coins) {
            if (i-c >= 0) {
                dp[i] += dp[i-c];
                dp[i] %= MOD;
            }
        }
    }

    cout << dp[x] << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}