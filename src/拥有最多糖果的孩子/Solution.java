package 拥有最多糖果的孩子;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 23:06
 * @Description:
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-25/problems/kids-with-the-greatest-number-of-candies/
 * @限制:
 * @Level:
 */
public class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>((o1, o2) -> o2-o1);
        List<Boolean> res=new ArrayList<>();
        for (int i=0;i<candies.length;i++){
            priorityQueue.add(candies[i]);
        }
        for (int i=0;i<candies.length;i++){
            int sum=candies[i]+extraCandies;
            if (sum>=priorityQueue.peek())
                res.add(true);
            else res.add(false);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
