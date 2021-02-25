package Java基础.二进制在算法中的应用.循环码排列;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/11 09:45
 * @Description:
 * 按第一位为0，生成格雷编码，然后把start（包括之后的所有）放到最前，其他右移即可
 * @Url: https://leetcode-cn.com/problems/circular-permutation-in-binary-representation/comments/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public List<Integer> circularPermutation(int n, int start) {

        List<Integer> res = new ArrayList<>();
        List<Integer> after = new ArrayList<>();

        int m = (int) Math.pow(2, n);
        boolean flag = false;

        for (int i = 0; i < m; i++) {
            int p = i ^ (i / 2);
            if (p == start) flag = true;
            if (flag) res.add(p);
            else after.add(p);
        }
        res.addAll(after);
        return res;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
