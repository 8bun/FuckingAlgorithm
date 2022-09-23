package 递归系列.电话号码的字母组合;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwq
 * @since 2021/03/23
 * @link https://www.cnblogs.com/grandyang/p/4452220.html
 */

public class Solution {
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return res;
        }
        String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinationsDFS(digits, dict, 0, "");
        return res;
    }

    private void letterCombinationsDFS(String digits, String[] dict, int curLevel, String curStr) {
        if(curLevel == digits.length()) {
            res.add(curStr);
            return;
        }
        String selectStr = dict[digits.charAt(curLevel) - '0'];
        for(int i = 0; i < selectStr.length(); i++) {
            letterCombinationsDFS(digits, dict, curLevel + 1, curStr + selectStr.charAt(i));
        }
    }
}
