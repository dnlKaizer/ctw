#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;

void solve() {
    int n, x, y;
    cin >> n >> x >> y;
    vector<int> v, p;
    int mn = INF, mn_idx = 0;
    for (int i = 0; i < n; i++) {
        int val; cin >> val;
        if (i >= x && i < y) {
            if (val < mn) {
                mn = val;
                mn_idx = (int) p.size();
            }
            p.push_back(val);
        } else { 
            v.push_back(val);
        }
    }
    int m = p.size();
    for (int i = 0; i < m; i++) p.push_back(p[i]);

    int i = 0, j = mn_idx;
    while (i < n-m && v[i] < p[j]) cout << v[i++] << " ";
    while (j - mn_idx < m) cout << p[j++] << " ";
    while (i < n-m) cout << v[i++] << " ";
    
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}