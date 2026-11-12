#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3f;

void solve() {
    vector<int> v(3);
    for (auto &x : v) cin >> x;
    sort(v.begin(), v.end());

    bool ans = false;
    if (v[0]+1 == v[1] && v[1]+1 == v[2]) ans = true;

    cout << (ans ? "YES" : "NO") << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    
    return 0;
}