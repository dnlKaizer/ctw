#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;

void solve() {
    int n, x; cin >> n >> x;
    vector<int> a(n), aux(n);
    for (auto &c : a) cin >> c;

    aux = a;

    sort(all(a));

    int l = 0, r = n-1;
    while (l < r) {
        if (a[l] + a[r] < x) l++;
        else if (a[l] + a[r] > x) r--;
        else break;
    }

    if (l >= r) {
        cout << "IMPOSSIBLE" << endl;
        return;
    }

    int f = -1, s = -1;
    for (int i = 0; i < n; i++) {
        if (f == - 1 && aux[i] == a[l]) {
            f = i+1;
        } else if (s == -1 && aux[i] == a[r]) {
            s = i+1;
        }
    }

    cout << f << " " << s << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t; cin >> t;
    // while (t--)
    solve();
    return 0;
}