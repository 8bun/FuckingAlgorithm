package DFS系列.统计二叉树中好节点的数目;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/16 23:02
 * @Description:
 * 比赛的时候思路想错，本来是想进行dfs遍历然后相邻遇到根结点的值大于子节点的值计数+1，但是没考虑图片的情况，
 * 正确做法应该是每一层得到前面路径的最大值，如果当前值比这个最大值还要小那么就不要+1，如果大于等于最大值就+1
 * 总结：dfs题目卡了应该想想思路是否正确，其次头脑要清晰
 * @Url:
 * @限制:
 * @Level:
 */


class TreeNode {
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
public class Solution {
    private int cnt = 0;

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        dfs(root, root.val);
        return cnt;
    }

    private void dfs(TreeNode root, int curMax){
        if (root == null) return;

        if (root.val >= curMax) cnt++;
        curMax = Math.max(curMax, root.val);
        dfs(root.left, curMax);
        dfs(root.right,curMax);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode left_left = new TreeNode(3);
        TreeNode right_left = new TreeNode(1);
        TreeNode right_right = new TreeNode(5);
        root.left = left;
        root.right = right;
        root.left.left = left_left;
        root.left.right = null;
        root.right.left = right_left;
        root.right.right = right_right;
        int res = new Solution().goodNodes(root);
        System.out.println(res);
    }
}
