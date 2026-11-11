#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main() {
    int t; cin >> t;
    while (t-- > 0) {
        int n, m, h;
        cin >> n >> m >> h;
        
        vector<ll> a(n+1);
        vector<ll> aux(n+1);
        for (int i = 1; i <= n; i++) {
            cin >> a[i];
            aux[i] = a[i];
        }
    
        int last = 0;
        vector<int> last_reset(n+1, 0);    
        for (int i = 0; i < m; i++) {
            int b, c;
            cin >> b >> c;
            
            if (last_reset[b] < last) {
                last_reset[b] = i;
                aux[b] = a[b];
            }
            
            aux[b] += c;
            
            if (aux[b] > h) {
                last = i;
                last_reset[b] = i;
                aux[b] = a[b];
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (last_reset[i] < last) {
                aux[i] = a[i];
            }
            cout << aux[i] << " ";
        }
        cout << endl;
        
    }
    
    return 0;
}