package 接雨水;

/**
 * @description：
 * 我们知道，能接到雨水的条件是某个区间，而且这个区间的趋势是先降后升才能接到，
 * 而某个位置i的能接到的雨水量为① min(l_max,r_max)-height[i]
 * 如果我们使用数组记录l_max[i]和r_max[i]就不用遍历很多无用的次数
 * 假设要求l_max[i]：位置 i 左边最⾼的柱⼦⾼度
 * 那么它跟位置i-1左边最高的柱子高度l_max[i-1]有关系
 * 而且还取决于它本身的高度，如果它出现了比前一个的l_max还要高的，那么，这个位置就无法存水了
 * 也就是看成l_max[i]=height[i]，（如果它是最高的，也就是r_max[i]=height[i]，那么式①变成0，符合定义）如果小的话，那么就是取l_max[i-1]
 * 同理，r_max也是一样的求法由于我们要先知道位置i右边的r_max，那么要从右开始遍历
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 13:13
 * @level：
 */
public class Solution2 {

    public int trap(int[] height) {
        int ans=0;
        int len=height.length;
        if (len==0)
            return 0;
        int[] l_max=new int[len];
        int[] r_max=new int[len];
        l_max[0]=height[0];//位置i处，它的左边的最高的柱子
        r_max[len-1]=height[len-1];
        for (int i=1;i<len;i++)
            l_max[i]=Math.max(l_max[i-1],height[i]);
        for (int i=len-2;i>=0;i--)
            r_max[i]=Math.max(r_max[i+1],height[i]);
        for (int i=0;i<len;i++)
            ans+=Math.min(r_max[i],l_max[i])-height[i];
        return ans;
    }
}
