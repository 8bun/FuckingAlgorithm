package DFS系列.从叶结点开始的最小字符串;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 23:32
 * @Description:
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，
 * a分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 'b'，依此类推。
 * 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 * @Url: https://leetcode-cn.com/problems/smallest-string-starting-from-leaf/
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
    private String smallestStr="~"; //ascii码126,z的ascii码122
    public String smallestFromLeaf(TreeNode root) {
        dfs(root,new StringBuilder());
        return smallestStr;
    }

    /**
     * 到达叶子结点时可以回溯叶子结点
     * @param root
     * @param curStr
     */
    private void dfs(TreeNode root,StringBuilder curStr){
        if (root==null)return;
        //选择root.val
        curStr.append((char)('a'+root.val));

        if (root.left==null&&root.right==null){
            //当到达叶子结点时选择root.val
            curStr.reverse();
            String revStr=curStr.toString();
            curStr.reverse();  //使得curStr一直都是从根结点到叶子结点的字符串路径
            if (revStr.compareTo(smallestStr)<0) smallestStr=revStr;
        }
        dfs(root.left,curStr);
        dfs(root.right,curStr);
        //不选择root.val,回溯
        curStr.deleteCharAt(curStr.length() - 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
