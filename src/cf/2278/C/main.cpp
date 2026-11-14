#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    int sum = 0;
    bool gt_one_mid = false, gt_one_side = false;
    for (int i = 0; i < n; i++) {
        int val; cin >> val;
        sum += val;
        if (i == 0 || i == n-1) gt_one_side |= val > 1;
        else gt_one_mid |= val > 1;
    }

    int ans;

    if (n == 1) ans = sum;
    else if (gt_one_mid) ans = sum - 1;
    else if (gt_one_side) ans = sum - n + 1;
    else ans = -1;

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    return 0;
}