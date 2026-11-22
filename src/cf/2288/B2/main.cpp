#include <bits/stdc++.h>

#define endl "\n"
#define ll long long

using namespace std;

const int INF = 1e9;
const int MOD = 1e9 + 7;

void solve() {
    int n; cin >> n;
    string s;
    for (int i = 0; i < n; i++) {
        s.push_back('+');
        s.push_back('-');
        s.push_back('-');
    }
    cout << s << endl;
    cout.flush();

    int k; cin >> k;
    if (k == 0) {
        cout << n << endl; 
    } else {
        cout << n + ((k+2) / 3) << endl; 
    }

    cout.flush();
}

int main(void){
    // freopen("measurement.in", "r", stdin);
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t; cin >> t;
    while(t--)
        solve();
    // freopen("measurement.out","w", stdout);
    return 0;
}

