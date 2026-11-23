#include <bits/stdc++.h>

using namespace std;

const int MOD = 998'244'353;
const int MAX = 20'000;

int factorial[MAX];

void compute() {
    factorial[0] = 1;
    factorial[1] = 1;
    for (int i = 2; i < MAX; i++) {
        factorial[i] = (1LL * i * factorial[i-1]) % MOD;
    }
}

int modPow(int base, int exp) {
    int ret = 1;
    base %= MOD;
    while (exp > 0) {
        if (exp & 1) {
            ret = (1LL * ret * base) % MOD;
        }
        base = (1LL * base * base) % MOD;
        exp >>= 1;
    }
    return ret;
}

int inv(int v) {
    return modPow(v, MOD - 2);
}

int comb(int v, int h) {
    int ret = factorial[v+h];
    ret = (1LL * ret * inv(factorial[v])) % MOD;
    ret = (1LL * ret * inv(factorial[h])) % MOD;
    return ret;
}

int calc(int n, int k) {
    vector<int> dp(n+2, 1);
    dp[0] = 0;
    dp[n+1] = 0;
    for (int x = 1; x <= k; x++) {
        for (int i = 1; i <= n; i++) {
            // dp[i-1] === dp[i-1][x]
            // dp[i+1] === dp[i+1][x-1]
            dp[i] = (dp[i-1] + dp[i+1]) % MOD;
        }
    }
    return dp[n];
}

void solve() {
    int r, c, u, l;
    cin >> r >> c >> u >> l;

    int v = r - 1 + 2 * u, h = c - 1 + 2 * l;

    int v_ways = calc(r, u);
    int h_ways = calc(c, l);
    int com = comb(v, h);

    int ans = (1LL * v_ways * h_ways) % MOD; 
    ans = (1LL * ans * com) % MOD;

    cout << ans << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    compute();
    solve();
    return 0;
}