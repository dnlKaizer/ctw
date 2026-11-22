#include <bits/stdc++.h>

#define endl "\n"
#define ll long long

using namespace std;

const int INF = 1e9;
const int MOD = 1e9 + 7;

int x;
int calc(int c) {
    int ret = 0, v = x;
    while (v > 0) {
        v -= c--;
        ret++;
    }
    return ret;
}

void solve() {
    cin >> x;
    int ans = INF, c = 1;
    for (int i = 0; i < x; i++) {
        int v = calc(c);
        ans = min(ans, i + v);
        c++;
    }
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

