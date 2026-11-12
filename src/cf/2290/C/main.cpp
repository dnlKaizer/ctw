#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3f;

void solve() {
    int n; cin >> n;
    vector<int> h(n);
    for (auto &a : h) cin >> a;

    sort(h.begin(), h.end());

    deque<int> inf;
    deque<int> sup;

    int ans = 0;
    int cnt = 0, prev = -1;
    for (int i = 0; i < n; i++) {
        if (h[i] == prev) {
            cnt++;
            sup.push_back(h[i]);
        } else {
            ans += min(cnt, 2);
            cnt = 1;
            prev = h[i];
            inf.push_back(h[i]);
        }
    }
    ans += min(cnt, 2);

    cout << ans << endl;
    while (!inf.empty()) {
        cout << inf.front() << " ";
        inf.pop_front();
    }
    while (!sup.empty()) {
        cout << sup.back() << " ";
        sup.pop_back();
    }
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