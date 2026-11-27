#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;

void solve() {
    int n, m; cin >> n >> m;
    vector<int> t(m);
    multiset<int> h;
    for (int i = 0; i < n; i++) {
        int v; cin >> v;
        h.insert(v);
    }
    for (auto &x : t) cin >> x;

    for (int i = 0; i < m; i++) {
        auto it = h.upper_bound(t[i]);
        if (it == h.begin()) {
            cout << -1 << endl;
        } else {
            --it;
            cout << *it << endl;
            h.erase(it);
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t; cin >> t;
    // while (t--)
    solve();
    return 0;
}