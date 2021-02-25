package 最长公共前缀;

/**
 * @description：
 * 将原数组拆分成每两个为一组，分别匹配最长公共前缀，将匹配结果继续两两匹配。
 * 分治算法的基本思想：
 * (1) 将一个规模为N的问题分解为 K 个规模较小的子问题。
 * (2) 这些子问题相互独立且与原问题性质相同。
 * 比较慢
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 19:04
 * @level：
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len=strs.length;
        if(strs==null||len==0)
            return "";
        return FindLongest(strs,0,strs.length-1);
    }

    private String FindLongest(String[] strs, int begin, int end) {
        if(begin==end)
            return strs[begin];
        //只有两个比较
        if (begin + 1 == end) {
            return commonPrefix(strs[begin], strs[end]);
        } else {
            int mid = (begin + end) / 2;
            String left_prefix=FindLongest(strs, begin, mid);
            if(!"".equals(left_prefix))
                return commonPrefix(FindLongest(strs, begin, mid), FindLongest(strs, mid + 1, end));
            return "";
        }
    }

    /**
     * 采用相同前缀划去前缀的方式进行比较，不同的时候返回
     * @param a
     * @param b
     * @return
     */
    private String commonPrefix(String a,String b){

        if(a.compareTo(b)==0)
            return a;
        String prefix="";
        if(!"".equals(a) && !"".equals(b)) {
            if(a.length()>b.length()){
                String tmp=a;
                a=b;
                b=tmp;
            }
            while (a.length()>=1 && a.charAt(0) == b.charAt(0)) {
                prefix += String.valueOf(a.charAt(0));
                if(a.length()==1)
                    break;
                a=a.substring(1);
                b=b.substring(1);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs={"ac","ac","a","a"};
        System.out.println(new Solution().longestCommonPrefix(strs));
    }


}
