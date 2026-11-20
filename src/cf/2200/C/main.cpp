#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    string s; cin >> s;

    deque<char> dq;
    for (int i = 0; i < n; i++) {
        if (dq.empty() || s[i] != dq.back()) {
            dq.push_back(s[i]);
        } else {
            dq.pop_back();
        }
    }

    cout << (dq.empty() ? "YES" : "NO") << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}