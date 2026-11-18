#include <bits/stdc++.h>

using namespace std;

void solve() {
    int t, c, k; cin >> t >> c >> k;
    cout << min(c, t * k) << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}