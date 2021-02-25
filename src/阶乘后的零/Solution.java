package 阶乘后的零;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/17 19:37
 * @Description:
 * [1*2*..5] [6*7..10] [11*12..15] [16*..20] [21*22*..25] [26..30]...
 * =[...2..*5] [..2*5][..6*2..3*5][...5*2*2][11*2*...5*5,2个5，一个5可以又与另外一个2结合产生1个0末尾][...3*2*5]...
 * 所以2*5能凑成多少对，结果就有多少个0，因为5的个数肯定是比2少的，
 * 也就是直接算1*...n拆分因子相乘的式子中有多少个5就可以（每个[]单位为5，各自的乘积可以构成一个0，所以计算有多少个[]区间）
 * 用n/5就可以算出有多少个[]区间，然后因为每个区间还可能不止一个5，所以还得继续算n/5之后的还有多少个[]区间
 * @Url: https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * @限制:
 * @Level:
 */
public class Solution {

    public int trailingZeroes(int n) {
        if(n<5)
            return 0;
        return n/5+trailingZeroes(n/5);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
