package 接雨水;

/**
 * @description：
 * 暴力做法：
 * 除第一个位置和最后一个位置外，其他所有位置能接的雨水高度=min(l_max,r_max)-height[i]
 * 位置 i 能达到的⽔柱⾼度和其左边
 * 的最⾼柱⼦、 右边的最⾼柱⼦有关， 我们分别称这两个柱⼦⾼度为 l_max 和 r_max
 * @url： https://leetcode-cn.com/problems/trapping-rain-water/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 9:24
 * @level：
 */
public class Solution {
    public int trap(int[] height) {
        int ans=0;
        int len=height.length;

        for (int i=1;i<len-1;i++){  //求i处柱子能接的雨水长度
            int l_max=0,r_max=0;
            //求l_max，从i开始，因为有可能以height[i]是最高的，左右依次递减
            for(int j=i;j>=0;j--){
                l_max=Math.max(l_max,height[j]);
            }
            for (int j=i;j<len;j++){
                r_max=Math.max(r_max,height[j]);
            }
            ans+=Math.min(l_max,r_max)-height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Solution().trap(height));
    }
}
