package 前缀树系列.实现前缀树;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/28 17:19
 * @Description: 前缀树系列.实现前缀树
 * 以实现如下功能
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * @Url: https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * https://www.bilibili.com/video/BV12741137Cr?from=search&seid=4027663172691198290
 * @限制:
 * @Level:
 */

/**
 * 前缀树节点：(多叉树)
 * 每个结点包含所有的以a、b...z等开头的路径
 * 实现功能:
 * 查找是否有某个字符前缀
 * 插入元素
 * 设置尾部标志
 *
 * “leet”
 */
class TireNode{
    //最多 R 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母
    private TireNode[] links;

    private final int R=26;
    //这里使用isEnd表示该结点是否是一个串的结束
    //因为这里
    // * trie.insert("apple");
    // * trie.search("apple");   // 返回 true
    // * trie.search("app");     // 返回 false
    //因为app的p不是apple树的前缀，那么就是返回false
    private boolean isEnd;

    public TireNode(){
        links=new TireNode[R];
    }
    public boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    public TireNode get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch,TireNode node){
        links[ch-'a']=node;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }
}
public class Trie {

    private TireNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root=new TireNode();
    }

    /** Inserts a word into the trie. */
    /**
     * 我们通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：
     * 链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
     * 链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。
     * 重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。
     * @param word
     */
    public void insert(String word) {
        TireNode cur_root=root;
        //test:apple
        for (char ch:word.toCharArray()){
            if (!cur_root.containsKey(ch)){
                cur_root.put(ch,new TireNode()); //创建新前缀结点
            }
            cur_root=cur_root.get(ch); //指向已有的前缀根
        }
        cur_root.setEnd(); //这样做是可以的，假如已经存在applepen，那么这样设置后面的search(apple)才会返回true
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TireNode cur_root=root;
        for (char ch:word.toCharArray()){
            if (cur_root.containsKey(ch)){
                cur_root=cur_root.get(ch);
            }
            else return false;
        }
        return cur_root.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TireNode cur_root=root;
        for (char ch:prefix.toCharArray()){
            if (cur_root.containsKey(ch)){
                cur_root=cur_root.get(ch);
            }
            else return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
