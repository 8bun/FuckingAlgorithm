package 翻转单词顺序I;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 9:38
 * @level：
 */
public class Solution {
    public String reverseWords(String s) {
        String[] str=s.split(" ");
        StringBuilder builder=new StringBuilder();
        //split后，str有可能：
        //a
        //the
        //""
        //world
        for (int i=str.length-1;i>=0;i--){
            if(str[i].equals("")){
                continue;
            }
            builder.append(str[i]+" ");
        }
        return builder.toString().trim();
    }
}
