package 计算右侧小于当前元素的个数;

import java.util.ArrayList;
import java.util.List;

/**
 * 先来想一个问题：
 * 当一个队伍，插入一个新同学的时候，如何知道有几个人身高比他矮呢？
 * 如果这个队伍的人并不是按照身高从低到高的顺序排列的话，那没办法，只能一个一个数了。如果这个队伍是有序的呢，只要找到插入的位置，就能直接计算出来比他矮的同学的个数了。
 * 在这个题目里，要想知道一个数后面比它小的数有多少个，只要找到新数字插入的位置就能判断出有几个比它小的（假设比它后面的数字都已经排好序了）。来一个例子。
 * [1,3,6,1,2,3]
 * input 3,  output: [3] -> 3 左边有 0 个数
 * input 2,  output: [2,3] -> 2 左边有 0 个数
 * input 1,  output: [1,2,3] -> 1 左边有 0 个数
 * input 6,  output: [1,2,3,6] -> 6 左边有 3 个数
 * input 3', output: [1,2,3',3,6] -> 3' 左边有 2 个数
 * input 1', output: [1',1,2,3',3,6] -> 1' 左边有 0 个数
 * 可以看到，在不断插入的过程中，能根据插入的位置判断出比它小的数有多少个。虽然插入的位置查找速度是 logn 的，但是插入过程却要移动元素，复杂度是 n，这个成本非常高。
 * 如果有一种办法，查找速度很快 logn，插入的速度也很快，O(1)，那多好。链表不行，虽然插入是 O(1)，但是查找却无法做到 log(n).
 * 那就只剩下树了。把上面的数组换成 bst (binary-search-tree)，一切就好办了。只要维护好这棵树就行。这里就不解释太多了，其它的答案都有很详细的说明。
 * @author cwq
 * @since 2020/7/11 10:12
 * @Description: 利用二叉搜索树，由于数组可能发生元素重复，所以使用一个count来表示节点元素出现的次数，插入重复元素直接+1就好了
 * 从数组尾到头构建二叉搜索树，例如[5,2,6,1]
 * 因为插入新结点的时候，反序构建二叉搜索树使得新结点的右边的元素都已经插入了，那么就可以直接从根结点遍历其他节点
 * @link
 * @限制:
 * @Level:
 */
public class Solution1 {

    /**
     * @param root 当前二叉树的根结点
     * @param val 待插入的值
     * @return 当前二叉树中的所有已存在节点中（都是数组右边的）比插入的值小的结点个数
     */
    public int insertNode(TreeNode root, int val) {

        TreeNode cur = root;
        int ans = 0; //统计从root节点开始遍历，比val小的结点的个数
        //由于我们是反序插入的，所以此时树的所有已存在节点都在待插入节点的右边
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

}
