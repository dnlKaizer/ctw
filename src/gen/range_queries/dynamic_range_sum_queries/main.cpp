#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int MAX = 2e5 + 10;
ll bit[MAX];
int n, q;

void add(int i, ll val) {
    for (; i <= n; i += i & -i) bit[i] += val;
}

ll query(int i) {
    ll s = 0;
    for (; i > 0; i -= i & -i) s += bit[i];
    return s;
}

void solve() {
    cin >> n >> q;
    for (int i = 1; i <= n; i++) {
        ll x; cin >> x;
        add(i, x);
    }

    for (int i = 0; i < q; i++) {
        int t, a, b; cin >> t >> a >> b;
        if (t == 2) cout << query(b) - query(a-1) << endl;
        else add(a, b - (query(a) - query(a-1)));
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}