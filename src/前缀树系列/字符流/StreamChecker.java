package 前缀树系列.字符流;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/1 14:04
 * @Description:
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // 初始化字典
 * streamChecker.query('a');          // 返回 false
 * streamChecker.query('b');          // 返回 false
 * streamChecker.query('c');          // 返回 false
 * streamChecker.query('d');          // 返回 true，因为 'cd' 在字词表中
 * streamChecker.query('e');          // 返回 false
 * streamChecker.query('f');          // 返回 true，因为 'f' 在字词表中
 * streamChecker.query('g');          // 返回 false
 * streamChecker.query('h');          // 返回 false
 * streamChecker.query('i');          // 返回 false
 * streamChecker.query('j');          // 返回 false
 * streamChecker.query('k');          // 返回 false
 * streamChecker.query('l');          // 返回 true，因为 'kl' 在字词表中。
 * 这道题的意思是：通过查询query字符产生一串字符流，请判断当前字符流的末尾字符是否能跟前面至少0个字符结合
 * 构成字典中的词
 * ab[cd]e[f]ghij[kl]
 * 这里构建words的后缀的前缀树，因为leetcode的测试用例不全面，而且最容易暴毙的样例是
 * aaa....ab，只有最后一个是b，如果倒着判断则只需判断两次
 * 当然如果所有单词都是 "aaaaaaa"[没有b]， 则倒着建树也没有用了。
 * 如果是ba....aaa，那倒着建树也是挺蠢的，但是leetcode没有给这样的
 * 思路大致是：
 * 对于words={"ap","abc","abcd"}
 * 对{"pa","cba","d","dc","dcba","dcbakesa"}建立前缀树
 * 然后每查询一个字符加到字符串末尾，例如
 * query:a,b,c,d
 * 字符串a,ab,abc,abcd,
 * 例如到了abcd，倒着遍历字符串从d开始
 * 然后查找是否存在前缀d，不存在则直接返回false
 * 存在则判断isWord是否是单词，是的话返回true,不是的话继续找是否存在前缀dc...
 * @Url: https://leetcode-cn.com/problems/stream-of-characters/
 * @限制:
 * @Level:
 */
class TireNode {
    TireNode[] next;
    boolean isWord;
    public TireNode() {
        next = new TireNode[26];
        isWord=false;
    }
    public void insert(String[] words) {
        for (String word:words) {
            TireNode cur_root=this;
            for (char ch : StreamChecker.reverse(word).toCharArray()) {
                if (cur_root.next[ch - 'a'] == null) {
                    cur_root.next[ch - 'a'] = new TireNode();
                }
                cur_root = cur_root.next[ch - 'a'];
            }
            cur_root.isWord=true;
        }
    }
    public boolean startWith(String word){
        TireNode root=this;
        for (int i=word.length()-1;i>=0;i--){
            if (root.next[word.charAt(i)-'a']!=null){
                root=root.next[word.charAt(i)-'a'];
                if (root.isWord)return true;
            }
            else return false;
        }
        return false;
    }
}
public class StreamChecker {

    private TireNode root;
    private String curStr;
    public StreamChecker(String[] words) {
        root=new TireNode();
        root.insert(words);
        curStr="";
    }

    public boolean query(char letter) {
        curStr+=letter;
        return root.startWith(curStr);
    }

    public static String reverse(String str){
        StringBuilder builder = new StringBuilder("");
        return builder.append(str).reverse().toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words={"cd","f","kl"};
        Character[] querys={'a','b','c','d','e','f','g','h','i','j','k','l'};
        StreamChecker checker=new StreamChecker(words);
        for (char query:querys){
            boolean res=checker.query(query);
            System.out.println(query+" "+res);
        }
    }
}
