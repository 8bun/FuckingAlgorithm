package 单词拆分II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/28 10:49
 * @Description:
 * @Url: https://leetcode-cn.com/problems/word-break-ii/
 * @限制:
 * @Level:
 */
public class Solution {
    private List<String> res = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len=s.length();
        boolean[] dp=new boolean[len];
        for (int i=0;i<len;i++){ //dp[i]表示s[0]...s[i]能否成功拆分
            //s[0]...s[i]如果不进行拆分，也就是组成的字符串就存在于dict里面，那么dp[i]=true
            if (wordDict.contains(s.substring(0,i+1))){
                dp[i]=true;continue;
            }
            //如果对s[0]...s[i]进行拆分，并且分为s[0]..s[l],s[l+1]..s[i]两部分
            for (int l=0;l<i;l++){
                String suffix=s.substring(l+1,i+1);//后半部分
                if (dp[l]&&wordDict.contains(suffix)){
                    dp[i]=true;break; //满足条件之后跳出循环即可，因为只需要找到一种方案满足
                }
            }
        }
        if (dp[len - 1]) {
            LinkedList<String> queue = new LinkedList<>();
            backtrace(s, len - 1, wordDict, queue, dp);
            return res;
        }
        return res;
    }

    /**
     * 求s[0]...s[end]的组合情况：
     * 假设l是s[0]...s[end]中最靠近end的那个dp为true的下标
     * 也就是s[0]...s[l]...s[end]其中s[l+1]...s[end]不可拆分并且在字典里，
     * 这就相当于做了一种选择：使用最短的后缀来作为结果的最后一个String
     * 那么可以这样进行backtrace查找
     * backtrace(end)->backtrace(l)+String(s[l+1]...s[end])，其中l的范围是[0,end)
     * 其实还有其他选择，那就是使用更长的后缀来作为结果的最后一个String，只要这个后缀在字典里面
     * 所以这就涉及了回溯的框架使用
     *
     * 回溯搜索是深度优先搜索（DFS）的一种
     * 对于某一个搜索树来说（搜索树是起记录路径和状态判断的作用），回溯和DFS，
     * 其主要的区别是，回溯法在求解过程中不保留完整的树结构，而深度优先搜索则记下完整的搜索树。
     * @param s
     * @param end
     * @param wordDict
     * @param dp
     */
    private void backtrace(String s, int end, List<String> wordDict, LinkedList<String> cur_str, boolean[] dp) {
        if (end==-1) {
            StringBuilder builder = new StringBuilder();
            for (String str : cur_str) {
                builder.append(str);
                builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            res.add(builder.toString());
            return;
        }
        for(int l=end;l>=0;l--){
            String suffix=s.substring(l,end+1);
            if (wordDict.contains(suffix)){
                //选择后缀
                cur_str.addFirst(suffix);
                //对后缀前面字符串的进行搜索
                backtrace(s,l-1,wordDict,cur_str,dp);
                //不选择后缀
                cur_str.removeFirst();
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        wordDict.add("applepen");
        wordDict.add("pine");
        wordDict.add("pineapple");
        Solution solution2 = new Solution();
        List<String> res = solution2.wordBreak(s, wordDict);
        System.out.println(res);
    }
}
