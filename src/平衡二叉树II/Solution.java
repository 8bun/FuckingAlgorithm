package 平衡二叉树II;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/1 10:17
 * @level：
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null)
            return true;
        if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right); //左右子树平衡则平衡
    }
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
