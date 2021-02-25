package 前缀树系列.单词搜索II;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/28 23:46
 * @Description:
 * 我们根据字典中的单词构建一个 Trie，稍后将用于匹配过程。
 * 从每个单元格开始，如果字典中存在以单元格中的字母开头的单词,则我们开始回溯探索（即 backtracking(cell)）。
 * 在递归函数 backtracking(cell) 调用过程中，我们探索当前单元格周围的相邻单元格（即 neighborCell）以进行下一个递归调用 backtracking(neighborCell)。在每次调用时，我们都会检查到目前为止遍历的字母序列是否与字典中的任何单词匹配，这需要借助于我们在开始时构建的 Trie 数据结构。
 *
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * 输出: ["eat","oath"]
 * @Url: https://leetcode-cn.com/problems/word-search-ii/
 * @限制:
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用
 * 不同单词的路径可以重复
 * @Level:
 */
public class Solution {

    class TireNode{
        private final int R=26;
        private boolean isWord;
        private TireNode[] next;
        public TireNode(){
            next=new TireNode[R];
            isWord=false;
        }
    }
    private TireNode root=new TireNode();

    private static int[] dx=new int[]{-1,1,0,0};
    private static int[] dy=new int[]{0,0,-1,1}; //方向数组，上下左右四个方向
    //int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //或者这样定义
    //int[] delta = new int[]{0, 1, 0, -1, 0};
    private boolean[][] vis;  //标记访问状态数组,因为同一个单元格内的字母在一个单词中不允许被重复使用。

    private List<String> res=new ArrayList<>(); //结果数组

    public List<String> findWords(char[][] board, String[] words) {
        vis=new boolean[board.length][board[0].length];
        buildTireTree(words);
        for (String word:words){
            for (int i=0;i<board.length;i++){
                for (int j=0;j<board[0].length;j++){
                    if (board[i][j]==word.charAt(0)){
                        dfs(i,j,board,word,0,root.next[board[i][j]-'a']);
                    }
                }
            }
        }
        return res;
    }
    /**
     *
     * @param x 当前开始的下标x
     * @param y 当前开始的下标y
     * @param board
     * @param word
     * @param hasFindIndex_last 当前已经找到的word最大下标
     * @param cur_root 当前的前缀结点
     */
    private void dfs(int x,int y,char[][] board,String word,int hasFindIndex_last,TireNode cur_root) {
        if (hasFindIndex_last==word.length()-1&&cur_root.isWord) {
            if (!res.contains(word))  //防止重复
                res.add(word);
            return;
        }
        int newx,newy;
        vis[x][y]=true;
        for (int i=0;i<4;i++){
            newx=x+dx[i];
            newy=y+dy[i];
            //数组越界或者该元素被访问过
            if (newx<0||newy<0||newx>=board.length||newy>=board[0].length||vis[newx][newy])
                continue;
            //四个方向的新字符中，如果是下一个要找的字符，并且该新字符对应的cur_root的下一个结点存在
            if (board[newx][newy]==word.charAt(hasFindIndex_last+1)&&cur_root.next[board[newx][newy]-'a']!=null)
                dfs(newx,newy,board,word,hasFindIndex_last+1,cur_root.next[board[newx][newy]-'a']);
        }
        //从board[x][y]开始的路径全部找完，可以被其他路径使用
        vis[x][y]=false;
    }

    /**
     * 为words构建前缀树
     * @param words
     */
    private void buildTireTree(String[] words){
        for (String word:words){
            TireNode cur_root=root;
            for (char ch:word.toCharArray()){
                if (cur_root.next[ch-'a']==null){
                    cur_root.next[ch-'a']=new TireNode();
                }
                cur_root=cur_root.next[ch-'a'];
            }
            cur_root.isWord=true;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board={
          {'o','a','a','n'},
          {'e','t','a','e'},
          {'i','h','k','r'},
          {'i','f','l','v'}
        };
        String[] words={"oath","pea","eat","rain","oaan"};

//        char[][] board={
//                {'a'},
//                {'a'}
//        };
//        String[] words={"a"};

//        char[][] board={
//                {'a','b'},
//                {'c','d'}
//        };
//        String[] words={"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
        List<String> res=new Solution().findWords(board,words);
        System.out.println(res.toString());
    }
}
