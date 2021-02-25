package 计算右侧小于当前元素的个数;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jack
 * @Date: 2020/7/11 10:12
 * @Description: 利用二叉搜索树，由于数组可能发生元素重复，所以使用一个count来表示节点元素出现的次数，插入重复元素直接+1就好了
 * 从数组尾到头构建二叉搜索树，例如[5,2,6,1]
 * 因为插入新结点的时候，反序构建二叉搜索树使得新结点的右边的元素都已经插入了，那么就可以直接从根结点遍历其他节点
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution1 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int count; //出现次数
        int leftCnt; //左子树所有节点的count总和

        TreeNode(int val) {
            this.left = null;
            this.right = null;
            this.val = val;
            count = 1;
            leftCnt = 0;
        }
    }

    public int insertNode(TreeNode root, int val) {

        TreeNode cur = root;
        int ans = 0; //统计从root节点开始遍历，比val小的结点的个数
        //由于我们是反序插入的，所以此时树的所有节点都在待插入节点的后面
        //leftCnt只有是val在某个结点的左子树才会+1
        while (true) {

            //如果出现了某个值相同的结点，那val的出现次数+1，并且返回统计的ans
            if (cur.val == val) {
                cur.count++;  //cur出现的次数+1
                ans += cur.leftCnt;
                break;
            }
            //如果后面的某个数大于当前数，当前数应该插在cur的左子树
            //与ans无关，因为ans统计的是比val小的结点的个数
            else if (cur.val > val) {
                cur.leftCnt++;
                //cur.left不为null，下移指针，继续循环
                if (cur.left != null) cur = cur.left;
                //直到cur.left为null，插入新节点
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
            //如果后面的某个数小于val，当前数应该插在cur的右子树，同时累加ans
            else {
                ans += (cur.count + cur.leftCnt); //cur是当前结点的个数（比val小）,cur.leftCnt是比当前结点值小的左子树（包括孙子..）的所有结点的count的总和
                //cur.right不为null，下移指针，继续循环
                if (cur.right != null) cur = cur.right;
                //直到cur.right为null，插入新节点
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            }
        }
        return ans;
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        if (len == 0) return res;

        TreeNode root = new TreeNode(nums[len - 1]);
        res.add(0, 0);

        for (int i = nums.length - 2; i >= 0; i--) {
            res.add(0, insertNode(root, nums[i]));
        }
        return res;
    }

}
