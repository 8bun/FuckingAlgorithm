package 字符串的排列II;

import java.util.Arrays;

/**
 * @description： 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * @url： https://leetcode-cn.com/problems/permutation-in-string/
 * @限制： 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * @author：Jack
 * @createTime：2020/4/11 21:11
 * @level：
 */
public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2)
            return false;
        int place_s1[] = new int[26];  //记录s1的情况
        for (int i = 0; i < len1; i++)
            place_s1[s1.charAt(i) - 'a']++;
        //为了在s1中匹配到s2的排列，定义窗口记录在s2中长度为s1长度的连续子串的情况
        //并使用place_s2数组记录该窗口的情况，如果place_s1记录情况和place_s1相同，那么返回true
        //要注意窗口在向右移动，左端之前的元素要从place_s2中清除
        //其实没必要去维护一个长度为s1长度的窗口

        int place_s2[] = new int[26]; //记录窗口的情况
        //使用right指针遍历s2，每次只要遍历到的下标大于等于len1（超过了s1的长度,right从0开始）
        //那么就让right-len1的元素在s2中减去1个计数，例如s1="abc",s2="ebcd"中的d(下标为3)，那么就处理一遍
        //让窗口的情况少去一个e（下标为0）的计数，表示滑动窗口现在没有记录e进来了，再把d统计进去，然后后面一直这样下去
        //直到窗口的情况数组与place_s1的情况数组一致
        for (int right = 0; right < len2; right++) {
            //如果遇到超过匹配的最大长度，先清理
            if (right >= len1)
                place_s2[s2.charAt(right - len1) - 'a']--;
            place_s2[s2.charAt(right) - 'a']++;
            if (Arrays.equals(place_s1, place_s2))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean res = new Solution().checkInclusion("ab", "eidbaooo");
        System.out.println(res);
    }
}
