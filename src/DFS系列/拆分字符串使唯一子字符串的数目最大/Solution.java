package DFS系列.拆分字符串使唯一子字符串的数目最大;


import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jack
 * @Date: 2020/9/24 13:44
 * @Description:
 * 回溯
 * @Url: https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 */
public class Solution {

    private int res;
    public int maxUniqueSplit(String s) {
        HashSet<String> pathSet = new HashSet<>();
        dfs(0, s, pathSet);
        return res;
    }

    public void dfs(int start,  String s, Set<String> curPath) {

        if(start == s.length()) {
            res = Math.max(res, curPath.size());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if(curPath.contains(sub)) continue;
            curPath.add(sub);
            dfs(i, s, curPath);
            curPath.remove(sub);
        }
    }

}

