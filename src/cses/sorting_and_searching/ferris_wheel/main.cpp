#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;

void solve() {
    int n, x;
    cin >> n >> x;
    vector<int> p(n);
    for (auto &a : p) cin >> a;

    sort(all(p));

    int ans = 0;
    int i = 0, j = n-1;
    while (i <= j) {
        if (p[i] + p[j] <= x) i++;
        j--;
        ans++;
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