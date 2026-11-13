#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;

    vector<int> par(n);
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        par[i] = x % 2;
    }

    int l = n-1, r = l-k-1;
    
    int curr = par[l], ans = 0;
    while (l > 0) {
        ans += par[l];
        l--;
        
        if (r >= 0) par[r--] ^= curr;
        
        par[l] ^= curr;
        curr ^= par[l];
    }
    ans += par[0];

    cout << (ans % 2 == 0 ? "Bob" : "Alice") << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int t; cin >> t;
    while (t--) 
        solve();

    return 0;
}