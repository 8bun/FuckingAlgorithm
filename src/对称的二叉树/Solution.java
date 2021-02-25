package 对称的二叉树;

/**
 * @description: 二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 我们发现，对于一开始的root节点，其root.left与root.right是对称的，而对于root.left和root.right
 * 而言，root.left.left与root.right.right是对称的（值相等），root.left.right与root.right.left同理，所以可写出递归
 * @url: https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 * @author: Jack
 * @createTime: 2020/2/27 10:36
 * @level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) //空树是对称
            return true;
        return isMatch(root.left, root.right);
    }

    private boolean isMatch(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val == right.val)
            return isMatch(left.left, right.right) && isMatch(left.right, right.left);
        else
            return false;
    }

}
