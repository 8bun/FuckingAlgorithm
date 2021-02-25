package 第一个只出现一次的字符;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description：
 * 1、先用HashMap扫一遍记录下出现次数
 * 2、找出第一次出现的字符
 * @url： https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 * @限制： 0 <= s 的长度 <= 50000
 * @author：Jack
 * @createTime：2020/3/5 9:43
 * @level： 简单
 */
public class Solution {
    public char firstUniqChar(String s) {
        //暴力搜索法，
        // 执行用时 :1570 ms, 在所有 Java 提交中击败了5.11%的用户
        //内存消耗 :41.9 MB, 在所有 Java 提交中击败了100.00%的用户
        Map<Character,Integer> counter=new LinkedHashMap<>();
        char[] chars=s.toCharArray();
        for(char ch:chars){
//            System.out.println("s "+ch);
            if(counter.containsKey(ch))
                counter.put(ch,counter.get(ch)+1);
            else counter.put(ch,1);
        }
        /**
         *   Hashtable.keySet()          降序
         *   TreeMap.keySet()            升序
         *   HashMap.keySet()            乱序
         *   LinkedHashMap.keySet()      原序
         */
        for(char ch:counter.keySet()){
//            System.out.println("set "+ch);
            if(counter.get(ch)==1)
                return ch;
        }
        return ' ';
    }
}
