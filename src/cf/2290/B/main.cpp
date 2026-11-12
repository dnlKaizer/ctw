#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3f;

void solve() {
    int n; cin >> n;
    string s; cin >> s;

    int curr = 0, cnt = 0;
    for (int i = 0; i < n; i++) {
        int val = s[i] == 'R' ? 0 : 1;
        if (curr != val) {
            cnt++;
            curr = val;
        }
        curr ^= 1;
    }

    cout << cnt << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    
    return 0;
}