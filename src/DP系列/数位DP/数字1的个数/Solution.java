package DP系列.数位DP.数字1的个数;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/21 14:49
 * @Description:
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13。
 * @Url: https://leetcode-cn.com/problems/number-of-digit-one/
 * dalao们对数位dp介绍: https://blog.csdn.net/wust_zzwh/article/details/52100392，
 * https://www.luogu.com.cn/blog/virus2017/shuweidp
 * 题目思路来源：https://my.oschina.net/u/4382082/blog/4249495/
 * @限制:
 * @Level:
 */
public class Solution {
    private int[] digits;
    private static int[][] dp = new int[30][30];
    public int countDigitOne(int n) {
        digits = new int[String.valueOf(n).length() + 1];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        int pos = 0;
        while (n > 0){
            digits[++pos] = n % 10;
            n /= 10;
        }
        //第一位受限，所以需要为true
        return dfs(pos, true, 0);
    }

    /**
     * 这里写一下这个dfs和dp记忆的含义：
     * 如n=1200
     * digits = [0,0,0,2,1],digits[1]=0,digits[2]=0,digits[3]=2,digits[4]=1
     * 也就是说
     * 数字    0 0 2 1  (digits[])
     * 第几位  1 2 3 4  (pos,len=4)
     *
     * 调用dfs(pos, true, 0)开始：
     * dfs(4,true,0),limit=true，受限，curPos只能从(0,1)选择
     * dfs(4,true,0)=dfs(3,false,0)+dfs(3,true,1)，确定好pos=4的位填的数后，也就是总共两个选择分支,一个1开头，一个0开头
     * 也就是从高位(开始画分支)，然后再确定下一个位，每个分支又独自分支选择。
     * 当到了curPos=2时,
     * 如果1(pos=4)那个分支下一位选了0(pos=3,传给dfs(2)的limit=false),
     * 如果0(pos=4)那个分支下一位选了1(pos=3,传给dfs(2)的limit=false)
     * 它们都会调用dfs(2)，这时就要注意了：
     * 在dfs(pos=2)前就已经确定了第3~4位(实际上是四位数字的高两位)是“01”（即cntOne=1，出现了一个“1”）, 那么第1位到第2位上就可以从00枚举到99，
     * 那么它们都会同时调用的dfs(2)具体是dfs(2,false,1),它的返回值就是0100到0199出现了多少个“1”和1000到1099出现了多少个“1”。
     * 我们发现，它们的个数完全一样，因为第1位（数字的第4位）到第2位（数字的第3位）上从00枚举到99，
     * 那么“0100到0199”和“1000到1099”出现的“1”的个数是一样的。
     * 那么就算重复了，所以就可以用dp来记录,它们都被记录在dp[2][1]中，表示在调用dfs(2,false,1)时的返回值，后面
     * 有相同的调用就会直接返回该记录的值,节省了大量时间。
     * 所以，可以说dp[pos][cnt]的值就等于dfs(pos,false,cnt)；
     * 只要你算过一次dfs(pos,false,cnt)，就用dp[pos][cnt]给你记录下来，在没有上界限制的情况下，可以直接使用dp[pos][cnt]，
     * 而不用再去重复算一次dfs(pos,false,cnt)
     *
     * @param curPos 待选择数字的当前位置
     * @param limit 当前位是否受限，受限的意思是是否得取n在当前位的数字以内的数字，不受限即可取9以内的数字，起到限制取值的作用
     * @param cntOne 在前面的数字digits[]和第几位的关系中，pos在[curPos+1,len]内已经统计了cntOne个'1'
     *               也就是前面的高位已经统计了多少了'1'
     *               每一次dfs(curPos-1)的调用，都使得pos越来越小，最终在curPos=0时返回结果，结合前面的含义，
     *               也就是返回了[1,len]内已经统计cntOne个'1'的统计数cntOne，刚好就是我们要求的值
     * @return
     */
    private int dfs(int curPos, boolean limit, int cntOne){
        if (curPos == 0) return cntOne;

        if (!limit && dp[curPos][cntOne] != -1) {//如果不受限制并且值已经被记录过了

            return dp[curPos][cntOne];
        }

        int ret = 0;
        int max_num = limit ? digits[curPos] : 9; //当前位能取到的最大值

        //暴力枚举第curPos位上的数
        for (int i = 0; i <= max_num; i++)
            ret += dfs(curPos - 1, limit && (i == max_num), cntOne + (i == 1 ? 1 : 0));

        if (!limit)  //如果curPos时不受限制，那么dp[curPos][cntOne]记录方案数再返回
            dp[curPos][cntOne] = ret;

        return ret;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res = new Solution().countDigitOne(13);
        System.out.println(res);
//        for (int i = 0; i < 30; i++){
//            for (int j = 0; j < 30; j++){
//                if (dp[i][j] == -1) continue;
//                System.out.print(i+","+j+":"+dp[i][j]+"  ");
//            }
//            System.out.println();
//        }
    }
}
