package 从上到下打印二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * BFS使用List(或队列LinkList)存储root，将其非空左右子树依次入List，root出List,然后继续循环
 * ArrayList向int[]转化时会报错，这是因为ArrayList中规定的泛型是Integer，不能直接转化到int[]
 * @url:https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * @author:Jack
 * @createTime:2020/2/28 10:25
 * @level:中等
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {

    public int[] levelOrder(TreeNode root) {
        if(root==null)
            return new int[0];
        List<Integer> res=new ArrayList<>();
        List<TreeNode> list_node=new ArrayList<>();
        list_node.add(root);
        res.add(root.val);
        while (list_node.size()>0){
            TreeNode node=list_node.get(0);
            if(node.left!=null){
                res.add(node.left.val);
                list_node.add(node.left);
            }
            if(node.right!=null){
                res.add(node.right.val);
                list_node.add(node.right);
            }
            list_node.remove(0); //删除根节点
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}
