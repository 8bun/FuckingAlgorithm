package 并查集系列.等式方程的可满足性;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 21:42
 * @Description:
 * @Url: https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 * @限制:
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 * @Level:
 */
public class Solution {
    private class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * @param x
         * @param y
         * @return
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX==rootY)return;
            parent[rootX] = rootY;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public boolean equationsPossible(String[] equations) {

        UnionFind unionFind = new UnionFind(26);

        for (String equation : equations) {
            //将所有等式建立关联，例如a==c的顶点a,c建立0,2的关联
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                unionFind.union(index1, index2);
            }
        }
        //将所有等式建立完后，再来遍历有没有违反等式的例子
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                //如果index1和index2已经建立连接，但这里又为不相等，表示等式有矛盾，
                if (unionFind.isConnected(index1, index2)) {
                    return false;
                }
            }
        }
        // 如果检查了所有不等式，都没有发现矛盾，返回 true
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
