#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

void solve() {
    int n, a, b;
    cin >> n >> a >> b;

    int sum = a + b;
    if (sum > n || (sum > 0 && (a == 0 || b == 0))) {
        cout << "NO" << endl;
        return;
    }

    cout << "YES" << endl;

    for (int i = 1; i <= n; i++) cout << i << " ";
    cout << endl;

    for (int i = a + 1; i <= sum; i++) cout << i << " ";
    for (int i = 1; i <= a; i++) cout << i << " ";
    for (int i = sum + 1; i <= n; i++) cout << i << " ";
    cout << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}