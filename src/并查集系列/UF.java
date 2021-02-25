package 并查集系列;

/**
 * @Author: Jack
 * @Date: 2020/5/8 13:10
 * @Description:  Union-Find 算法
 * @Url:
 * @限制:
 * @Level:
 */
public class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP; //此时rootQ的root是rootP，也就是说合并后rootQ不再是任何集合的root
            //此时我们只关心作为root的rootP的集合的size，因为合并后的集合的root是rootP而不是rootQ了
            //当然这里可以更新size[rootQ] += size[rootP];但是没有任何意义
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}
