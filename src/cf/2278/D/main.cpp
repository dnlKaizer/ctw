#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;

void solve() {
    int n; cin >> n;
    vector<ll> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    sort(a.begin(), a.end());

    ll ans = 0, diff = a[n-1] - a[0];
    for(int i = 0; i < n - 1; i++){
        ans += (a[i + 1] - a[i]) * min(i + 1, n - i - 1);
    }

    cout << ans << " " << diff << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    return 0;
}