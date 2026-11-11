#include <bits/stdc++.h>

using namespace std;

int main() {
    int t; cin >> t;
    while (t-- > 0) {
        int n, m;
        cin >> n >> m;

        int ans = 0;
        int befT = 0, befS = 0;
        for (int i = 0; i < n; i++) {
            int a, b;
            cin >> a >> b;

            int dist = a - befT;
            int signal = dist % 2;

            if ((befS ^ signal) == b) ans += dist;
            else ans += dist - 1;
            
            befT = a; befS = b;
        }

        if (befT < m) ans += m - befT;

        cout << ans << endl;
    }

    return 0;
}