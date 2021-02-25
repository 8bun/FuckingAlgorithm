package 二叉树的镜像;

/**
 * @description:对于每个节点而言，都可以看成发生左右子树的交换，所以可采用递归的方式更新当前节点并进行交换
 * @url:https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 * @author:Jack
 * @createTime:2020/2/26 22:26
 * @level:简单
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null)
            return null;
        TreeNode tmp=root.left;
        root.left=mirrorTree(root.right);
        root.right=mirrorTree(tmp);
        return root;
    }
}
