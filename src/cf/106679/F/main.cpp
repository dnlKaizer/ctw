#include <bits/stdc++.h>

using namespace std;

int log3(int n) {
    int v = 1;
    while (n >= 3) {
        n /= 3;
        v *= 3;
    }
    return v;
}

int f(int x) {
    int v = log3(x);

    if (x <= 2*v) return v + x;
    else return 3 * (x - v);
}

void solve() {
    int n; cin >> n;
    cout << f(n) << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}