package 最简分数;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/16 22:43
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        if (n == 1) return res;
        for (int i = 2; i <= n; i++){ //4
            for (int j = 1; j < i; j++){
                System.out.println("i "+i+" j"+j);
                if (j%i==0)continue;
                if (method_one(i,j) != 1)continue;
                res.add(j+"/"+i);
            }
        }
        return res;
    }

    public static int method_one(int a, int b){
        return a%b==0 ? b : method_one(b, a%b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =4;
        List<String> res = new Solution().simplifiedFractions(n);
        System.out.println(res.toString());
    }
}
