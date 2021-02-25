package DFS系列.二叉树的直径;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 22:47
 * @Description:
 * 可以将二叉树的直径转换为：二叉树的每个节点的左右子树的高度和的最大值。
 * @Url: https://leetcode-cn.com/problems/diameter-of-binary-tree/
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

    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)return max;
        dfs(root);
        return max;
    }

    /**
     * 这个递归有点特殊，那就是用一个递归维护多个值
     * 这里维护了(root的左子树高度)和(root的右子树高度)两个单边高度（再分别加上root 1个结点高度）
     * 用最大值作为root的高度
     * 再利用它们的和更新最大直径
     * 也就是说在实现功能的过程中实现了其他功能
     * [相似做法的题目有: 二叉树中的最大路径和]
     * @param root
     * @return
     */
    private int dfs(TreeNode root){
        if (root == null) return 0;
        int leftDepth = dfs(root.left);

        int rightDepth = dfs(root.right);
        max = Math.max(max, leftDepth + rightDepth); //root的直径 = root左子树高度 + root右子树高度
        return Math.max(leftDepth, rightDepth) + 1;  //root的高度 = max {root左子树高度, root右子树高度} + 1
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
