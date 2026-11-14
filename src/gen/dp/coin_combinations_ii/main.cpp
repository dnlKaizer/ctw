#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;
const int MOD = 1'000'000'007;

void solve() {
    int n, x; cin >> n >> x;

    vector<int> coins(n);
    for (int &c : coins) cin >> c;

    vector<ll> dp(x+1, 0);
    dp[0] = 1;
    for (int c : coins) {
        for (int i = 1; i <= x; i++) {
            if (i - c >= 0) {
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