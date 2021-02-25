package 字符串的排列;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @description: 输入一个字符串，打印出该字符串中字符的所有排列。
 * 其实这样想，例如输入字符串abc，通过“交换”的思想来解决：
 * 我们要依次求出三个位置的可能出现的字符（依次确定）
 * 求第一个位置可能出现的字符：将第一个位置字符a与后面的b、c分别交换（当然a与a交换，得到的仍是a），得到第一个位置可能出现的字符为a、b、c
 * 当交换完一次之后，就说明第一个位置的字符已经确定了，那么就去确定下一个字符(index+1)的位置，进入下一次的递归
 * 比如交换完了a和b,那么字符串第一个位置确定为了b，那么去确定下一个位置的字符，也就是a和c进行交换，
 * 得到第二个位置的字符，找到之后，假设是a，那么字符串变成了ba_，然后继续搜索下一个位置的字符，肯定是c，
 * 那么此时退出递归，进入搜索第一个位置的其他情况，此时，a已经到了index+1的位置(也可能到了index+n的位置)，那么如果还要实现index处的位置
 * 还是a，那么我们就要把a交换回来。
 * @url: https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 * @author: Jack
 * @createTime: 2020/3/1 19:06
 * @level: 中等
 */

public class Solution {

    private ArrayList<String> res;
    private String s;
    private char[] ch;

    public String[] permutation(String s) {
        this.ch = s.toCharArray();
        this.res = new ArrayList<>();
        this.s = s;
        FindChar(0);
        res = new ArrayList<>(new HashSet<>(res));  //去重
        return res.toArray(new String[res.size()]);
    }
    
    //寻找place位置要确定的字符
    private void FindChar(int place) {
        if (place == s.length() - 1) {
            res.add(String.valueOf(ch));
        } else {
            //将ch[place]字符与[place,len-1]位置的字符进行交换
            for (int i = place; i < s.length(); i++) {
                //交换一次，确定了place位置的字符
                char tmp = ch[i];
                ch[i] = ch[place];
                ch[place] = tmp;
                //寻找place+1位置的字符
                FindChar(place + 1);
                //通过上面的递归已经找到一种排列，将原来place位置的字符交换回来，继续下一次的i++ for循环，实现了
                //仍然是将ch[place]字符与[place,len-1]位置的字符进行交换
                tmp = ch[i];
                ch[i] = ch[place];
                ch[place] = tmp;
            }
        }
    }
}

