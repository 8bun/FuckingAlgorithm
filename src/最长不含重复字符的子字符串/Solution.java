package 最长不含重复字符的子字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @description： 使用一个窗口[head,tail]维护一个没有重复的子串，使用Set维护该窗口的所有字符
 * 如果tail此时访问到的元素在窗口中还没有出现，那么就继续扩大窗口的范围,head不变，tail++
 * 如果tail此时访问到的元素在窗口中已经出现过，那么就要改变head，abca
 * @url： https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 * @限制：
 * @author： Jack
 * @createTime： 2020/3/4 20:09
 * @level：
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window=new HashSet<>();
        int max_len=0;
        for( int head=0,tail=0;tail<s.length();tail++) {
            Character ch = s.charAt(tail);  //当前被访问的元素
            //如果有重复的话，就移除最左边的节点，一直到没有重复的存在。
            //例如"pabcakew",head=0,tail=4,ch='a'时进入循环,
            //要将head右移到b(head=2)的位置，窗口才能不出现重复
            //而之前窗口有的p、a元素都要从Set中去除，用head来移动进行删除即可，
            //head指向p时，还是存在重复元素a的，head指向a时，还是存在a的，所以都进行
            //一一去除，然后head指向b，此时就没有重复元素a了
            while (window.contains(ch)){
                //System.out.println("包含了字符："+ch+"删除了位于"+head+"的字符:"+s.charAt(head));
                window.remove(s.charAt(head));
                head++;
            }
            //此时[head,tail-1]没有ch，要加上
            window.add(ch);
            //窗口无重复字符，更新最大长度
            max_len=Math.max(max_len,tail-head+1);
        }
        return max_len;
    }
}
