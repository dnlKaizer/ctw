#include <bits/stdc++.h>

using namespace std;

int prime(int n) {
    set<int> s;
    int ret = 1;
    for (int i = 2; i*i <= n; i++) {
        while (n % i == 0) {
            s.insert(i);
            n /= i;
            ret = i;
        }
    }
    if (n > 1) {
        s.insert(n);
        ret = n;
    }

    if (s.size() > 1) return -1;
    return ret;
}

void solve() {
    int n; cin >> n;
    vector<int> v(n), p(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
        p[i] = prime(v[i]);
    }

    if (is_sorted(v.begin(), v.end()))
        cout << "Bob\n";
    else if (*min_element(p.begin(), p.end()) == -1)
        cout << "Alice\n";
    else if (is_sorted(p.begin(), p.end()))
        cout << "Bob\n";
    else
        cout << "Alice\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--)
        solve();
    return 0;
}