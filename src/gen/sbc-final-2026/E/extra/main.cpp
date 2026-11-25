#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

void solve() {
    int n; cin >> n;
    vector<vector<int>> adj(n+1);
    vector<int> parent(n+1);
    for (int i = 2; i <= n; i++) {
        int p; cin >> p;
        adj[p].push_back(i);
        parent[i] = p;
    }

    bool possible = true;
    vector<int> e(n+1, 0);
    vector<list<int>> leaves(n+1);
    for (int u = n; u >= 1; u--) {
        auto &curr = leaves[u];
        
        if (adj[u].empty()) {
            curr.push_back(u);
        } else for (int v : adj[u]) {
            curr.splice(curr.end(), leaves[v]);
        }

        if (u == 1) {
            if (curr.size() != 1) possible = false;
            else e[curr.front()] = u;
            break;
        }

        if (curr.size() > 1) {
            e[curr.front()] = u;
            curr.pop_front();
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