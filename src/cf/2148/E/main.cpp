#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main() {
    int t; cin >> t;
    while (t-- > 0) {
        int n, k;
        cin >> n >> k;

        vector<int> a(n);
        vector<int> count(n+1);
        for (int i = 0; i < n; i++) {
            int x; cin >> x;
            a[i] = x;
            count[x]++;
        }

        bool possible = true;
        for (auto& x : count) {
            if (x % k != 0) {
                possible = false;
                break;
            }
            x /= k;
        }

        if (!possible) {
            cout << 0 << endl;
            continue;
        }

        vector<int> currCount(n+1);
        ll ans = 0;
        int l = 0, r = 0;
        while (l < n) {
            ll currAns = 0;

            while (r < n && currCount[a[r]] + 1 <= count[a[r]]) {
                currCount[a[r]]++;
                r++;
                currAns += r - l;
            }

            ans += currAns;
            currCount[a[l]]--;
            l++;
        }

        cout << ans << endl;
        
    }

    return 0;
}