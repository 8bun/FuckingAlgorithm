package 面试常问.字符串A中删除字符串B中所有字符;

import java.util.Hashtable;

/**
 * @description：
 * 输入两个字符串，从第一字符串中删除第二个字符串中所有的字符。
 * 例如，输入A串”They are students.”和B串”aeiou”，则删除之后的第一个字符串变成”Thy r stdnts.”
 * 使用指针遍历+hashTable来解决，
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/13 10:54
 * @level：
 */
public class DeleteChar {


    public String delete(String a,String b){
        Hashtable<Character,Boolean> hashtable=new Hashtable<>();
        if(a.isEmpty()||b.isEmpty())
            return null;
        for(char ch:b.toCharArray()){
            if(ch!=' ')
                hashtable.put(ch,true);
        }
        int fast=0; //遍历b串的指针
        StringBuilder res=new StringBuilder();
        char[] chars=a.toCharArray();
        for(;fast<chars.length;){
            char ch=chars[fast];
            if(hashtable.containsKey(ch)){
                fast++;
            }
            else {
                res.append(ch);
                fast++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DeleteChar().delete(" They are students."," aeiou"));
    }
}
