#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

void solve() {
    int n; cin >> n;
    vector<vector<int>> adj(n+1);
    for (int i = 2; i <= n; i++) {
        int p; cin >> p;
        adj[p].push_back(i);
    }

    if (adj[1].size() > 1) {
        cout << "*" << endl;
        return;
    }
    
    bool possible = true;
    vector<int> e(n+1, 0);
    deque<int> candidates;
    deque<tuple<int,int,bool>> dq;
    dq.push_back({2, 1, false});
    while (!dq.empty()) {
        auto [u, to, exiting] = dq.back();
        dq.pop_back();

        if (exiting) {
            if (!candidates.empty() && candidates.back() == u) {
                candidates.pop_back();
            }
            continue;
        }

        if (to == -1) {
            if (candidates.empty()) {
                possible = false;
                break;
            }
            to = candidates.back();
            candidates.pop_back();
        }

        if (adj[u].empty()) {
            e[u] = to;
            continue;
        }

        if (adj[u].size() == 1) {
            candidates.push_back(u);
            dq.push_back({u, 0, true});
        }

        for (int j = 0; j < adj[u].size(); j++) {
            int v = adj[u][j];
            if (j == 0) {
                dq.push_back({v, to, false});
            } else if (j == 1) {
                dq.push_back({v, u, false});
            } else {
                dq.push_back({v, -1, false});
            }
        }
    }

    if (!possible) {
        cout << "*" << endl;
        return;
    }
    
    for (int i = 1; i <= n; i++) {
        cout << e[i] << " ";
    }
    cout << endl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();
    return 0;
}