#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n; cin >> n;
    int pref_c = 0, pref_v = 0, ans[n+1];
    for (int i = 1; i <= n; i++) {
        int c, v; cin >> c >> v;
        pref_c += c;
        pref_v += v;

        if (pref_c > pref_v) ans[i] = 1;
        else if (pref_c == pref_v) ans[i] = 0;
        else ans[i] = -1;
    }

    int q; cin >> q;
    while (q--) {
        int i; cin >> i;

        string s;
        if (ans[i] == 1) s = "COMPRA";
        else if (ans[i] == 0) s = "NEUTRO";
        else s = "VENDA";

        cout << s << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}