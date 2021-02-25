package 竖直打印单词;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: Jack
 * @Date: 2020/5/25 22:37
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-172/problems/print-words-vertically/
 * @限制:
 * @Level:
 */
public class Solution {

    public List<String> printVertically(String s) {
        TreeMap<Integer, String> map = new TreeMap<>();
        String[] str = s.split("\\s");
        int maxLen = 0;
        for(String st : str)
            maxLen = Math.max(maxLen, st.length());
        System.out.println(maxLen);
        for(String st : str) {
            int i = 0;
            for(;i < st.length(); i++) {
                String val;
                if (map.get(i) == null) val = st.charAt(i) + "";
                else val = map.get(i) + st.charAt(i) + "";
                map.put(i, val);
            }
            while (i < maxLen) {
                String val;
                if (map.get(i) == null) val = " ";
                else val = map.get(i) + " ";
                map.put(i, val);
                i++;
            }
        }
        List<String> res = new ArrayList<>();
        for (int i : map.keySet()) {
            String cur = map.get(i);
            if (cur.trim().equals("")){
                res.add("");continue;
            }
            int index;
            for(index = cur.length() - 1; index >= 0; index--){
                if (cur.charAt(index) != ' ') break;
            }
            res.add(cur.substring(0,index+1));
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = new Solution().printVertically("CONTEST IS COMING");
        System.out.println(res.toString());
    }
}
