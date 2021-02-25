#include<bits/stdc++.h>
/**
 * https://www.luogu.com.cn/problem/P3390
 */
using namespace std;

typedef long long ll;
const int maxn = 110;
const int MOD = 1e9 + 7;
ll n; //维度
#define mod(x) ((x) % MOD)
//单位矩阵
struct mat{
    int m[maxn][maxn];
} unit_mat;
/**
 * 重载矩阵乘法
 * @param a
 * @param b
 * @return
 */
mat operator * (mat a, mat b) {
    mat ret;
    ll x;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            x = 0;
            for(int k = 0; k < n; k++){
                x = mod(x + (ll) mod(a.m[i][k]) * mod(b.m[k][j]));
            }
            ret.m[i][j] = x;
        }
    }
    return ret;
}
/**
 * 初始化单位矩阵，主对角线都是1
 */
void init_unit_mat(){
    for(int i = 0; i < n; ++i) {
        unit_mat.m[i][i] = 1;
    }
}
/**
 * 矩阵快速幂与常数快速幂相类似，1变成单位矩阵，乘法需要重载
 * @param a
 * @param k
 * @return
 */
mat pow_mat(mat a, ll k) {
    mat ret = unit_mat;
    while (k) {
        if(k & 1) ret = ret * a;
        a = a * a;
        k >>= 1;
    }
    return ret;
}

int main() {
    ll k;
    //矩阵a为n*n，求a^k的矩阵
    cin >> n >> k;
    init_unit_mat();
    mat a;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> a.m[i][j];
        }
    }
    a = pow_mat(a, k);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if(j + 1 == n) cout << a.m[i][j] << endl;
            else cout << a.m[i][j] << " ";
        }
    }
    return 0;
}
