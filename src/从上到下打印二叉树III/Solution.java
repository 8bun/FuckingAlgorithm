package 从上到下打印二叉树III;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * @url: https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * @author: Jack
 * @createTime: 2020/2/28 13:11
 * @level: 中等
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
        int num=1;  //层号
        while (list_node.size()>0){
            List<Integer> r=new ArrayList<>();
            int size=list_node.size();
            //遍历所有父节点并将所有节点的子节点加入同一个List
            //为了保证每次index为0的位置为新的一个父节点，旧节点必须要出List
            for (int i=size-1;i>=0;i--){
                //反向遍历List
                /**
                 * 例如：
                 *      1
                 *    /  \
                 *   2   3
                 * / \  / \
                 *4  5 6  7
                 * num=1,List=[1],先入左子节点再入右
                 * List=[2,3],得到第二层的反向序列，num=2,先入右再入左，List反向遍历，先遍历3的子节点
                 * List=[2,7,6],同一层还没跳出for循环，继续遍历2的子节点，也是右再左
                 * List=[7,6,5,4]，得到第三层的反向序列
                 */
                TreeNode cur_fatherNode=list_node.remove(i);
                r.add(cur_fatherNode.val);
                if(num%2!=0) {
                    if (cur_fatherNode.left != null) {
                        list_node.add(cur_fatherNode.left);
                    }
                    if (cur_fatherNode.right != null) {
                        list_node.add(cur_fatherNode.right);
                    }
                }
                else{
                    if (cur_fatherNode.right != null) {
                        list_node.add(cur_fatherNode.right);
                    }
                    if (cur_fatherNode.left != null) {
                        list_node.add(cur_fatherNode.left);
                    }
                }
            }
            num++;
            res.add(r);
        }
        return res;
    }
}
