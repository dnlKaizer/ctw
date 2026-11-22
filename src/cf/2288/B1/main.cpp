#include <bits/stdc++.h>

#define endl "\n"
#define ll long long

using namespace std;

const int INF = 1e9;
const int MOD = 1e9 + 7;

void solve() {
    int x, y, m; cin >> x >> y >> m;
    string s; cin >> s;

    int ans = 0;
    for (int i = 0; i < m; i++) {
        if (y == 0 && x == 0) break;
        ans = max(x + y, ans);
        
        if (s[i] == '+') {
            if (y == 0) x--;
            y++;
        } else if (x == 0 || y > 1) {
            y--;
            x++;
        } else {
            x--;
        }
    }
    ans = max(x + y, ans);

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

