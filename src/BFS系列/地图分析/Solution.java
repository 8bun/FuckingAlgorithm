package BFS系列.地图分析;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 22:48
 * @Description:
 * 官方题解解释为什么不使用Floyd算法以及源点集的选取：
 * https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/di-tu-fen-xi-by-leetcode-solution/
 * 这道题涉及一个“超级结点”分析以及超级结点连接集选取：
 * https://blog.csdn.net/chicken3wings/article/details/105219907?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2
 *  0 代表海洋，1 代表陆地
 * 找出一个海洋区域，求出每一个海洋区域（grid[i][j] == 0 的区域）的「最近陆地区域」，然后记录下它们的距离，
 * 返回在这些距离里面的最大值
 * 也就是对于每一个1，都有一个或者多个0
 * 图的BFS（多源BFS，树的BFS才是）可以想象成多颗树的层序遍历，从第一层多个源头出发（多源BFS）
 * 第一层为所有陆地（从左到右加入队列），然后从每个陆地开始从左到右依次进行遍历
 * 对每个遍历的陆地的四周为海洋的看成它的子节点，在队列后面加入这些结点，删掉陆地，然后再遍历它右边的陆地（此时是队头）的四周...
 * 下一层对每个海洋再遍历它四周的海洋，知道队列为空，统计遍历的层数-1为最大距离
 * @Url: https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/jian-dan-java-miao-dong-tu-de-bfs-by-sweetiee/
 * @限制:
 * @Level:
 */
public class Solution {

    private int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
    public int maxDistance(int[][] grid) {
        int row=grid.length,col=grid[0].length;
        /**
         * ArrayDeque是Deque的实现类，可以作为栈来使用，效率高于Stack；
         * 也可以作为队列来使用，效率高于LinkedList。
         * 需要注意的是，ArrayDeque不支持null值。
         */
        Deque<Pair<Integer,Integer>> indexes=new ArrayDeque<>();
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (grid[i][j]==1)   // 将所有陆地都放入队列中
                    indexes.push(new Pair<>(i,j));
            }
        }
        if (indexes.size()==0||indexes.size()==row*col)     // 如果没有陆地或者海洋，返回-1
            return -1;
        int curLevel=0;
        while (!indexes.isEmpty()){
            curLevel++;
            int size=indexes.size();
            //一次性取出size个元素(都是相同层的)
            for (int i=0;i<size;i++){
                Pair<Integer,Integer> cur=indexes.pollFirst();
                int x=cur.getKey(),y=cur.getValue();
                for (int[] dir:dirs){
                    int newX=x+dir[0],newY=y+dir[1];
                    // 如果搜索到的新坐标超出范围/陆地/已经遍历过，则不搜索了
                    if (newX<0||newY<0||newX>=row||newY>=col||grid[newX][newY]!=0)continue;
                    grid[newX][newY]=1;    // 把grid中搜索过的元素设置为1,防止被它四周重复搜索
                    indexes.addLast(new Pair<>(newX,newY));
                }
            }
        }
        return curLevel-1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
