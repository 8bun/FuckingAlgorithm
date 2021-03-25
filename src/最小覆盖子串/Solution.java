package 最小覆盖子串;

/**
 * 滑动窗口
 *
 * @author cwq
 * @link https://leetcode-cn.com/problems/minimum-window-substring/
 * @since 2021/03/25
 */

public class Solution {
    public String minWindow(String s, String t) {
        int[] letterNeed = new int[128];
        int left = 0, cnt = 0, minLeft = - 1, minLen = Integer.MAX_VALUE;
        // letterNeed记录当前需要的字母个数，初始的所有非0值之和等于t.length()
        for (char ch : t.toCharArray()) {
            ++ letterNeed[ch];
        }
        for (int i = 0; i < s.length(); i++) {
            // 减少了一个'需要'的字母，窗口内已找到需要的cnt+1（如果letterCnt-1<0则不需要了）
            if (-- letterNeed[s.charAt(i)] >= 0) {
                ++ cnt;
            }
            while (cnt == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    minLeft = left;
                }
                // 窗口右移前，如果letterNeed[s.charAt(left)]之前需求为0个，需求增加，并且已收集需要的cnt--
                if (++ letterNeed[s.charAt(left)] == 1) {
                    -- cnt;
                }
                ++ left;
            }
        }
        return minLeft == - 1 ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        String res = new Solution().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }
}
