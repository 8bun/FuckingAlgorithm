package 矩阵中的路径;

/**
 * @description:深度优先搜索
 * @url:https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 * @author:Jack
 * @createTime:2020/2/22 10:55
 * @level:中等
 */
public class Solution {

        private String word;
        private char[][] board;

        private boolean[][] visit; //访问数组

        public boolean exist(char[][] board, String word) {

            this.board=board;
            this.word=word;
            visit=new boolean[board.length][board[0].length];
            for(int i=0;i<board.length;i++)
                for (int j=0;j<board[0].length;j++){

                    if(board[i][j]==word.charAt(0)){  //遍历到是word第一个字符，开始dfs

                        if(dfs(i,j,0))
                            return true;
                    }
                }
            return false;
        }
        //深度优先搜索有无word路径，传入dfs开始的board字符位置以及对应的字符位置，判断是否有相邻字符为要搜索的字符
        //dfs到index_in_word所对应的字符再继续dfs,如果dfs x元素的四周都没有，则开始回溯到之前位置，修改
        private boolean dfs(int i,int j,int search_from_index_in_word){
            if(search_from_index_in_word==word.length()) {
                return true;
            }
            if(verify(i,j)&&!visit[i][j]&&word.charAt(search_from_index_in_word)==board[i][j]){
                System.out.println(i+","+j);
                visit[i][j]=true;
                if(dfs(i,j+1,search_from_index_in_word+1)||
                        dfs(i,j-1,search_from_index_in_word+1)||
                        dfs(i+1,j,search_from_index_in_word+1)||
                        dfs(i-1,j,search_from_index_in_word+1))
                    return true;
                //当if条件失败时，才会执行到此步，也就是四周都dfs不到的情况
                visit[i][j]=false;
            }
            return false;
        }
        //判断dfs的数组下标是否合法
        private boolean verify(int i,int j){
            return i>=0&&i<board.length&&j>=0&&j<board[0].length;
        }

    public static void main(String[] args) {
        char[][] board= {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word ="ABCCED";
//        char[][] board= {{'a','a'}};
//        String word ="aaa";
//        char[][] board= {{'a'}};
//        String word ="a";
        System.out.println(new Solution().exist(board,word));
    }
}