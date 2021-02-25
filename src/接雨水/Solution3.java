package 接雨水;

/**
 * @description：
 * 对于前面的结论，某个位置i能接的雨水数与下面式子有关，
 * min(l_max, r_max) 。l_max是i的左边的最高，r_max是右边的最高，
 * 如果已经知道l_max < r_max 了， 那么⾄于这个 r_max 是不是右边最⼤不重要，重要的是 height[i] 能够装的⽔只和 l_max 有关
 * 也就是我们可以定义两个头尾指针left，right来遍历左和右区间，遍历过程不断更新l_max和r_max的值
 * left一直遍历到l_max>r_max
 * 直到指针left>right后停止
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 13:40
 * @level：
 */
public class Solution3 {
    public int trap(int[] height) {
        int ans=0;
        int len=height.length;
        if (len==0)
            return 0;
        int left=0,right=len-1;
        int l_max=height[left],r_max=height[right];
        while (left<=right){
            l_max=Math.max(l_max,height[left]);
            r_max=Math.max(r_max,height[right]);
            if(l_max<r_max)
                ans+=l_max-height[left++];
            else
                ans+=r_max-height[right--];
        }
        return ans;
    }
}
