package DFS系列.删除给定值的叶子节点;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/25 23:09
 * @Description:
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

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        dfs(root, root.left,target);
        dfs(root, root.right,target);
        return root;
    }
    private TreeNode dfs(TreeNode pre, TreeNode after, int target){
        if (after == null) return null;
        if (after.left == null && after.right ==null){
            if (after.val == target){
                pre.left = null;
                pre.right = null;
            }
            if (pre.val == target) return pre;
            return null;
        }
        TreeNode next = dfs(after, after.left, target);
        if (next != null) {
            dfs(next, next.left, target);
            dfs(next, next.right, target);
        }
        next = dfs(after, after.right, target);
        if (next != null) {
            dfs(next, next.left, target);
            dfs(next, next.right, target);
        }
        return pre;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
