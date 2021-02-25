package 期望个数统计;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 22:47
 * @Description:
 * @Url: https://leetcode-cn.com/problems/qi-wang-ge-shu-tong-ji/solution/c-zhi-xu-yao-ji-suan-bu-tong-neng-li-zhi-de-shu-li/
 * @限制:
 * @Level:
 */
public class Solution {

    public int expectNumber(int[] scores) {
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<scores.length;i++){
            set.add(scores[i]);
        }
        return set.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
