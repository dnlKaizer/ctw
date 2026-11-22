#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    if (n < 4) {
        cout << -1 << "\n";
        return;
    }
    for (int i = 1; i <= n; i++) {
        if (i == 1) cout << 4 << " ";
        else if (i == 4) cout << 1 << " ";
        else cout << i << " ";
    }
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}