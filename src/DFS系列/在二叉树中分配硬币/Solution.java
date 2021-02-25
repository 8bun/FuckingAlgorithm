package DFS系列.在二叉树中分配硬币;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/6 18:30
 * @Description:
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。
 * (移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 这道题引入一个过载量，例如一个结点含有的硬币数为1，那么它的过载量为0
 * 如果硬币数为2，则过载量为+1，表示多1；硬币数为0，过载量为-1，表示缺1
 * 这个过载量与需要移动的路径是关联的
 * https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/solution/cdi-gui-jie-shi-by-maybe-3/
 * @Url: https://leetcode-cn.com/problems/distribute-coins-in-binary-tree/
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
    private int sum=0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return sum;
    }


    /**
     * 统计以root为根结点的左右子树的需要移动的路径
     *   2
     *  / \
     *  1 0
     * @param root
     * @return
     */
    private int dfs(TreeNode root){
        if (root==null)return 0;
        int leftNeed=dfs(root.left);
        int rightNeed=dfs(root.right);
        sum+=Math.abs(leftNeed);
        sum+=Math.abs(rightNeed);
        return leftNeed+rightNeed + 1-root.val;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
