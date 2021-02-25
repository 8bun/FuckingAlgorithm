package 二叉搜索树的后序遍历序列;

/**
 * @description: 使用递归的方式可进行判断，从前面开始遍历，小于的当前根元素的值是左子树的
 * 当找到第一个大于当前根元素的值，可以确定后半段的元素都应是在当前节点的右子树
 * 如果后半段（右子树）里面有小于根元素的值的元素，就说明这个不是二叉搜索树的后序遍历
 * 最后循环校验每个子树是否也满足二叉搜索树的后序遍历即可。
 * @url: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * @author: Jack
 * @createTime: 2020/2/28 15:00
 * @level: 中等
 */
public class Solution {

    private int[] postorder;

    public boolean verifyPostorder(int[] postorder) {
        this.postorder = postorder;
        if (postorder.length <= 2) {
            return true;
        }
        return isPostorder(0, postorder.length - 1);
    }

    //检查范围,end处为根节点
    private boolean isPostorder(int start, int end) {
        if (start >= end)
            return true;
        int i = start;
        //找到右子树开始元素
        while (postorder[i] < postorder[end])  //不存在重复元素
            i++;
        int flag = i;  //[start,flag-1]为左子树，[flag,end-1]为右子树
        for (; i < end; i++) {
            if (postorder[i] < postorder[end]) //如果后半段（右子树）里面有小于根元素的值的元素，就说明这个不是二叉搜索树的后序遍历
                return false;
        }
        return isPostorder(start, flag - 1) && isPostorder(flag, end - 1);
    }
}
