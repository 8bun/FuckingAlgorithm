package DFS系列.二叉树中的伪回文路径;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/24 19:01
 * @Description:
 * 判断一个字符串能不能经过重排之后变成回文串，就要判断出现奇数次的字符是不是只有一种或者根本没有出现奇数次的字符即可
 * 其实是一个回溯问题
 * @Url: https://leetcode-cn.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 * @限制:
 * @Level:
 */

public class Solution {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private int[] cnt = new int[10]; //统计路径中[1,9]出现的次数
    private int ans = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode root){
        if (root == null) return;
        ++cnt[root.val]; //选择root
        if (root.left == null && root.right == null) {//到了叶子节点
            int count = 0;
            for (int i = 1; i < 10; i++){
                if (cnt[i] % 2 != 0) ++count;
            }
            if (count <= 1) ++ans;
        }
        else {
            dfs(root.left);
            dfs(root.right);
        }
        --cnt[root.val]; //不选择root，回溯
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }
}
