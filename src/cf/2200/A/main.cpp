#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    int cnt = 0, mx = -1;
    for (int i = 0; i < n; i++) {
        int a; cin >> a;
        if (a > mx) {
            cnt = 1;
            mx = a;
        } else if (a == mx) {
            cnt++;
        }
    }  

    cout << cnt << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}