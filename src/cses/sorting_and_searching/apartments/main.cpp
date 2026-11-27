#include <bits/stdc++.h>

#define endl '\n'
#define all(x) (x).begin(), (x).end()

using namespace std;
using ll = long long;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3fLL;

void solve() {
    int n, m, k;
    cin >> n >> m >> k;
    vector<int> a(n), b(m);
    for (auto &x : a) cin >> x;
    for (auto &x : b) cin >> x;
    
    sort(all(a));
    sort(all(b));

    int ans = 0;
    for (int i = 0, j = 0; i < n && j < m;) {
        if (b[j] >= a[i] - k && b[j] <= a[i] + k) {
            ans++;
            i++;
            j++;
        } else if (a[i] - k < b[j]) {
            i++;
        } else {
            j++;
        }
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