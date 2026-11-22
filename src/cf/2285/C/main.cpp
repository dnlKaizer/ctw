#include <bits/stdc++.h>

using namespace std;

const int MOD = 1'000'000'007;

typedef long long ll;

ll modPow(ll base, ll exp) {
    ll resultado = 1;
    base = ((base % MOD) + MOD) % MOD;
    while (exp > 0) {
        if (exp & 1) {
            resultado = resultado * base % MOD;
        }
        base = base * base % MOD;
        exp >>= 1;
    }

    return resultado;
}

void solve() {
    int n; cin >> n;
    vector<ll> v(n);
    for (auto &x : v) cin >> x;

    sort(v.rbegin(), v.rend());

    ll ans = 0;
    for (int i = 0; i < n; i++) {
        if (i < n-1 && v[i] == v[i+1]) break;

        ll val = v[i] - 1;
        ans = (ans + val * modPow(val, i)) % MOD;
    }

    cout << (ans + 1) % MOD << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}