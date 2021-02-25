package 加油站;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/24 11:44
 * @Description:
 * @Url: https://leetcode-cn.com/problems/gas-station/
 * https://leetcode-cn.com/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
 * @限制:
 * @Level:
 */
public class Solution {
    //暴力写法，遍历每个站为出发站，看是否能回到原点
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasNum=gas.length;
        for (int start=0;start<gasNum;start++){
            int get=start;
            int remain=gas[start];
            while (remain-cost[get]>=0){ //=时表示剩余油量刚好够开到下一站
                remain=remain-cost[get]+gas[(get+1)%gasNum];
                get=(get+1)%gasNum;
                if (get==start) return start;
            }
        }
        return -1;
    }

    /**
     * 当考虑 i 能到达的最远的时候，假设是 j。
     * 那么 i + 1 到 j 之间的节点是不是就都不可能绕一圈了？
     * 证明为什么不可以：
     * 假设 i + 1 的节点能绕一圈，那么就意味着从 i + 1 开始一定能到达 j + 1。
     * 又因为从 i 能到达 i + 1，所以从 i 也能到达 j + 1。
     * 但事实上，i 最远到达 j 。产生矛盾，所以 i + 1 的节点一定不能绕一圈。同理，其他的也是一样的证明。
     * 所以下一次的 i 我们不需要从 i + 1 开始考虑，直接从 j + 1 开始考虑即可。
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int gasNum=gas.length;
        for (int start=0;start<gasNum;start++){
            int get=start;
            int remain=gas[start];
            //尝试模拟
            while (remain-cost[get]>=0){ //=时表示剩余油量刚好够开到下一站
                remain=remain-cost[get]+gas[(get+1)%gasNum];
                get=(get+1)%gasNum;
                if (get==start) return start;
            }
            //模拟失败
            //start不能到get
            if (get<start) //最远距离绕到了之前，所以 i 后边的都不可能绕一圈了
                return -1;
            start=get;//start从 get+1开始考虑（for循环会+1）
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
