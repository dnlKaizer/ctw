#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

vector<vector<char>> board(8, vector<char>(8));
int ans = 0;

bool valid(vector<int> &v) {
    for (int i = 0; i < 7; i++) {
        for (int j = i+1; j < 8; j++) {
            if (abs(v[j] - v[i]) == j - i) return false;
        }
    }
    return true;
}

void fill(int i, vector<int> &v, set<int> &used) {
    if (i == 8) {
        ans += valid(v) ? 1 : 0;
        return;
    }

    for (int j = 0; j < 8; j++) {
        if (used.contains(j) || !board[i][j]) continue;
        v[i] = j;
        used.insert(j);
        fill(i+1, v, used);
        used.erase(j);
    }
}

void solve() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            char c; cin >> c;
            board[i][j] = c == '.';
        }
    }

    set<int> used;
    vector<int> v(8);

    fill(0, v, used);

    cout << ans << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}