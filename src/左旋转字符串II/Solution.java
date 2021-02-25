package 左旋转字符串II;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 9:52
 * @level：
 */
public class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n)+s.substring(0,n);
    }
}
