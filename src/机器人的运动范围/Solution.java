package 机器人的运动范围;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @description:广度优先遍历可以利用队列的特点解决；
 * 例如，若a的相邻元素为b,c，b的相邻元素为a,d,e
 * 那么遍历a的所有相邻元素时，入队顺序为a,b,c，此时a先出队
 * b是下一个出队列的元素，遍历b的所有相邻元素后，队列顺序为b,c，(a已被访问不入队)，d,e
 * 然后再处理c...这样直到队列为空
 * 在Java中，LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
 * BFS在修改visit后不修改回来（在从队列中取出元素后才进行修改），回溯法需要修改回来
 * 【注意：在此题中，只有a的相邻b,c都符合条件时才入队】
 *
 * @url:https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 * @author:Jack
 * @createTime:2020/2/22 20:05
 * @level:中等
 */
public class Solution {


    private int m;
    private int n;
    private int k;

    private int ans;
    private Queue<int[]> queue=new LinkedList<>();  //保存被搜索的元素
    //除此之外，根据上面的描述，还需要一个visit数组标记是否被访问过而且现在处于队列之外的
    private boolean[][] visit;
    public int movingCount(int m, int n, int k) {

        this.m=m;
        this.n=n;
        this.k=k;
        queue.add(new int[]{0,0});
        visit=new boolean[m][n];
        return BFS();
    }

    /**
     * BFS只需要右、下两个方向
     * @return
     */
    private int BFS(){
        while (!queue.isEmpty()) {
            //只要有元素就先取出来并保存值
            int[] first = queue.poll();
            int row = first[0];
            int col = first[1];
            if(!visit[row][col]){
                visit[row][col]=true;  //把元素取出才进行visit的修改
                //只有该数组位置可走时，走到其附近才有意义
                if(Sum(row)+Sum(col)<=k){
                    ans++;
                    //将其所有相邻入队列，注意只要2个方向即可
                    if (verify(row, col + 1)){
                        if (!visit[row][col + 1]) {
                            //把其加进去之后，不需要进行visit的修改
                            queue.add(new int[]{row,col+1});
                        }
                    }
                    if(verify(row+1,col)) {
                        if (!visit[row + 1][col]) {
                            queue.add(new int[]{row+1,col});
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 计算数位之和
     * @return
     */

    private int Sum(int num){
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private boolean verify(int i,int j){
        return i>=0&&i<m&&j>=0&&j<n;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().movingCount(16,8,4));
    }
}
