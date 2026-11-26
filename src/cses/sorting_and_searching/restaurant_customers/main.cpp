#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n; cin >> n;
    map<int, int> diff;
    for (int i = 0; i < n; i++) {
        int in, out;
        cin >> in >> out;
        diff[in]++;
        diff[out]--;
    }

    int ans = 0, curr = 0;
    for (const auto &[k, v] : diff) {
        curr += v;
        ans = max(ans, curr);
    }

    cout << ans << endl;

    return 0;
}