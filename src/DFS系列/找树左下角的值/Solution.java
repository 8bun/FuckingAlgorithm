package DFS系列.找树左下角的值;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 13:44
 * @Description:
 *
 * @Url: https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 * @限制: 可以假设树（即给定的根节点）不为 NULL。
 * @Level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    private int maxLevel=0;
    private int posVal;
    public int findBottomLeftValue(TreeNode root) {
        posVal=root.val;
        dfs(root.left,1);
        dfs(root.right,1);
        return posVal;
    }
    private void dfs(TreeNode root,int curLevel){
        if (root==null)return;
        //维护一个最深层，目标值一定在更深的层
        if (curLevel>maxLevel){
            maxLevel=curLevel;
            posVal=root.val;
        }
        dfs(root.left,curLevel+1);
        dfs(root.right,curLevel+1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
