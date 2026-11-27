#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;

void solve() {
    int n; cin >> n;
    vector<ll> pref(n+1, 0);
    for (int i = 1; i <= n; i++) {
        cin >> pref[i];
        pref[i] += pref[i-1];
    }

    ll ans = -INF, big = pref[n];
    for (int i = n-1; i >= 0; i--) {
        ans = max(ans, big - pref[i]);
        big = max(big, pref[i]);
    }

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t; cin >> t;
    // while (t--)
    solve();
    return 0;
}