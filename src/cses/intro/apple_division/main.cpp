#include <bits/stdc++.h>

#define endl "\n"

typedef long long ll;

using namespace std;

const ll INF = 0x3f3f3f3f3f3f3f3f;

void solve() {
    int n; cin >> n;
    vector<int> a(n);
    for (auto &x : a) cin >> x;

    ll ans = INF;
    for (int i = 0; i < (1 << n); i++) {
        ll cnt[2] = {};
        for (int j = n-1; j >= 0; j--) {
            int idx = (i >> j) & 1;
            cnt[idx] += a[j];
        }
        ans = min(ans, abs(cnt[0] - cnt[1]));
    }
    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}