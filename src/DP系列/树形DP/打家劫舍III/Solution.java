package DP系列.树形DP.打家劫舍III;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 23:33
 * @Description:
 * 法一:（暴力  递归）
 * 我们使用爷爷、两个孩子、4 个孙子来说明问题
 * 首先来定义这个问题的状态
 * 爷爷节点获取到最大的偷取的钱数呢
 *
 * 首先要明确相邻的节点不能偷，也就是爷爷选择偷，儿子就不能偷了，但是孙子可以偷
 * 二叉树只有左右两个孩子，一个爷爷最多 2 个儿子，4 个孙子
 * 根据以上条件，我们可以得出单个节点的钱该怎么算
 * 4 个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱 哪个组合钱多，就当做当前节点能偷的最大钱数。这就是动态规划里面的最优子结构
 *
 * 由于是二叉树，这里可以选择计算所有子节点
 *
 * 4 个孙子投的钱加上爷爷的钱如下
 * int method1 = root.val + rob(root.left.left) + rob(root.left.right) + rob(root.right.left) + rob(root.right.right)
 * 两个儿子偷的钱如下
 * int method2 = rob(root.left) + rob(root.right);
 * 挑选一个钱数多的方案则
 * int result = Math.max(method1, method2);
 * @Url:
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

    public int rob(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) money += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) money += rob(root.right.left) + rob(root.right.right);

        return Math.max(money, rob(root.left) + rob(root.right));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
