package 前缀树系列.添加与搜索单词之数据结构设计;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/28 22:44
 * @Description:
 * 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * 示例：
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * @Url: https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
 * @限制:
 * @Level:
 */
public class WordDictionary {

    class TireNode{
        private TireNode[] next;
        private final int R=26;
        private boolean isWord;
        public TireNode(){
            next=new TireNode[R];
            isWord=false;
        }
    }

    private TireNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root=new TireNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TireNode cur_root=root;
        for (char ch:word.toCharArray()){
            if (cur_root.next[ch-'a']==null)
                cur_root.next[ch-'a']=new TireNode();
            cur_root=cur_root.next[ch-'a'];
        }
        cur_root.isWord=true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root,word);
    }
    public boolean dfs(TireNode root,String word){
        if (root==null)
            return false;
        if ("".equals(word)&&root.isWord)
            return true;
        for (char ch:word.toCharArray()){
            if (ch=='.'){    //当ch=='.'时有26种路径选择
                for (int i=0;i<26;i++){
                    if (dfs(root.next[i],word.substring(1)))
                        return true;
                }
                return false;
            }
            //当ch是正常的字母
            else
                return dfs(root.next[ch-'a'],word.substring(1));
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordDictionary dictionary=new WordDictionary();
        dictionary.addWord("at");
        dictionary.addWord("and");
        dictionary.addWord("an");
        dictionary.addWord("add");
        boolean a=dictionary.search("a");
        boolean b=dictionary.search(".at");
        dictionary.addWord("bat");
        boolean c=dictionary.search(".at");
        boolean d=dictionary. search("an.");
        boolean e=dictionary. search("a.b.");
        boolean f=dictionary. search("b.");
        boolean g=dictionary. search("a.d");
        boolean h=dictionary. search(".");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println();
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
    }
}
