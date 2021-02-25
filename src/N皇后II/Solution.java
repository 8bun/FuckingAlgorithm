package N皇后II;

import java.util.*;

/**
 * @description：
 * @url：https://leetcode-cn.com/problems/n-queens-ii/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 10:15
 * @level：
 */
public class Solution {
    //根据已经被标记为Q的位置的特征进行记录
    private Set<Integer> colSet = new HashSet<>();
    private  Set<Integer> subSet = new HashSet<>();
    private  Set<Integer> addSet = new HashSet<>();

    private  List<List<String>> res=new LinkedList<>();

    public int totalNQueens(int n) {
        if (n <= 0)
            return 0;
        char[][] board=new char[n][n];
        for (char[] chars:board)
            Arrays.fill(chars,'.');
        backtrace(board,0);
        return res.size();
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
        System.out.println(new Solution().totalNQueens(4));
    }
}
