package 前缀树系列.单词替换;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 15:56
 * @Description:
 * @Url: https://leetcode-cn.com/problems/replace-words/
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
public class Solution {
    private TireNode root;

    public String replaceWords(List<String> dict, String sentence) {
        buildTireTree(dict);
        String[] words=sentence.split("\\s+");
        StringBuilder builder=new StringBuilder();
        for (String word:words){
            String rep=replace(word);
            if ("".equals(rep))
                builder.append(word);
            else builder.append(rep);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    private void buildTireTree(List<String> dict){
        root=new TireNode();
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
    private String replace(String word){
        TireNode ptr=root;
        StringBuilder res= new StringBuilder("");
        for (char ch:word.toCharArray()){
            if (ptr.next[ch-'a']!=null){
                ptr=ptr.next[ch-'a'];
                res.append(ch);
                if (ptr.isWord) //如果继承词有许多可以形成它的词根，则用最短的词根替换它。
                    break;
            }
            else break;
        }
        return ptr.isWord?res.toString():"";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words=new ArrayList<>();
        String[] wo={"a", "aa", "aaa", "aaaa"};
        for (String w:wo){
            words.add(w);
        }
        String sentence="baba ababa";
        String res=new Solution().replaceWords(words,sentence);
        System.out.println(res);
    }
}
