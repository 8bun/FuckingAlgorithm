package 数青蛙;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 15:05
 * @Description: croak是一只青蛙完整的叫，可以分散的叫
 * 统计各字母出现次数，有效的条件是顺序在后面的字母出现次数必须总是小于前面的，否则一定不是有效的字符串。举个例子，比如有字符串croakrcoak，
 * 统计到第五个字母之后，五个字母都各出现了一次，在第六个字母上，出现了r，发现在前面的c出现次数为1小于r的出现次数2，
 * 此时就可以确定不是有效的了。当有一只青蛙叫完五个字母后，这只青蛙又可以开始从头叫了，所以需要将各个字母的出现次数都减1，
 * 那么最终需要的青蛙最少数目就是各个字母出现次数的最大者（表示同一时间有多少只青蛙在叫）。
 * @Url: https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/
 * @限制:
 * @Level:
 */
public class Solution {
    /**
     * 统计当前遍历的字符串的各种字符的大小
     */
    private int c_count=0,r_count=0,o_count=0,a_count=0,k_count=0;

    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0)
            return -1;
        char[] chars = croakOfFrogs.toCharArray();

        int frog_need=0;
        for (char state:chars){
            switch (state){
                case 'c': c_count++;break;
                case 'r': r_count++;break;
                case 'o': o_count++;break;
                case 'a': a_count++;break;
                case 'k': //遇到一个k，前面的croak看成整体，统计青蛙
                    k_count++;
                    if (isValid(c_count,r_count,o_count,a_count,k_count)) { //因为有可能第一个就是k，所以要进行判断
                        frog_need = Math.max(frog_need, c_count); //无论在任何状态，c_count要么就是与其他的都相等，要么就是最大
                        c_count--;r_count--;o_count--;a_count--;k_count--;
                        continue;
                    }
                    break;
            }
            if (!isValid(c_count,r_count,o_count,a_count,k_count)) { //必须保持任意时刻（c>=r>=o>=a>=k）,才是正确的
                frog_need=-1;
                break;
            }
        }
        return frog_need;
    }
    private boolean isValid(int c_count, int r_count, int o_count, int a_count, int k_count){
        return c_count>=r_count && r_count>=o_count && o_count>=a_count && a_count>=k_count;
    }
    public static void main(String[] args) {
        int res=new Solution().minNumberOfFrogs("croacrokak");
        System.out.println(res);
        Scanner scanner = new Scanner(System.in);
    }
}
