#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;

int main() {

    int t; cin >> t;
    while (t-- > 0) {
        int n; cin >> n;
        vector<int> v(n);
        for (auto &x : v) cin >> x;
        sort(v.begin(), v.end());

        int ans = 0, bef = -INF, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == bef + 1) {
                cnt++;
            } else if (v[i] != bef) {
                ans = max(ans, cnt);
                cnt = 1;
            }
            bef = v[i];
        }

        ans = max(ans, cnt);

        cout << ans << endl;
    }

    return 0;
}