package DP系列.背包DP.分割等和子集;

import java.util.Arrays;

/**
 * @description： DFS+剪枝
 * 遍历数组，维护两个变量（可以看成子集）存储现在的和，如果其中一个集合和为一半就是true
 * 遍历数组，初始cur1=0,cur2=0
 * 每个元素都有两种选择，要么加入cur1，要么加入cur2
 * 然后在递归的开始地方进行条件判断，剪去决策树的某些枝
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 20:54
 * @level：
 */
public class Solution2 {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int num:nums)
            sum+=num;
        if((sum&1)!=0)  //奇数
            return false;
        int len=nums.length;
        sum=sum/2;
        Arrays.sort(nums); //是为了可以进行剪支，避免无用的查找
        return dfs(0,0,nums,sum,len-1); //从后往前找好些
    }

    private boolean dfs(int cur1, int cur2, int[] nums, int target, int index) {
        if(target<cur1||target<cur2||index==-1)
            return false;
        if (target==cur1||target==cur2)
            return true;
        return  dfs(cur1+nums[index],cur2,nums,target,index-1)||
                dfs(cur1,cur2+nums[index],nums,target,index-1);
    }

}
