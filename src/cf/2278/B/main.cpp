#include <bits/stdc++.h>

using namespace std;

void solve() {
    int x, y, z;
    cin >> x >> y >> z;
    
    char ans = 'S';
    int mx = max(max(x, y), z);
    if (mx == x) {
        ans = 'P';
    } else if (mx == y) {
        ans = 'R';
    }

    for (int i = 0; i < x+y+z; i++) cout << ans;
    cout << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    return 0;
}