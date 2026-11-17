#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int MOD = 1'000'000'007;
const int MAX = 1'000'010;
ll dp[MAX];

void solve() {
    int n; cin >> n;
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
        ll sum = 0;
        for (int j = 1; j <= 6; j++) {
            if (i-j >= 0) {
                sum += dp[i-j];
                sum %= MOD;
            }
        }
        dp[i] = sum;
    }
    cout << dp[n] << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}