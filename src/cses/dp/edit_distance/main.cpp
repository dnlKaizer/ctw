#include <bits/stdc++.h>

using namespace std;

// Solução padrão

// void solve() {
//     string a, b; cin >> a >> b;
//     int n = a.size(), m = b.size();
//     vector<vector<int>> dp(n+1, vector<int>(m+1, 0));
//     for (int i = 0; i <= n; i++) dp[i][0] = i;
//     for (int i = 0; i <= m; i++) dp[0][i] = i;
    
//     for (int i = 1; i <= n; i++) {
//         for (int j = 1; j <= m; j++) {
//             dp[i][j] = dp[i-1][j-1];
//             if (a[i-1] != b[j-1]) {
//                 dp[i][j] = 1 + min({dp[i][j], dp[i-1][j], dp[i][j-1]});
//             }
//         }
//     }

//     cout << dp[n][m] << "\n";
// }


// Solução Otimizada

void solve() {
    string a, b; cin >> a >> b;
    int n = a.size(), m = b.size();
    if (n < m) {
        int t = n; n = m; m = t;
        string ts = a; a = b; b = ts;
    }
    
    vector<int> dp(m+1);
    iota(dp.begin(), dp.end(), 0);
    for (int i = 1; i <= n; i++) {
        int prev = dp[0];
        dp[0] = i;
        for (int j = 1; j <= m; j++) {
            // prev    === dp[i-1][j-1]
            // dp[j-1] === dp[i][j-1]
            // dp[j]   === dp[i-1][j]
            int temp = dp[j];
            dp[j] = prev;
            if (a[i-1] != b[j-1]) {
                dp[j] = 1 + min({prev, temp, dp[j-1]});
            }
            prev = temp;
        }
    }

    cout << dp[m] << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}