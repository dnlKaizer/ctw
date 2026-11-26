#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

void solve() {
    int n; cin >> n;
    int dist[n][n];
    for (auto &i : dist) for (auto &j : i) j = -1;

    int dx[8] = {1, 2, -1, 2, 1, -2, -1, -2};
    int dy[8] = {2, 1, 2, -1, -2, 1, -2, -1};

    queue<pair<int, int>> q;
    q.push({0, 0});
    dist[0][0] = 0;

    while (!q.empty()) {
        auto [i, j] = q.front();
        q.pop();

        for (int d = 0; d < 8; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];

            if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;

            if (dist[ni][nj] == -1) {
                dist[ni][nj] = dist[i][j] + 1;
                q.push({ni, nj});
            }
        }
    }

    for (auto &i : dist) {
        for (auto &j : i) cout << j << " ";
        cout << endl;
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}