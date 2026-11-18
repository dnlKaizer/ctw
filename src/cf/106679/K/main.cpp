#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    vector<int> c(n), k(n);
    int c_sum = 0;
    for (int &a : c) {
        cin >> a;
        c_sum += a;
    }
    for (int &a : k) {
        cin >> a;
    }

    int ans = -1;
    for (int i = 0; i < n; i++) {
        if (c[i] < k[i]) {
            ans = -1;
            break;
        }
        ans = max(ans, c_sum - c[i] + k[i]);
    }
    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}