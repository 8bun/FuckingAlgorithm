package 罗马数字转整数;

import java.util.HashMap;

/**
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/roman-to-integer/
 */

public class Solution {

    public int romanToInt(String s) {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            // VI -> 5+1
            if(i == s.length() - 1 || map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
                res += val;
            }
            // IV -> 0-1+5
            else {
                res -= val;
            }
        }
        return res;
    }
}
