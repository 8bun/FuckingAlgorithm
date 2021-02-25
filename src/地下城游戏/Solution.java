package 地下城游戏;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 10:22
 * @Description:
 * 在任意时刻，骑士的生命值x都大于等于1
 * 由于题目求的是从arr[0][0]出发直到arr[len1-1][len2-1]的初始最小的血量
 * 那么我们可以使用dp[len1-1][len2-1]表示到达arr[len1-1][len2-1]之前最少血量
 * 例如
 * -2 -3  3
 * -5 -10 1
 * 10 30 -5
 * 那么到达右下角的-5之前所需的最少血量为6。
 * 所以可相应推出到达同一列和同一行的dp值
 * 例如-5上面的1的dp为5，也就是使用它下面的dp减去本身的1，如果减去<=0，那么至少保证存活，dp为1，比如这个1换成6
 * 然后再算出不在同一列不在同一行的dp，例如-10，
 * 那到达它之前的最少血量为11，因为
 * -10 1
 * 30 -5
 * （至少需要15）-10-->（至少需要5）1-->-5
 * （至少需要11）-10-->（至少需要1）30-->-5
 * 这个11就是用它的右边和下边的dp值减去它本身然后取较小值，如果减去<=0，那么也是取1
 * 从意义理解这个dp就是减去10后才能满足-10的下边和右边的需求，然后我们选择两种算出来的dp值的最小，成本最低
 * 最后返回dp[0][0]即可
 * @Url: https://leetcode-cn.com/problems/dungeon-game/
 * 思路解析：https://www.bilibili.com/video/BV1Q7411u7kB?from=search&seid=1267132013541585376
 * @限制: 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * @Level:
 */
public class Solution {

    public int calculateMinimumHP(int[][] dungeon) {
        int row=dungeon.length;
        int col=dungeon[0].length;
        int[][] dp=new int[row][col]; //dp[i][j]表示到达dungeon[i][j]之前的最少的生命值，注意是最少，因为从隔壁到[i][j]的路径有两种选择
        dp[row-1][col-1]=Math.max(1,1-dungeon[row-1][col-1]);
        for (int i=row-2,j=col-1;i>=0;i--)
            dp[i][j]=Math.max(1,dp[i+1][j]-dungeon[i][j]);
        for (int i=row-1,j=col-2;j>=0;j--)
            dp[i][j]=Math.max(1,dp[i][j+1]-dungeon[i][j]);
        for (int i=row-2;i>=0;i--){
            for (int j=col-2;j>=0;j--)
                dp[i][j]=Math.max(1,Math.min(dp[i][j+1],dp[i+1][j])-dungeon[i][j]);
        }
//        for (int i=0;i<row;i++){
//            for (int j=0;j<col;j++)
//                System.out.print(dp[i][j]+" ");
//            System.out.println();
//        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] arr={
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
        int res=new Solution().calculateMinimumHP(arr);
        System.out.println(res);
        Scanner scanner = new Scanner(System.in);
    }
}
