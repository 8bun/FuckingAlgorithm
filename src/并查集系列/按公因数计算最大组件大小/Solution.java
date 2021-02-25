package 并查集系列.按公因数计算最大组件大小;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/9 09:31
 * @Description:
 * 这些数的所有质因子，是连接不同候选数的桥梁，即两个数因为有了相同的质因子，它们才能在一个连通分量里；
 * @Url: https://leetcode-cn.com/problems/largest-component-size-by-common-factor/
 * @限制:
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 100000
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

        /**
         * 这里会进行路径压缩，使得某个num的root因子会一直指向它当前所在的连通分量
         * 中所有num中的最大因子
         * 例如4,6,15,35，它们最后的root都会指向35的最大因子7，也称代表元
         * 所以在统计最大连通个数的时候，就只用求出每个连通分量中有多少num指向它，并求这些数量的的最大值即可
         * @param x
         * @return
         */
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * 这里的x一个是num，一个是num的因子
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

    }
    public int largestComponentSize(int[] A) {
        int len=A.length;
        int maxVal=Integer.MIN_VALUE;
        for (int num:A)
            maxVal=Math.max(maxVal,num);
        UnionFind uf=new UnionFind(maxVal+1); //最大因子可能为maxVal

        for (int num:A){
            for (int i=2; i<=Math.sqrt(num); i++){
                if (num%i==0){
                    uf.union(num, i);
                    if (num!=num/i) {
                        uf.union(num,num/i);
                    }
                }
            }
        }

        // 将候选数组映射成代表元，统计代表元出现的次数，找出最大者
        int[] cnt = new int[maxVal + 1];
        int res = 0;
        for (int num : A) {
            int root = uf.find(num);
//            System.out.println(num+" :"+root);
            cnt[root]++;
            res = Math.max(res, cnt[root]);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] A={4,6,15,35};
        int res=new Solution().largestComponentSize(A);
        System.out.println(res);
    }
}
