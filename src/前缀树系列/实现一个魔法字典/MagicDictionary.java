package 前缀树系列.实现一个魔法字典;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 17:16
 * @Description: 实现一个带有buildDict, 以及 search方法的魔法字典。
 * 对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。
 * 对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * @Url: https://leetcode-cn.com/problems/implement-magic-dictionary/
 * @限制:
 * @Level:
 */
class TireNode{
    char ch;
    TireNode[] next;
    boolean isWord;
    public TireNode(){
        next=new TireNode[26];
        isWord=false;
    }
}
public class MagicDictionary {

    private TireNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root=new TireNode();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        buildTireTree(dict);
    }
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return dfs(root,0,word,0);
    }

    /**
     *
     * @param ptr 从ptr的子节点开始找findIndex处的字符
     * @param findIndex
     * @param word
     * @param differentCharCnt 开始找之前已经替换的字符次数
     * @return
     */
    private boolean dfs(TireNode ptr,int findIndex,String word,int differentCharCnt){

        if (findIndex==word.length())
            return differentCharCnt==1&&ptr.isWord;
        if (differentCharCnt>1||ptr==null)
            return false;
        //遍历所有26种选择
        for (int i=0;i<26;i++){
            if (ptr.next[i]==null)
                continue;
            int p=i==word.charAt(findIndex)-'a'?0:1; //如果走的字符是否是刚好是要找的，不是则不同+1
            if(dfs(ptr.next[i],findIndex+1,word,differentCharCnt+p))
                return true;
        }
        return false;
    }
    private void buildTireTree(String[] dict){
        for (String word:dict){
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
        String[] dict={"hello","leetcode"};
        String[] search={"hell","leetcoded","hhllo","hello"};
        MagicDictionary dictionary=new MagicDictionary();
        dictionary.buildDict(dict);
        for (String s:search){
            boolean res=dictionary.search(s);
            System.out.println(s+" "+res);
        }
    }
}
