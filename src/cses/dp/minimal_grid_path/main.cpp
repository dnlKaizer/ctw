#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    string s[n];
    for (int i = 0; i < n; i++) cin >> s[i];

    vector<vector<char>> vis(n, vector<char>(n, false));
    vector<pair<int,int>> curr;
    curr.push_back({0, 0});

    string ans;
    ans.reserve(2 * n - 1);
    ans += s[0][0];
    for (int k = 0; k < 2 * n - 2; k++) {
        char best = 'Z';
        for (const auto &[i, j] : curr) {
            if (i+1 < n) best = min(best, s[i+1][j]);
            if (j+1 < n) best = min(best, s[i][j+1]);
        }
        
        ans += best;
        vector<pair<int,int>> next;
        for (const auto &[i, j] : curr) {
            if (i+1 < n && !vis[i+1][j] && s[i+1][j] == best) {
                vis[i+1][j] = true;
                next.push_back({i+1, j});
            }
            if (j+1 < n && !vis[i][j+1] && s[i][j+1] == best) {
                vis[i][j+1] = true;
                next.push_back({i, j+1});
            }
        }
        curr = next;
    }

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}