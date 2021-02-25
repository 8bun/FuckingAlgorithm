package 规律题系列.灯泡开关IV;
/**
 * @Author: Jack
 * @Date: 2020/7/27 09:52
 * @Description:
 * 从头比到尾部，不管后面任何的反转
 * 例子：10111
 * 00000
 * （第*位不同，表示与前一位的数比较）
 * -》【1】1111（第一位不同，变换1次）
 * -》1【0】000（第二位不同，变换第2次）
 * -》10【1】11（第三位不同，变换第3次）
 * -》101【1】 1（第四位相同，不变换）
 * -》1011【1】（第五位相同，不变换）
 * 共变换3次，因此整体思路为：找出01切换的次数。
 * @Url: https://leetcode-cn.com/problems/bulb-switcher-iv/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int minFlips(String target) {
        char val = '0';
        int ans = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != val) {
                val = target.charAt(i);
                ans++;
            }
        }
        return ans;
    }
}
