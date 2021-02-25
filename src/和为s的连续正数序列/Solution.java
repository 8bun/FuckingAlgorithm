package 和为s的连续正数序列;

import java.util.ArrayList;

/**
 * @description：
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 滑动窗口解题，题解：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/shi-yao-shi-hua-dong-chuang-kou-yi-ji-ru-he-yong-h/
 *
 * @url： https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/2 23:18
 * @level：
 */
public class Solution {
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res=new ArrayList<>();
        int left=1;  //左指针指向的数字
        int right=2;
        int sum=left+right;
        //例如target=7，left=[1,2,3]
        while (left<=target/2){
            if(sum<target){
                right++;
                sum+=right;
            }else if (sum>target){
                sum-=left;
                left++;
            } //例如9,[2,3,4]，left++，找下一个更大的起点
            else {
                int arr[]=new int[right-left+1];
                for(int i=left;i<=right;i++){
                    arr[i-left]=i;
                }
                res.add(arr);
                sum-=left;
                left++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
