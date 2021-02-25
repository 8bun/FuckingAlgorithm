package 前缀树系列.恢复空格;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/9 15:57
 * @Description:
 * dp[i] 表示字符串的前 i 个字符的最少未匹配数。
 * 为字典构建后缀树,题解见官方
 * @Url:  https://leetcode-cn.com/problems/re-space-lcci/solution/hui-fu-kong-ge-by-leetcode-solution/
 * @限制: 
 * @Level: 
 */
public class Solution {

    class TireNode {
        TireNode[] next;
        boolean isEnd;

        public TireNode() {
            next = new TireNode[26];
            isEnd = false;
        }
        public void insert(String s) {
            TireNode cur = this;
            //构建单词s的后缀树
            for (int i = s.length() - 1; i >= 0; i--) {
                int t = s.charAt(i) - 'a';
                if (cur.next[t] == null) cur.next[t] = new TireNode();
                cur = cur.next[t];
            }
            cur.isEnd = true;
        }
    }

    // dictionary = ["looked","just","like","her","brother"]
    // sentence = "jesslookedjustliketimherbrother"

    public int respace(String[] dictionary, String sentence) {

        TireNode root = new TireNode();
        for (String d : dictionary) {
            root.insert(d);
        }
        int n = sentence.length();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++) {

            dp[i] = dp[i - 1] + 1;

            TireNode tireNode = root;
            for (int j = i; j >= 1; --j) {
                int t = sentence.charAt(j - 1) - 'a';
                if (tireNode.next[t] == null) {
                    break;
                } else if (tireNode.next[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                tireNode = tireNode.next[t];
            }
        }
        return dp[n];
    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String[] d = {"looked","just","like","her","brother"};
        String s = "jesslookedjustliketimherbrother";
        int res = new Solution().respace(d, s);
        System.out.println(res);
    }
}
