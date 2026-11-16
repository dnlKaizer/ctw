#include <bits/stdc++.h>

using namespace std;

const int MOD = 1'000'000'007;
const int MAX = 1'000'000;

typedef long long ll;

int dp[MAX+1];

void calc() {
    int one = 1, two = 1, nxt_one, nxt_two;
    for (int i = 1; i <= MAX; i++, one = nxt_one, two = nxt_two) {
        dp[i] = (one + two) % MOD;
        nxt_one = (4LL * one + two) % MOD;
        nxt_two = (2LL * two + one) % MOD;
    }
}

void solve() {
    int n; cin >> n;
    cout << dp[n] << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    calc();
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}