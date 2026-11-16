#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

void solve() {
    int n; cin >> n;
    vector<int> even((n+1) / 2), odd(n / 2);
    for (int i = 0; i < n; i++) {
        int val; cin >> val;
        if (i % 2 == 0) even[i/2] = val;
        else odd[i/2] = val;
    }

    sort(even.begin(), even.end());
    sort(odd.begin(), odd.end());

    for (int i = 0; i < n / 2; i++) {
        if (-(even[i] + odd[i]) > even[i] + odd[i]) {
            int temp = even[i];
            even[i] = -odd[i];
            odd[i] = -temp;
        } else {
            break;
        }
    }

    ll ans = 0;
    for (auto &x : even) ans += x;
    for (auto &x : odd) ans += x;

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    return 0;
}