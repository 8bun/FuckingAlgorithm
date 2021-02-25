package 第一个只出现一次的字符;

/**
 * @description：双100，采用字符对应的ASCII码作为下标进行计数，重新遍历数组，找到第一个为1的
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/5 11:04
 * @level：
 */
public class Solution2 {
    public char firstUniqChar(String s) {
        int count[]=new int[128];
        char[] chars=s.toCharArray();
        for(char ch:chars){
            count[(int)ch]++;
        }
        for(char ch:chars){
            if(count[(int)ch]==1){
                return ch;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().firstUniqChar("leetcode"));
    }
}
