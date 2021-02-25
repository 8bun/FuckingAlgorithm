package Java基础.二进制在算法中的应用.格雷编码;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/11 09:38
 * @Description: 格雷编码生成过程
 * G(i) = i ^ (i/2)
 * 如 n = 3:
 * G(0) = 000,
 * G(1) = 1 ^ 0 = 001 ^ 000 = 001
 * G(2) = 2 ^ 1 = 010 ^ 001 = 011
 * G(3) = 3 ^ 1 = 011 ^ 001 = 010
 * G(4) = 4 ^ 2 = 100 ^ 010 = 110
 * G(5) = 5 ^ 2 = 101 ^ 010 = 111
 * G(6) = 6 ^ 3 = 110 ^ 011 = 101
 * G(7) = 7 ^ 3 = 111 ^ 011 = 100
 * @Url:  https://leetcode-cn.com/problems/gray-code/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();

        int m = (int) Math.pow(2, n);
        for (int i = 0; i < m; i++) {
            res.add(i ^ (i / 2));
        }
        return res;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
