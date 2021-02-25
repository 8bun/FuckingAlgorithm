package 前缀树系列.回文对;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Jack
 * @Date: 2020/4/29 14:02
 * @Description: 这里没有用到前缀树，根据规律
 * 思路为：
 * 数组里有空字符串，并且数组里还有自己就是回文的字符串，每出现一个可与空字符串组成两对。
 * 如果自己的翻转后的字符串也在数组里，可以组成一对，注意翻转后不能是自己。
 * 如果某个字符串能找到一个分割点，分割点前的部分是回文，后半部分翻转后也在数组里，可组成一对。
 * 把3反过来，如果后部分是回文，前半部分翻转后在数组里，可组成一对。
 * @Url: https://leetcode-cn.com/problems/palindrome-pairs/
 * @限制:
 * @Level:
 */
public class Solution {
    //保存字符串及下标，便于快速查找
    private HashMap<String,Integer> wordMap=new HashMap<>();

    private List<List<Integer>> res=new ArrayList<>();
    //保存words数组中所有回文字符串的下标
    private List<Integer> palindromeIndexList=new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        init(words);

        String rev="";

        for (int i=0;i<words.length;i++){
            rev=reverse(words[i]); //当前字符串的反转
            //当前为空字符串,并且数组里还有本身就是回文串的字符串，每出现一个可与空字符串前后组合组成两对。
            if ("".equals(words[i])){
                for (int j=0;j<palindromeIndexList.size();j++){
                    if (i==palindromeIndexList.get(j))
                        continue;
                    List<Integer> list1=new ArrayList<>();
                    list1.add(i);
                    list1.add(palindromeIndexList.get(j));
                    List<Integer> list2=new ArrayList<>();
                    list2.add(palindromeIndexList.get(j));
                    list2.add(i);
                    res.add(list1);
                    res.add(list2);
                }
            }
            //长度为1的处理，因为后面的else对字符串进行了两部分的划分，并且每个部分长度至少为1
            if (words[i].length()==1){
                for (int k=0;k<words.length;k++){
                    if (k==i) continue;
                    //例如words[i]=a,则aa,ac,ca,acc,bba..等以a开头或结尾的字符串都可以和a组合成回文串
                    if (words[k].startsWith(words[i])||words[k].endsWith(words[i])){
                        if (isPalindrome(words[i]+words[k])){
                            List<Integer> list=new ArrayList<>();
                            list.add(i);list.add(k);res.add(list);
                        }
                        if (isPalindrome(words[k]+words[i])){
                            List<Integer> list=new ArrayList<>();
                            list.add(k);list.add(i);res.add(list);
                        }
                    }
                }
            }
            else if (words[i].length()>=2){
                //如果自己的翻转后的字符串也在数组里，可以按顺序组成一对，注意翻转后不能是自己。
                if (wordMap.containsKey(rev)&&!rev.equals(words[i])){
                    //如words[i]=abcd,反转为dcba在数组里，可以组成一对
                    List<Integer> list=new ArrayList<>();
                    list.add(i);
                    list.add(wordMap.get(rev));
                    res.add(list);
                }
                //其他情况
                for (int l = 1; l < words[i].length(); l++) { //这里保证两部分至少有一个字符
                    String l_rev = reverse(words[i].substring(0, l));
                    String r_rev = reverse(words[i].substring(l));
                    //如果某个字符串能找到一个分割点，分割点前的部分是回文，后半部分翻转后也在数组里，可组成一对。
                    //如word[i]=acadcb，可分割为[a|cadbb,ac|adcb,..,aca|dcb...,acadc|b]，而bcd(dcb的反转)在数组内
                    //所以可以组成bcd|acadcb回文串
                    if (isPalindrome(words[i].substring(0, l)) && wordMap.containsKey(r_rev)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(wordMap.get(r_rev));
                        list.add(i);
                        res.add(list);
                    }
                    //如果某个字符串能找到一个分割点，如果后部分是回文，前半部分翻转后在数组里，也可组成一对.
                    //如word[i]=dbcaca，可分割为[d|bcaca,db|caca,..,dbc|aca...,dbcac|a]，而cbd(dbc的反转)在数组内
                    //所以可以组成dbcaca|cbd回文串
                    //注意这里要使用两个if，因为有可能同时满足
                    if (isPalindrome(words[i].substring(l)) && wordMap.containsKey(l_rev)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(wordMap.get(l_rev));
                        res.add(list);
                    }
                }
            }
        }
        //去重
        return res.stream().distinct().collect(Collectors.toList());
    }

    private void init(String[] words){
        for (int i=0;i<words.length;i++){
            wordMap.put(words[i],i);
            if (isPalindrome(words[i]))
                palindromeIndexList.add(i);
        }
    }

    /**
     * 判断是否是回文串
     * @param str
     * @return
     */
    private boolean isPalindrome(String str){
        if ("".equals(str))
            return true;
        int l=0,r=str.length()-1;
        while (l<r){
            if (str.charAt(l)==str.charAt(r)){
                l++;r--;
            }
            else return false;
        }
        return true;
    }

    /**
     * 反转字符串
     * @param str
     * @return
     */
    private String reverse(String str){
        StringBuilder builder=new StringBuilder();
        return builder.append(str).reverse().toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words={"abcd","dcba","lls","s","sssll"};
        List<List<Integer>> res=new Solution().palindromePairs(words);
        Collections.sort(res, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        for (List<Integer> list:res){
            System.out.println(list);
        }
    }
}
