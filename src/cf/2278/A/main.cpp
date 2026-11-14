#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n, m; cin >> n >> m;
    string a, b; cin >> a >> b;

    b += "six";

    sort(a.begin(), a.end());
    sort(b.begin(), b.end());

    cout << (a == b ? "YES" : "NO") << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    return 0;
}