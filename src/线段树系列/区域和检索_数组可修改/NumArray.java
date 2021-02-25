package 线段树系列.区域和检索_数组可修改;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/7/16 18:22
 * @Description:
 * @Url: https://leetcode-cn.com/problems/range-sum-query-mutable/
 * @限制:
 * @Level:
 */
public class NumArray {

    private SegmentTreeNode root;

    private static class SegmentTreeNode {
        int start;// 节点线段的开始下标
        int end;// 节点线段的结束下标
        int sumValue;// 节点线段开始-结束所有值的和，start==end的时候，就是节点本身的值

        SegmentTreeNode left;
        SegmentTreeNode right;
        SegmentTreeNode parent;

        SegmentTreeNode(int start, int end) {
            this(start, end, 0);
        }

        SegmentTreeNode(int start, int end, int sumValue) {
            this.start = start;
            this.end = end;
            this.sumValue = sumValue;
        }
    }

    public NumArray(int[] nums) {
        this.root = buildSegmentTree(nums, 0, nums.length - 1);
    }

    /**
     * 构造[start,end]的线段树
     *
     * @param nums
     * @param start
     * @param end
     *
     * @return
     */
    private SegmentTreeNode buildSegmentTree(int[] nums, int start, int end) {
        if (start > end) return null;
        else if (start == end) return new SegmentTreeNode(start, end, nums[start]);
        int mid = start + (end - start >> 1); //注意括号不可去
        SegmentTreeNode left = buildSegmentTree(nums, start, mid);
        SegmentTreeNode right = buildSegmentTree(nums, mid + 1, end);
        SegmentTreeNode parent = new SegmentTreeNode(start, end);
        if (left != null) {
            parent.left = left;
            left.parent = parent;
            parent.sumValue += left.sumValue;
        }
        if (right != null) {
            parent.sumValue += right.sumValue;
            right.parent = parent;
            parent.right = right;
        }
        return parent;
    }

    public void update(int i, int val) {
        if (i < this.root.start || i > this.root.end) return;
        SegmentTreeNode node = this.root;
        //直到编号i的叶子结点
        while (node.start != node.end) {
            int mid = node.start + (node.end - node.start >> 1);
            if (mid >= i) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        node.sumValue = val;
        while (node.parent != null) {
            node.parent.sumValue = node.parent.left.sumValue + node.parent.right.sumValue;
            node = node.parent;
        }
    }

    public int sumRange(int i, int j) {
        return sumNode(root, i, j);
    }

    private int sumNode(SegmentTreeNode root, int i, int j) {

        if (root.start == i && root.end == j) return root.sumValue;
        else {
            int mid = root.start + (root.end - root.start >> 1);
            if (mid >= j) return sumNode(root.left, i, j);
            else if (mid < i) return sumNode(root.right, i, j);
            else return sumNode(root.left, i, mid) + sumNode(root.right, mid + 1, j);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // int[] nums = {0,9,5,7,3};
        // NumArray numArray = new NumArray(nums);
        // int sum_4_4 = numArray.sumRange(4,4);
        // int sum_2_4 = numArray.sumRange(2,4);
        // int sum_3_3 = numArray.sumRange(3,3);
        // numArray.update(4,5);
        // numArray.update(1,7);
        // numArray.update(0,8);
        // int sum_1_2 = numArray.sumRange(1,2);
        // numArray.update(1,9);
        // int sum_4_4_2 = numArray.sumRange(4,4);
        // numArray.update(3, 4);
        System.out.println(1 + 3 - 1 >> 1); //+-的优先级高于>>，所以先算1+3-1=3,3>>1=1
        System.out.println(1 + (3 - 1 >> 1)); //1 + (2 >> 1) = 1+1=2
    }
}
