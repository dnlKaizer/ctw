#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

typedef long long ll;

void solve() {
    string s; cin >> s;
    int n = s.size();

    vector<int> freq(26, 0);
    for (auto &c : s) freq[c - 'A']++;

    int max_f = *max_element(freq.begin(), freq.end());
    if (max_f > (n + 1) / 2) {
        cout << -1 << endl;
        return;
    }
    
    string ans;
    ans.reserve(n);

    char last = '&';
    for (int i = 0; i < n; i++) {
        int curr = n - i;

        for (int c = 0; c < 26; c++) {
            if (freq[c] == 0 || ('A' + c) == last) continue;

            freq[c]--;
            max_f = *max_element(freq.begin(), freq.end());

            if (max_f <= curr / 2) {
                last = 'A' + c;
                ans.push_back(last);
                break;
            }

            freq[c]++;
        }
    }

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    // int t; cin >> t;
    // while (t--)
        solve();
    return 0;
}