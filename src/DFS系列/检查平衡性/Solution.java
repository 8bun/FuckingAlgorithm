package DFS系列.检查平衡性;

import 前缀树系列.键值映射.MapSum;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/1 22:15
 * @Description:
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 * 检查"每个"节点的左子树和右子树的最大深度，如果相差大于1，那么不是一颗平衡树
 * @Url: https://leetcode-cn.com/problems/check-balance-lcci/
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

    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        //当前root根节点的二叉树要平衡的条件：左右子树深度相差<=1，并且左右子树都是平衡的
        return Math.abs(maxDepth(root.left,0)-maxDepth(root.right,0))<=1&&isBalanced(root.left)&&isBalanced(root.right);
    }
    public int maxDepth(TreeNode root,int cnt){
        if (root==null)
            return cnt;
        return Math.max(maxDepth(root.left,cnt+1),maxDepth(root.right,cnt+1));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
