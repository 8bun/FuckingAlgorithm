package 顺时针打印矩阵;

/**
 * @description:顺时针打印矩阵
 * 其实就是由外到内矩阵的顺时针打印，每次定义好了其边界进入循环即可，直到最后一个矩阵只有一个元素后停止，也就是循环条件为
 * 每个矩阵循环都是以顺时针打印
 * @url:https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 * @author:Jack
 * @createTime:2020/2/27 11:18
 * @level:
 */
public class Solution {
    public int[] spiralOrder(int[][] matrix) {
        int row=matrix.length;
        int col=matrix[0].length;
        if(row==0)
            return new int[0];
        int res[]=new int[row*col];
        boolean visit[][]=new boolean[row][col];
        int top=0;
        int bottom=row-1;
        int left=0;
        int right=col-1;
        int index=0;
        while ((top<=bottom)&&(left<=right)){
            int t=top+1; //从上到下起点
            int r=right;//从右到左起点
            int l=left; //从左到右起点
            int b=bottom-1;//从下到上的起点
            //向右
            while (l<=right&&!visit[top][l]){
                visit[top][l]=true;
                res[index++]=matrix[top][l++];
            }
            //向下
            while (t<bottom&&!visit[t][right]){
                visit[t][right]=true;
                res[index++]=matrix[t++][right];
            }
            //向左
            while (r>=left&&!visit[bottom][r]){
                visit[bottom][r]=true;
                res[index++]=matrix[bottom][r--];
            }
            //向上
            while (b>top&&!visit[b][left]){
                visit[b][left]=true;
                res[index++]=matrix[b--][left];
            }
            //缩圈
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}
