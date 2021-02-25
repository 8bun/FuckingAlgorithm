package 滑动窗口系列.定长子串中元音的最大数目;

/**
 * @Author: Jack
 * @Date: 2020/5/24 10:44
 * @Description: 固定长度的滑动窗口问题
 * 超时及其分析：
 * 1 <= s.length <= 10^5
 * 1 <= k <= s.length
 * 如果每次滑动窗口后都从窗口左边开始向右重新计算一次aeiou的数量，总共需要这样计算s-k次，那么时间复杂度
 * 将是O((s-k)*k),也就是10^10级别（O(n^2)），由于一般计算机一秒能运行 5 x 10^8次计算左右
 * 那么时间将超过1s了（一般C++限时1s）
 * 所以，根据经验：要保证不超时，
 * 一般 O(n)的算法数据范围n < 10^8。
 * O(n*logn)的算法数据范围n <= 10^6。(10^7左右)
 * O(n*sqrt(n))的算法数据范围n < 10^5。(n=99999时，值为31,622,302)
 * O(n^2)的算法数据范围n< 5000。(4999^2=24,990,001)
 * O(n^3)的算法数据范围n < 300 (299^3=26,730,899‬)。
 * O(2^n)的算法数据范围n < 25。(2^25 = 16,777,216)
 * O(n!)的算法数据范围n < 11。(10! = 3,628,800, 11! = 39,916,800‬)
 *
 * 所以本题可以这样做，每次窗口只向右移动1格，只计算加进来的1个和减掉的头部一个即可，这样
 * 除了第一次遍历k次之外，时间O(k)，其他时间都是线性的计算两个而已，总的大概是O(n)的时间复杂度
 * @Url: https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * @限制:
 * @Level:
 */
public class Solution {
    public int maxVowels(String s, int k) {
        String tmp = "aeiou";
        int ans = 0;
        //统计第一个k长度的区间[0,k-1]有多少个元音
        for (int i = 0; i < k; i++)
            ans += c2i(s.charAt(i));

        if (ans == k) return k;
        //统计以[k,n-1]为末尾的长度为k的区间有多少个元音
        //以k为末尾为例，它的元音统计就是(以k-1为末尾的长度为k的区间[0,k-1]的统计少了开头s[0]，多了s[k])的统计
        int sum = ans;
        for (int i = k; i < s.length(); i++){
            sum += (c2i(s.charAt(i))-c2i(s.charAt(i - k)));
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private int c2i(char x){
        String tmp = "aeiou";
        if (tmp.contains(x+"")) return 1;
        return 0;
    }


    public static void main(String[] args) {
        int res = new Solution().maxVowels("aeiou", 2);
        System.out.println(res);
    }
}
