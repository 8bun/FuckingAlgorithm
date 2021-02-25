package 从上到下打印二叉树II;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 某个根节点的左右节点（同一层的所有节点）都压入List（根节点以及）,然后遍历List（遍历一个
 * 出一个List）将它们所有的子节点加入同一个List
 * @url: https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * @author: Jack
 * @createTime: 2020/2/28 11:12
 * @level: 简单
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)
            return new ArrayList<>(res);
        List<TreeNode> list_node=new ArrayList<>();
        list_node.add(root);
        while (list_node.size()>0){
            List<Integer> r=new ArrayList<>();
            int size=list_node.size();
            //遍历所有父节点并将所有节点的子节点加入同一个List
            //为了保证每次index为0的位置为新的一个父节点，旧节点必须要出List
            for (int i=0;i<size;i++){
                TreeNode cur_fatherNode=list_node.remove(0);
                r.add(cur_fatherNode.val);
                if(cur_fatherNode.left!=null){
                    list_node.add(cur_fatherNode.left);
                }
                if(cur_fatherNode.right!=null){
                    list_node.add(cur_fatherNode.right);
                }
            }
            res.add(r);
        }
        return res;
    }
}
