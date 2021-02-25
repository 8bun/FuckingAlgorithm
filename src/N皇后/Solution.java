package N皇后;

import java.util.*;

/**
 * @description：
 * n 皇后问题研究的是如何将n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * 某一行和某一列以及双对角线的交叉位置有且只有一个皇后（皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位）
 * 求放置的方案数
 * 给定一个整数 n，返回所有不同的n皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 *  ]
 *
 * @url： https://leetcode-cn.com/problems/n-queens/
 * 回溯思想详解：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
 * @限制：
 * @autho: Jack
 * @createTime: 2020/4/9 23:17
 * @level：
 */
public class Solution {


    //根据已经被标记为Q的位置的特征进行记录
    private  Set<Integer> colSet = new HashSet<>();
    private  Set<Integer> subSet = new HashSet<>();
    private  Set<Integer> addSet = new HashSet<>();

    private  List<List<String>> res=new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0)
            return null;
        char[][] board=new char[n][n];
        for (char[] chars:board)
            Arrays.fill(chars,'.');
        backtrace(board,0);
        return res;
    }
    private void backtrace(char[][] board,int x){
        if(x==board.length){
            res.add(charToString(board));
            return;
        }
        //找行x的哪一列可以放置皇后
        for (int i=0;i<board.length;i++){
            if (!isValid(x,i))
                continue;
            board[x][i]='Q';
            colSet.add(i);
            addSet.add(x+i);
            subSet.add(x-i);
            backtrace(board,x+1);
            board[x][i]='.';
            colSet.remove(i);
            addSet.remove(x+i);
            subSet.remove(x-i);
        }
    }

    private List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }

//    private static boolean isValid(char[][] board ,int x, int y){
//        int cols=board[0].length;
//        int rows=board.length;
//        for (int i=0;i<rows;i++){
//            if (board[i][y]=='Q')
//                return false;
//        }
//        for (int i=0;i<cols;i++){
//            if (board[x][i]=='Q')
//                return false;
//        }
//        //判断斜率为1的 对角线是否有冲突, 只用算上方，下方还没开始填充不用管
//        for (int i=x-1,j=y+1;i>=0&&j<cols;i--,j++){
//            if(board[i][j]=='Q')
//                return false;
//        }
//        // 判断斜率为-1的 对角线是否有冲突
//        for (int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
//            if(board[i][j]=='Q')
//                return false;
//        }
//        return true;
//    }
    private  boolean isValid(int x, int y){
        return !colSet.contains(y)&&!addSet.contains(x+y)&&!subSet.contains(x-y);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        List<List<String>> res=new Solution().solveNQueens(n);
        for (List<String> list:res){
            while (list.size()>0){
                System.out.println(list.get(0));
                list.remove(0);
            }
            System.out.println("-------------------");
        }
    }
}
