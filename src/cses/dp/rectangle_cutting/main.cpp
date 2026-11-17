#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;
const int MAX = 505;

void solve() {
    int a, b; cin >> a >> b;
    if (a > b) swap(a, b);
    int dp[MAX][MAX];
    for (int i = 1; i <= a; i++) {
        for (int j = i; j <= b; j++) {
            if (j % i == 0) {
                dp[i][j] = j / i;
            } else {
                dp[i][j] = INF;
                for (int k = 1; k <= i/2; k++) {
                    dp[i][j] = min(dp[i][j], dp[i-k][j] + dp[k][j]);
                }
                for (int k = 1; k <= j/2; k++) {
                    dp[i][j] = min(dp[i][j], dp[i][j-k] + dp[i][k]);
                }
            }
            dp[j][i] = dp[i][j];
        }
    }
    cout << dp[a][b]-1 << endl;
}

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}