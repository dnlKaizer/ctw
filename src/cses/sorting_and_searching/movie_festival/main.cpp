#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;


void solve() {
    int n; cin >> n;
    vector<pair<int,int>> m(n);
    for (int i = 0; i < n; i++) {
        int a, b; cin >> a >> b;
        m[i] = {a, b};
    }

    sort(all(m));

    int ans = 0, end = -1;
    for (int i = 0; i < n; i++) {
        auto [s, e] = m[i];
        if (s >= end) {
            end = e;
            ans++;
        }
        end = min(end, e);
    }

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t; cin >> t;
    // while (t--)
    solve();
    return 0;
}