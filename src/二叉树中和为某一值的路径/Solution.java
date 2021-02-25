package 二叉树中和为某一值的路径;


import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @url: https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * @author: Jack
 * @createTime: 2020/2/28 16:52
 * @level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {

    private List<List<Integer>> res;
    private int sum;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.res=new ArrayList<>();
        if(root==null)
            return res;
        List<Integer> list=new ArrayList<>();
        list.add(root.val);
        this.sum=sum;
        dfs(root,list);
        return res;
    }
    private void dfs(TreeNode cur_node,List<Integer> cur_list){
        if(cur_node.left==null && cur_node.right==null){ //当访问到叶子节点时，查看cur_list是否符合要求
            if(sum(cur_list)==sum){
                /**
                 * result.add(item)的话传的是item的地址，所以如果更新item里面的内容的话result里面也会变；
                 * 而result.add(new ArrayList<String>(item))相当于deep copy，
                 * 更新item并不会更新result里面的东西
                 */
                res.add(new ArrayList<>(cur_list));
            }
        }
        if(cur_node.left!=null){
            cur_list.add(cur_node.left.val);
            dfs(cur_node.left,cur_list);
            //左子树递归结束时（也就是到了叶子节点，不会再进行下一次递归了）回去（递归父调用）时
            //将路径上的节点删除，直到cur_node为路径的最后一个节点，然后进入cur_node的右子树
            cur_list.remove(cur_list.size()-1);
        }
        if(cur_node.right!=null){

            cur_list.add(cur_node.right.val);
            dfs(cur_node.right,cur_list);
            cur_list.remove(cur_list.size()-1);
        }
    }
    //计算路径总和
    private int sum(List<Integer> list){
        int s=0;
        for(Integer num:list){
            s+=num;
        }

        return s;
    }

}
