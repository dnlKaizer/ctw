#include <bits/stdc++.h>

using namespace std;

// Solução Padrão

// void solve() {
//     int n, x; cin >> n >> x;
//     vector<int> h(n+1), s(n+1);
//     for (int i = 1; i <= n; i++) cin >> h[i];
//     for (int i = 1; i <= n; i++) cin >> s[i];

//     vector<vector<int>> dp(n+1, vector<int>(x+1, 0));
//     for (int i = 1; i <= n; i++) {
//         for (int j = 1; j <= x; j++) {
//             dp[i][j] = dp[i-1][j];
//             if (h[i] <= j) {
//                 dp[i][j] = max(dp[i][j], dp[i-1][j-h[i]] + s[i]);
//             }
//         }
//     }

//     cout << dp[n][x] << endl;
// }


// Solução Otimizada

void solve() {
    int n, x; cin >> n >> x;
    vector<int> h(n+1), s(n+1);
    for (int i = 1; i <= n; i++) cin >> h[i];
    for (int i = 1; i <= n; i++) cin >> s[i];

    vector<int> dp(x+1, 0);
    for (int i = 1; i <= n; i++) {
        for (int j = x; j >= 0; j--) {
            if (h[i] <= j) {
                dp[j] = max(dp[j], dp[j-h[i]] + s[i]);
            }
        }
    }

    cout << dp[x] << endl;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(0);
    solve();
    return 0;
}