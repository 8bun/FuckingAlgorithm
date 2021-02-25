package 替换空格;

/**
 * StringBuilder追加字符串
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
class Solution {
    public String replaceSpace(String s) {
        StringBuilder stringBuilder=new StringBuilder();
       for(char ch:s.toCharArray()){
           if(ch==' ')
               stringBuilder.append("%20");
           else
               stringBuilder.append(ch);
       }
       return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().replaceSpace("We are happy."));
    }
}
