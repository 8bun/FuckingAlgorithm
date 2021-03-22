package DFS系列.合法二叉搜索树;

import 粤港澳.O;

/**
 * @author cwq
 * @since 2021/03/20
 */

public class Solution1 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 限定以root为根的所有子树节点都必须满足
     * min.val < root.val < max.val
     * 也就是 左子树所有节点最大值 < root.val < 小于右子树所有节点的最小值
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        // 如果违反了min.val < root.val < max.val关系
        if (min != null && root.val <= min.val) {
            return false;
        }
        if(max != null && root.val >= max.val) {
            return false;
        }
        // 如果 min.val < root.left.val < root.val && root.val < root.right.val < max.val
        // => min.val < root.left.val < root.val < root.right.val < max.val
        // 所以 min.val < root.val < max.val
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}
