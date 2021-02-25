package DP系列.状态压缩DP.我能赢吗;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/14 10:08
 * @Description:
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏的每轮都选择对他最有利的数，不是随机抽）？
 * （两个玩家累计整数和）
 * 例：
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 输出：
 * false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 * 也就是说“稳赢”指的是，假设有两个人A, B，A先抽整数然后轮到B，再轮到A...，每次都根据要凑的
 * 和选择最有利于自己赢的数，如果存在 A走一步之后, B无论怎么走能还是A能赢的情况，那么就说A能稳赢（而不是所有的情况下）
 * 例如maxChoosableInteger = 10,desiredTotal=3
 * 那么就存在A抽3，然后B抽其他数都赢不了的情况，因为A已经赢了（可以理解为B就不用再抽了）,所以A可以稳赢
 * 又如：maxChoosableInteger = 10，desiredTotal = 11
 * 那么A第一轮选1(此时选得越小就越有利于自己赢),那么第二个玩家可以选择10（此时选择11-1就有利于自己赢）来赢得胜利，那么A就不能稳赢了
 * @Url: https://leetcode-cn.com/problems/can-i-win/
 * https://www.bilibili.com/video/BV1ox411j74S?from=search&seid=12333115506452428115
 * @限制:
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300
 * 玩家不能重复使用整数
 * @Level:
 */
public class Solution {

    /**
     * 记忆化回溯（也称为递归+备忘录），自顶向下
     * 采用记忆化后的时间复杂度为O(2^n)（如果不进行记忆的话，时间复杂度将是O(n!)），可以理解为已经缩成了只有一个分支了
     * 然后为什么要进行记忆化：
     * 因为我们发现，例如[2,3]和[3,2]之后的玩家选择状态都是一样的，都是可以从除了2,3之外的
     * 数字进行选择，那么就可以对选择2和3后第一个玩家能不能赢进行记忆存储
     * 这里采用state[]数组存储每个数字是否都被选过，选过则记录为1，然后我们将state.toString()
     * 使得[2,3]和[3,2]它们的结果都是一样的"0011"，作为key，存储在HashMap中，value是选了2和3
     * 之后第一个玩家是否稳赢
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) return true;
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false; //1,..maxChoosable数列总和都比目标和小
        int[] state = new int[maxChoosableInteger + 1];  //state[1]=1表示1被选了

        return backtraceWitMemo(state, desiredTotal, new HashMap<String, Boolean>());
    }

    private boolean backtraceWitMemo(int[] state, int desiredTotal, HashMap<String, Boolean> map) {
        String key = Arrays.toString(state); //这里比较关键，如何表示这个唯一的状态，例如[2,3]和[3,2]都是"0011"，状态一样
        if (map.containsKey(key)) return map.get(key);  //如果已经记忆了这样下去的输赢结果,记忆是为了防止如[2,3]，[3,2]之后的[1,4,5,..]这个选择区间被重复计算

        for (int i = 1; i < state.length; i++){
            if (state[i] == 0){ //如果这个数字i还没有被选中
                state[i] = 1;
                //如果当前选了i已经赢了或者选了i还没赢但是后面对方选择输了
                if (desiredTotal - i <= 0 || !backtraceWitMemo(state, desiredTotal - i, map)) {
                    map.put(key, true);
                    state[i] = 0; //在返回之前回溯
                    return true;
                }
                //如果不能赢也要记得回溯
                state[i] = 0;
            }
        }
        //如果都赢不了
        map.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
