#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

void solve() {
    int n, k; cin >> n >> k;
    string s; cin >> s;

    vector<int> cnt(2);
    for (int i = 0; i < n; i++) {
        int v = s[i] - '0';
        cnt[v]++;
    }

    ll mx = max(cnt[0], cnt[1]), mn = min(cnt[0], cnt[1]);
    if (k <= mn) {
        mx += k;
        mn -= k;
    } else {
        mx += mn;
        mn -= mn;
    }

    ll ans = ((mx + 1) * mx / 2) + ((mn + 1) * mn / 2) + 1;
    cout << ans << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}