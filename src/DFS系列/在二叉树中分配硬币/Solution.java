package DFS系列.在二叉树中分配硬币;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/6 18:30
 * @Description: 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * <p>
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。
 * (移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 这道题引入一个过载量，例如一个结点含有的硬币数为1，那么它的过载量为0
 * 如果硬币数为2，则过载量为+1，表示多1；硬币数为0，过载量为-1，表示缺1
 * 这个过载量与需要移动的路径是关联的
 * 即自底向上的递归
 * 如叶子结点的硬币数目无非有以下两种种情况：
 * 叶子节点有0枚硬币（与所需1枚硬币相比，过载量为-1）；需要从父节点移动1枚给叶子节点
 * 叶子节点为4枚硬币（与所需1枚硬币相比，过载量为+3）；需要从叶子节点移动3枚给父节点
 * 那么总移动的次数是过载量绝对值之和1+3=4
 * https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/solution/zai-er-cha-shu-zhong-fen-pei-ying-bi-by-leetcode/
 * @link https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/
 * @限制:
 * @Level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class Solution {
    private int sum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return sum;
    }

    /**
     * 统计root结点的过载量：当前结点的val+左右子树的过载量之和-1
     * 2
     * / \
     * 1 0
     *
     * @param root
     *
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) return 0;

        // 左右子树的过载量
        int leftNeed = dfs(root.left);
        int rightNeed = dfs(root.right);

        // 移动总次数加上左右子树的过载量的绝对值之和
        sum += Math.abs(leftNeed);
        sum += Math.abs(rightNeed);

        return leftNeed + rightNeed + root.val - 1;
    }
}
