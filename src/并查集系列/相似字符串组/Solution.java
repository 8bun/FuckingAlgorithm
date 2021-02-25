package 并查集系列.相似字符串组;

/**
 * @Author: Jack
 * @Date: 2020/5/8 22:23
 * @Description:
 * 如果我们交换字符串 X 中的"两个不同位置"的字母，使得它和字符串 Y 相等，
 * 那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
 * 但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
 * 也就是说除了只有一个字符串的关联组之外，其他的同一关联组中，对于每个字符串都能在组内找到至少一个与它是相似的字符串，而不必两两相似
 * 对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 给出一个不包含重复的字符串列表 A。列表中的每个字符串都是 A 中其它所有字符串的一个字母异位词。
 * 求 A 中有多少个相似字符串组
 * 这里将相似的字符串的下标进行联合，然后计算最终有几个连通分量即可
 *  @Url: https://leetcode-cn.com/problems/similar-string-groups/
 * @限制:
 * A.length <= 2000
 * A[i].length <= 1000
 * A.length * A[i].length <= 20000
 * A 中的所有单词都"只包含小写字母"。
 * A 中的所有单词都具有"相同的长度，且是彼此的字母异位词"。
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
    public int numSimilarGroups(String[] A) {
        int n = A.length;
        if (A.length==0)
            return 0;
        UnionFind uf=new UnionFind(n);
        for (int i=0;i<n-1;i++) {
            for (int j = i + 1; j < n; j++) { //对A[i]后面的所有字符进行检查
                if (isSimilar(A[i],A[j]))
                    uf.union(i,j);
            }
        }
        return uf.getCount();
    }

    /**
     * 判断字符串是否相似
     * @param a
     * @param b
     * @return
     */
    public boolean isSimilar(String a, String b){
        char[] cha=a.toCharArray();
        char[] chb=b.toCharArray();
        int cnt=0;
        for (int i=0;i<cha.length;i++) {
            if (cha[i] != chb[i]) cnt++;
        }
        //因为给出的所有单词都具有相同的长度，且是彼此的字母异位词，也就是每个单词的字符种类是一样的，
        //所以cnt==2可以返回true
        if (cnt==0||cnt==2)
            return true;
        return false;
    }
}
