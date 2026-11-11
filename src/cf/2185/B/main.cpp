#include <bits/stdc++.h>

using namespace std;

const int INF = 0x3f3f3f3f;

int main() {

    int t; cin >> t;
    while (t-- > 0) {
        int n; cin >> n;
        int big = -INF;
        for (int i = 0; i < n; i++) {
            int x; cin >> x;
            big = max(big, x);
        }

        cout << n * big << endl;
    }

    return 0;
}