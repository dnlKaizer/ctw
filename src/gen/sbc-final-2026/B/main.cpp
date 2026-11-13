#include <bits/stdc++.h>
#define pb push_back

using namespace std;

typedef long long ll;

struct pt {
    ll x, y;
    pt(ll x = 0, ll y = 0) : x(x), y(y) {}

    pt operator + (const pt& p) const { return pt(x+p.x, y+p.y); }
    pt operator - (const pt& p) const { return pt(x-p.x, y-p.y); }
    pt operator * (const int c) const { return pt(x*c, y*c); }
    ll operator * (const pt& p) const { return x*p.x + y*p.y; }
    ll operator ^ (const pt& p) const { return x*p.y - y*p.x; }
};

struct cat {
    pt p; ll w; int i;
};

const int MAX = 2e5 + 5;
ll bit[MAX];
vector<cat> l, r;
int n, d;

void add(int i, ll val) {
    for (; i <= n; i += i & -i) bit[i] += val;
}

ll query(int i) {
    ll ret = 0;
    for (; i > 0; i -= i & -i) ret += bit[i];
    return ret;
}

void update(int i, ll val) {
    add(i, val - (query(i) - query(i-1)));
}

void solve() {
    cin >> n >> d;
    for (int i = 1; i <= n; i++) {
        ll x, y, w;
        cin >> x >> y >> w;
        cat c = {pt(x, y), w, i};
        l.pb(c);
        r.pb(c);
    }

    sort(l.begin(), l.end(), [](const cat& a, const cat &b){
        return (a.p ^ b.p) > 0;
    });

    sort(r.begin(), r.end(), [](const cat& a, const cat &b){
        pt v = pt(d, 0);
        pt x = a.p - v;
        pt y = b.p - v;
        return (x ^ y) < 0;
    });

    vector<int> pos(n+1);
    for (int i = 1, j = 0; i <= n; i++, j++) {
        add(i, r[j].w);
        int idx = r[j].i;
        pos[idx] = j;
    }

    ll ans = 0;
    for (int i = 1, j = 0; i <= n; i++, j++) {
        int idx = l[j].i;
        int k = pos[idx];
        ll qry = query(k);
        ll sum = l[j].w * qry;
        ans += sum;
        update(k+1, 0);
    }

    cout << ans << endl;
    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    solve();
    return 0;
}