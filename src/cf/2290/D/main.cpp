#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int INF = 0x3f3f3f3f;
const ll LINF = 0x3f3f3f3f3f3f3f3f;

void solve() {
    int n; cin >> n;
    string s; cin >> s;

    vector<char> degree(26, 0);
    for (int i = 0, j = n-1; i < n; i++, j--) {
        if (s[i] != s[j]) {
            int x = s[i] - 'a', y = s[j] - 'a';
            if (x > y) swap(x, y);
            for (int k = x+1; k <= y; k++) {
                degree[k] = true;
            }
        }
    }

    cout << count(degree.begin(), degree.end(), true) << endl;

}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while (t--) 
        solve();
    
    return 0;
}