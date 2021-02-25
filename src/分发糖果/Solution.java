package 分发糖果;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @Author: Jack
 * @Date: 2020/4/22 13:30
 * @Description:
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *
 * @Url: https://www.leetcode-cn.com/problems/candy/
 * https://www.bilibili.com/video/BV1W7411t7tk?from=search&seid=5154466916325102398下面代码的做法
 * https://leetcode-cn.com/problems/candy/solution/candy-cong-zuo-zhi-you-cong-you-zhi-zuo-qu-zui-da-/求最大值做法
 * @限制:
 * @Level:
 */
public class Solution {

    public int candy(int[] ratings) {
        int len=ratings.length;
        if (len==0)
            return 0;
        int[] res=new int[len];
        Arrays.fill(res,1);

        //看左边评分的情况，从第二个开始往右遍历
        for (int i=1;i<len;i++){
            if (ratings[i]>ratings[i-1])//对分数小的糖果数+1更新给邻居
                res[i]=res[i-1]+1;
//            System.out.print(res[i-1]+" ");
        }
//        System.out.println(res[len-1]);
        int cnt=0;
        //看右边评分的情况，从倒数第二个开始往左遍历
        for (int i=len-2;i>=0;i--){
            if (ratings[i]>ratings[i+1]&&res[i]<=res[i+1])//对分数小的糖果数+1更新给分数大的邻居
                res[i]=res[i+1]+1;
            cnt+=res[i+1];
        }
        return cnt+res[0];
    }
    //求最大值做法
    public int candy1(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i++)
            if(ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ratings={5,7,8,3,4,2,1};
        int cnt=new Solution().candy(ratings);
        System.out.println(cnt);
    }
}
