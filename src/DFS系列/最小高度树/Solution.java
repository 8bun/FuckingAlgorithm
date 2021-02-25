package DFS系列.最小高度树;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 23:10
 * @Description:
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵“高度最小”的二叉搜索树，这棵树不唯一。
 * 要满足高度最小，那么树应该是平衡二叉树，那么二叉平衡搜索树的元素个数相差1，可以是一种情况
 * @Url: https://leetcode-cn.com/problems/minimum-height-tree-lcci/
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

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums,0,nums.length-1);
    }
    private TreeNode dfs(int[] nums,int left,int right){
        if (left>right)
            return null;
        int mid=(left+right)>>1;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=dfs(nums,left,mid-1); //构建左子树
        root.right=dfs(nums,mid+1,right); //构建右子树
        return root;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
