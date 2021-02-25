package BFS系列.零一矩阵;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 17:02
 * @Description:
 * 给定一个由 0 和 1 组成的矩阵，找出"每个"元素到 "最近的0"  的距离。
 * 两个相邻元素间的距离为 1
 * BFS可以看成层序遍历，每一层离出发点距离逐渐增加，并且是离它最近的，所以一般最短最近之类的问题都可以用BFS来解决
 * 这道题与地图分析恰恰相反，可以想象成第一层全部为海洋，然后遍历每个海洋的周围，
 * 1162.地图分析这道题是可以理解为需要找到每个 0 最近的 1，思路是找到每一个1一直走海洋直到没有海洋可走的距离
 * 所以做法与地图分析相反
 * @Url: https://leetcode-cn.com/problems/01-matrix/
 * @限制:
 * @Level:
 */
public class Solution {
    private int[][]dirs= {{0,1},{0,-1},{1,0},{-1,0}};
    private Deque<Pair<Integer,Integer>> indexes=new ArrayDeque<>();
    public int[][] updateMatrix(int[][] matrix) {
        int row=matrix.length,col=matrix[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (matrix[i][j]==0)
                    indexes.addLast(new Pair<>(i,j));
                // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是未被计算最近距离的 1
                else
                    matrix[i][j]=-1;
            }
        }
        while (!indexes.isEmpty()){
            Pair<Integer,Integer> start=indexes.pollFirst(); //起点
            int x=start.getKey(),y=start.getValue(); //起点坐标
            //从(x,y)开始扩散
            for (int[] dir:dirs){
                int newX=x+dir[0],newY=y+dir[1];
                //为了找到最近的0，有些太远的0就没有必要进行扩散了
                /**
                 *  0  0   0
                 *  0  -1   0
                 * -1  -1  -1
                 * 首先将所有0的坐标压入队列，并将所有1改为-1，那么遍历到当前为(0,0)的0时，
                 * 向四周扩散，发现它的四周都是0，那么就不从当前0作为起点扩散，而是进入下一个起点的扩散
                 * 假设是(1,0)的0,那么由于它下方是陆地，所以可以从(1,0)开始扩散，所以改写陆地的值为0+1=1
                 * 将(2,0)加入队列后方,再发现它(1,0)的右边是-1，所以也改写为1，将(1,1)加入队列后方，删掉队首元素
                 * 继续进入下一个起点的扩散。
                 * 此时矩阵变为
                 *  0  0   0
                 *  0  1   0
                 *  1  -1  -1
                 * 我们发现此时已经将(2,0)和(1,1)的-1改成了1，也就表示它们的最近海洋距离找到了，并且都是1，如果
                 * 我们不对它们进行一些标记，也就是在开始的时候不对陆地1改为-1的话，那么此时后面的访问就会导致重复访问
                 * 例如(1,1)的0进行遍历的时候，发现它下方是1，但是不知道它已经被访问了，所以还是会对它的值进行一次修改
                 * 并且由前面分析，这些已经找到最近海洋距离的陆地坐标同样也加入到“起点”队列中，所以在所有0起点遍历完后，
                 * 假设从(2,0)开始遍历，那么它发现右边为陆地，那么就改成它的min+1，也就是2，然后加入队列后方
                 * 当队列遍历到(2,1)时，发现左边和右边都是陆地，就又改值为它的min+1，也就是3，这就造成了重复计算的错误了
                 */
                if (newX<0||newY<0||newX>=row||newY>=col||matrix[newX][newY]==0)continue;
                if (matrix[newX][newY]==-1){ //如果起点的四周有未被计算最近距离的陆地，那么它的值应该是起点的最近距离+1
                    //注意起点有可能是0，也有可能是已经被计算过最近距离的陆地，+1都是正确的
                    matrix[newX][newY]=matrix[x][y]+1;
                    indexes.addLast(new Pair<>(newX,newY));
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix={
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        int[][] res=new Solution().updateMatrix(matrix);
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++)
                System.out.print(res[i][j]+" ");
            System.out.println();
        }
    }
}
