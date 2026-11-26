#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

typedef long long ll;

void solve() {
    ll k; cin >> k;

    ll d = 1;
    ll count = 9;
    ll start = 1;

    while (k > count * d) {
        k -= count * d;
        d++;
        count *= 10;
        start *= 10;
    }

    ll num = start + (k - 1) / d;

    string s = to_string(num);
    cout << s[(k - 1) % d] << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}