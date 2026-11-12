#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main () {
    int t; cin >> t;
    while (t-- > 0) {
        int n; cin >> n;
        ll ans = 0;
        vector<int> odd;
        for (int i = 0; i < n; i++) {
            int x; cin >> x;
            if (x % 2 == 0) ans += x;
            else odd.push_back(x);
        }

        if (odd.size() == 0) {
            cout << 0 << endl;
            continue;
        }

        sort(odd.begin(), odd.end());

        int l = 0, r = odd.size()-1;
        while (l < r) {
            ans += odd[r];
            l++; r--;
        }
        if (l == r) ans += odd[l];

        cout << ans << endl;
    }

    return 0;
}