package 树的子结构;

/**
 * @description:输入两棵二叉树A和B，判断B是不是A的子结构,空树不是任意一个树的子结构
 * @url:https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 * @author:Jack
 * @createTime:2020/2/25 23:10
 * @level:中等
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null)
            return false;
        else{
            return isMatch(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);
        }

    }
    //当前被检查比较的子树的顶点，每次递归更新，比较其值相同则继续递归它们的左子树或者右子树,如果其左子树或右子树匹配则返回true
    private boolean isMatch(TreeNode subA, TreeNode B){
        if(subA==null)
            return false;
        if(subA.val==B.val){
            boolean flag=true;
            //左子树试一下
            if(B.left!=null) {
                //左子树试一下
                flag = flag &&  isMatch(subA.left,B.left);  //更新flag的匹配状态
            }
            if(B.right!=null){
                //右子树试一下，方向一致
                flag = flag &&  isMatch(subA.right,B.right);  //更新flag的匹配状态
            }
            if(flag)
                return true;
        }
        return false;
    }
}
