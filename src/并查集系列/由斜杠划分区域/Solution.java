package 并查集系列.由斜杠划分区域;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 22:55
 * @Description:
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * 返回区域的数目。
 * @Url: https://leetcode-cn.com/problems/regions-cut-by-slashes/
 * https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/guan-yu-guan-fang-bing-cha-ji-ti-jie-de-zi-wo-li-j/
 * @限制:
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 * @Level:
 */
public class Solution {

    private class UnionFind {
        private int[] parent;

        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            count=n;
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
            count--;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int getCount(){
            return count;
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf=new UnionFind(n*n*4); //n*n个1*1的小正方形，每个小正方形有4个区域
        //这里我们可以利用二维坐标转一维的技巧上加上0-3四个数来代表每个小正方形的四个区域
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                int start = 4 * (i * n + j); //这里要乘4，因为总量为4*n*n个小区域
                switch (grid[i].charAt(j)) {
                    case ' ':
                        uf.union(start+0, start + 1);
                        uf.union(start+2, start + 3);
                        uf.union(start+0, start + 2);
                        break;
                    case '/':
                        uf.union(start+0, start + 3);
                        uf.union(start+1, start + 2);
                        break;
                    case '\\':
                        uf.union(start+0, start + 1);
                        uf.union(start+2, start + 3);
                        break;
                }
                //同一列，上下两个相邻的小正方形的坐标为(i-1,j),(i,j)
                //他们的start关系为4*((i-1)*n+j)=4*(i*n+j)-4n
                //这里i>0保证从每一列从上数第二个小正方形开始向下合并
                if (i>0)uf.union(start+0,start-4*n+2); //下面边小正方形的0和上(start-4*n)的2合并

                //同一行，左右两个相邻的小正方形的坐标为(i,j-1),(i,j)
                //他们的start关系为4*(i*n+j-1)=4*(i*n+j)-4
                if (j>0)uf.union(start+3,start-3);  //右边小正方形的3和左(start-4)的1合并
            }
        }
        return uf.getCount();
    }
}
