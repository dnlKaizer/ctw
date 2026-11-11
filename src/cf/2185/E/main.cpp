#include <bits/stdc++.h>

using namespace std;

int main() {
    int t; cin >> t;
    while (t-- > 0) {
        int n, m, k;
        cin >> n >> m >> k;

        vector<int> a(n);
        for (auto &x : a) cin >> x;
        
        vector<int> b(m);
        for (auto &x : b) cin >> x;
        sort(b.begin(), b.end());

        vector<vector<int>> count(2 * k + 1);
        for (int i = 0; i < n; i++) {
            int x = a[i];
            auto it = lower_bound(b.begin(), b.end(), x);

            if (it != b.end()) {
                int diff_sup = *it - x;
                int idx = diff_sup + k;
                if (idx >= 0 && idx <= 2*k) count[diff_sup + k].push_back(i);
            }
            
            if (it != b.begin()) {
                int diff_inf = *prev(it) - x;
                int idx = diff_inf + k;
                if (idx >= 0 && idx <= 2*k) count[diff_inf + k].push_back(i);
            }
        }
        
        vector<bool> visited(n, false);
        string s; cin >> s;
        int curr = 0, ans = n;
        int mn = 0, mx = 0;
        for (int i = 0; i < k; i++) {
            if (s[i] == 'L') curr--;
            else curr++;

            if (curr < mn) {
                mn = curr;
                for (auto& j : count[curr + k]) {
                    if (!visited[j]) {
                        visited[j] = true;
                        ans--;
                    }
                }
            } else if (curr > mx) {
                mx = curr;
                for (auto& j : count[curr + k]) {
                    if (!visited[j]) {
                        visited[j] = true;
                        ans--;
                    }
                }
            }

            cout << ans << " ";
        }

        cout << endl;
    }

    return 0;
}