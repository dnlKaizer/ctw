#include <bits/stdc++.h>

#define endl "\n"
#define ll long long

using namespace std;

const int INF = 1e9;
const int MOD = 1e9 + 7;

void solve() {
    int n, x; cin >> n >> x;
    vector<ll> a(n);
    for (auto &x : a) cin >> x;

    sort(a.begin(), a.end());
    ll k = 1, ans = 0;
    for (int i = 0; i < n-1; i++) {
        if (a[i] > k) {
            ll div = (a[i] - k) / x;
            ans += div;
            k += div * x;
        } 
        k += x;
    }

    cout << ans << endl;
}

int main(void){
    // freopen("measurement.in", "r", stdin);
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while(t--)
        solve();
    // freopen("measurement.out","w", stdout);
    return 0;
}

