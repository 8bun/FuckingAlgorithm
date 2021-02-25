package 前缀树系列.连接词;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 14:44
 * @Description:
 * 给定一个不含重复单词的列表，编写一个程序，返回给定单词列表中所有的连接词。
 * 连接词的定义为：一个字符串完全是由至少两个给定数组中的单词组成的
 * @Url: https://leetcode-cn.com/problems/concatenated-words/
 * 与https://leetcode-cn.com/problems/word-break/单词拆分类似，但是这里使用前缀树来解决
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
    private List<String> res=new ArrayList<>();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        buildTireTree(words);
        for (String word:words){
            if (dfs(word,0,1))
                res.add(word);
        }
        return res;
    }

    /**
     *
     * @param word
     * @param startIndex 开始搜索的下标
     * @param cnt 统计当前word中的不可拆分单词的个数
     *            满足条件的字符串的cnt的数量至少为2个，若遍历到最后只有一个单词，则cnt的值还是为1
     * @return
     */
    private boolean dfs(String word,int startIndex,int cnt) {

        TireNode ptr=root;
        for (int index=startIndex;index<word.length();index++){
            if (ptr.next[word.charAt(index)-'a']==null)
                return false;
            ptr=ptr.next[word.charAt(index)-'a'];
            if (ptr.isWord){ //到达某个单词尾
                if (index==word.length()-1) return cnt>1;
                if (dfs(word,index+1,cnt+1))return true;
            }
        }
        return false;
    }
    private void buildTireTree(String[] words) {
        root=new TireNode();
        for (String word:words ){
            TireNode cur_root=root;
            for (char ch:word.toCharArray()){
                if (cur_root.next[ch-'a']==null)
                    cur_root.next[ch-'a']=new TireNode();
                cur_root=cur_root.next[ch-'a'];
            }
            cur_root.isWord=true;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words={"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        List<String> res=new Solution().findAllConcatenatedWordsInADict(words);
        System.out.println(res.toString());
    }
}
