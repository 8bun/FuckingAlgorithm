package 前缀树系列.前缀和后缀搜索;

import 前缀树系列.单词搜索II.Solution;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 23:49
 * @Description:
 * 有序列表构建前缀和后缀的字典树（后缀以每个字符串的最后一个字符开始），字典树的每个结点存储以到该结点为止的树路径为前缀的字符串下标集合，
 * 例如words={"app","apple","mk"},那么构建的前缀字典树中，第一个节点a存储{0,1},m存储{2},第一个字符p存储{0,1}因为以"ap"为前缀的字符串有words[0]和words[1]；对于后缀字典树，构建的是{"ppa","elppa","km"}的前缀树
 * 根据输入
 * ① 如果 prefix==""&&suffix=="" ，那么返回的是数组的最大下标；
 * ② 否则，在字典树查找prefix和suffix的最大集合：（在前缀构建的字典树搜prefix，在后缀构建的字典树搜reverse(suffix)）
 * 如果集合有null，则数组不存在以prefix为前缀或者suffix为后缀的字符串,返回-1；
 * 如果prefix和suffix中有一方为"",则前缀任意后缀受限或前缀受限后缀任意，返回的是非""一方集合的最大权重
 * 其他正常情况： 返回两个集合中最大的相同权重
 * @Url: https://leetcode-cn.com/problems/prefix-and-suffix-search/
 * @限制:
 * @Level:
 */
class TireNode{
    TireNode[] next;
    ArrayList<Integer> indexes;  //记录当前前缀路径存在于words数组的哪些元素的下标的集合
    public TireNode(){
        next=new TireNode[26];
        indexes=new ArrayList<>();
    }

     void buildTireTree(String word,int index) {

        TireNode cur_root=this;
        for (char ch:word.toCharArray()){
            if (cur_root.next[ch-'a']==null){
                cur_root.next[ch-'a']=new TireNode();
            }
            cur_root=cur_root.next[ch-'a'];
            cur_root.indexes.add(index);
        }
    }

     ArrayList<Integer> getStartwith(String prefix){ //获取前缀树中以prefix为前缀的存在于words数组的哪些元素的下标的集合
        TireNode cur_root=this;
        for (char ch:prefix.toCharArray()){
            if (cur_root.next[ch-'a']==null) return null;
            cur_root=cur_root.next[ch-'a'];
        }
        return cur_root.indexes;
    }
}
public class WordFilter {

    private TireNode prefixRoot;
    private TireNode suffixRoot;

    private int max_Index;
    public WordFilter(String[] words) {

        prefixRoot=new TireNode();
        suffixRoot=new TireNode();

        for (int i=0;i<words.length;i++) {
            prefixRoot.buildTireTree(words[i],i);
            suffixRoot.buildTireTree(reverse(words[i]),i);
        }
        max_Index=words.length-1;
    }

    private String reverse(String str){
        StringBuilder builder = new StringBuilder("");
        return builder.append(str).reverse().toString();
    }
    public int f(String prefix, String suffix) {
        if (prefix.isEmpty()&&suffix.isEmpty())
            return max_Index;
        ArrayList<Integer> preIndexes=prefixRoot.getStartwith(prefix);
        ArrayList<Integer> sufIndexes=suffixRoot.getStartwith(reverse(suffix));
        if (preIndexes==null||sufIndexes==null) //不存在以prefix为前缀的或者以suffix为后缀的词
            return -1;
        int p=preIndexes.size()-1;
        int s=sufIndexes.size()-1;
        if (prefix.isEmpty())//前缀字符串为空，返回后缀字典树中的最大权重
            return sufIndexes.get(s);
        if (suffix.isEmpty())//后缀字符串为空，返回前缀字典树中的最大权重
            return preIndexes.get(p);
        //双指针查找两个ArrayList中最大的相同元素
        //如{1,2,4,8},{2,3,6}
        while (p>=0&&s>=0){
            //这里要注意，get的对象是Integer对象，根据Integer的对象缓存策略：
            //当值大于127或者小于-128的时候即使两个数值相同，也会new一个integer,那么比较的是两个对象，用==比较的时候返回false
            //所以这里不要直接使用preIndexes.get(p)==sufIndexes.get(s)
            if (Objects.equals(preIndexes.get(p), sufIndexes.get(s)))
                return preIndexes.get(p);
            if (preIndexes.get(p)>sufIndexes.get(s))
                p--;
            else s--;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String[] words={
//                "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
//                "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
//                "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"
//                };
        String[] words={"apple"};
        WordFilter wordFilter=new WordFilter(words);
        int res=wordFilter.f("a","e");
        System.out.println(res);
//        while (scanner.hasNext()){
//            String pre=scanner.next();
//            String suf=scanner.next();
//            int res=wordFilter.f(pre,suf);
//            System.out.println(res);
//        }
    }
}
