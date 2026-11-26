#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

void solve() {
    int n, m; cin >> n >> m;
    vector<vector<char>> v(n, vector<char>(m));
    for (auto &i : v) for (auto &j : i) cin >> j;

    char letters[4] = {'A', 'B', 'C', 'D'};
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (auto &l : letters) {
                if (v[i][j] == l) continue;
                if (i > 0 && v[i-1][j] == l) continue;
                if (j > 0 && v[i][j-1] == l) continue;
                v[i][j] = l;
                break;
            }
        }
    }

    for (auto &i : v) {
        for (auto &j : i) cout << j;
        cout << endl;
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}