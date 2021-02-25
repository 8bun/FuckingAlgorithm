package DP系列.线性DP.其他经典问题.鸡蛋掉落;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/9 12:04
 * @Description:
 *
 * 有n层楼，
 * 如果只有1个鸡蛋，那就只能一层一层的去试，一共要试n次
 * 如果有x个鸡蛋，那么第一个鸡蛋有n种扔法，可以在1-n的任意一层扔
 *     假设在第k层扔，那么有两种结果
 *         如果碎了，那么鸡蛋就减少1个，临界点F肯定在k之下。那问题就变成了 x - 1个鸡蛋，k - 1层楼需要多少次
 *         如果没碎，鸡蛋还是x个，F肯定在k之上。问题变成x个鸡蛋，n - k层需要多少次
 *         取最坏情况，即上面两种情况的最大值，然后还要加1，因为在第k层扔了一次。
 *     把k所有的取值都试一遍，找到最小值
 *
 * @Url: https://leetcode-cn.com/problems/super-egg-drop/
 * 分析过程：https://www.bilibili.com/video/BV1KE41137PK?from=search&seid=17973611971894816621
 * 图解函数：https://leetcode-cn.com/problems/super-egg-drop/solution/dong-tai-gui-hua-zhi-jie-shi-guan-fang-ti-jie-fang/
 * @限制:
 * 1 <= K <= 100
 * 1 <= N <= 10000
 * @Level:
 */
public class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp=new int[K+1][N+1]; //1<=i,1<=j
        for (int i=1;i<=N;i++){dp[1][i]=i;dp[0][i]=0;}
        for (int i=1;i<=K;i++){dp[i][1]=1;dp[i][0]=0;}

        for(int egg=2;egg<=K;egg++){

            for (int high=2;high<=N;high++){
                dp[egg][high]=Integer.MAX_VALUE;
//                for (int select=1;select<=high;select++){ //1....select....high，选择第select层扔，有两种可能，碎和不碎
                    //debug:观察 dp[egg - 1][select - 1] 和 dp[egg][high - select]，可以发现前者是随着select单调不减，
//                    if (egg==K&&high==N) {
//                        System.out.println("select=" + select + ",dp[egg-1][select-1]=" + dp[egg - 1][select - 1] + ",dp[egg][high-select]=" + dp[egg][high - select]);
//                    }
                    // 后者是随着select单调不增的，且每次变化的值至少为0，至多为1，所以存在某个值（不一定是整数）使得二者相等
                    //也就是说，我们在[1,high]之间找到某个select值target_select，这个target_select刚好是交点处
                    // 的select值或者当交点处的select不为整数时离交点最近的左边的整数,
                    //也就是说，这个target_select之前的所有整数select(并且它是最大的)，都能满足dp[egg - 1][select - 1]<=dp[egg][high - select]
                    //Math.min(dp[egg][high],Math.max(dp[egg-1][select-1],dp[egg][high-select])+1)这行代码是求
                    // 每个select时两个方程的较大值，然后再求这些最大值之中的最小值，所以其实就是求target_select(通过从select=1逐步模拟可以理解)
                    //相当于求上半部V形山谷值，可以二分查找来快速寻找这个点
                int target_select=binarySearch(1,high,egg,high,dp);

                dp[egg][high]=Math.min(dp[egg][high],Math.max(dp[egg-1][target_select-1],dp[egg][high-target_select])+1);
            }
        }
        return dp[K][N];
    }
    private int binarySearch(int left, int right, int egg, int high,int[][] dp){
        if (left==right) {
//            System.out.println("up= " + dp[egg - 1][left - 1]);
//            System.out.println("down=" + dp[egg][high - left]);
            return left;
        }
        int mid=(left+right)>>1;
        int up=dp[egg-1][mid-1];
        int down=dp[egg][high-mid];
        /**
         * 注意，最后返回的位置，它是位置最右的使得up<down或者位置最左的up>down
         * 或者up==down
         */
        if (up<down)
            return binarySearch(mid+1, right, egg, high, dp); //从mid+1开始，或许mid+1处就是位置最左的up>down也是正确的
        else if (up>down)
            return binarySearch(left,mid,egg,high,dp);//到mid结束，或许mid处就是位置最左的up>down也是正确的
        else return mid; //up==down
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res=new Solution().superEggDrop(100,10000);
        System.out.println(res);
    }
}
