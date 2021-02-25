package 前缀树系列.词典中最长的单词;

import 前缀树系列.实现一个魔法字典.MagicDictionary;
import 前缀树系列.添加与搜索单词之数据结构设计.WordDictionary;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 22:46
 * @Description:
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，
 * 该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * 也就是说如果答案字符串长度为len，那么词典中存在这len-1个单词都是答案字符串的前缀，它们的长度依次相差1
 * 所以，最长单词的每个字符在前缀树中对应的节点都能表示一个字符串
 * @Url: https://leetcode-cn.com/problems/longest-word-in-dictionary/
 * @限制:
 * @Level:
 */
class TireNode{
    TireNode[] next;
    boolean isWord;
    public TireNode(){
        next=new TireNode[26];
        isWord=false;
    }
}
public class Solution {
    private TireNode root;
    public String longestWord(String[] words) {
        buildTireTree(words);
        String maxWord="";
        //满足条件的最长单词的每个字符在前缀树中对应的节点都能表示一个字符串
        for (String word:words){
            TireNode ptr=root;
            boolean flag=true;//是否满足条件
            for (char ch:word.toCharArray()){
                if (ptr.next[ch-'a']==null||!ptr.next[ch-'a'].isWord) {
                    flag=false;break;
                }
                ptr=ptr.next[ch-'a'];
            }
            if (flag) {
                if (word.length()==maxWord.length()) {
                    maxWord = word.compareTo(maxWord) > 0 ? maxWord : word;
                    continue;
                }
                maxWord = word.length()>maxWord.length()  ? word : maxWord;
            }
        }
        return maxWord;
    }
    private void buildTireTree(String[] words){
        root=new TireNode();
        for (String word:words){
            TireNode ptr = root;
            for (char ch:word.toCharArray()) {
                if (ptr.next[ch-'a']==null)
                    ptr.next[ch-'a']=new TireNode();
                ptr=ptr.next[ch-'a'];
            }
            ptr.isWord=true;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dict={"k","lg","it","oidd","oid","oiddm","kfk","y","mw","kf","l","o","mwaqz","oi","ych","m","mwa"};
        String res=new Solution().longestWord(dict);
        System.out.println(res);
    }
}
