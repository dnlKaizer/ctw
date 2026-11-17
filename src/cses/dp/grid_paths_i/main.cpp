#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int MOD = 1'000'000'007;

void solve() {
    int n; cin >> n;
    vector<int> dp(n, 0);
    dp[0] = 1;

    for (int i = 0; i < n; i++) {
        string s; cin >> s;
        for (int j = 0; j < n; j++) {
            if (s[j] == '*') {
                dp[j] = 0;
            } else if (j > 0) {
                dp[j] += dp[j-1];
                dp[j] %= MOD;
            }
        }
    }

    cout << dp[n-1] << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}