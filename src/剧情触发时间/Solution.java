package 剧情触发时间;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 15:01
 * @Description:
 * @Url: https://leetcode-cn.com/problems/ju-qing-hong-fa-shi-jian/submissions/
 * @限制:
 * @Level:
 */
public class Solution {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int days=increase.length;
        int[][] cur=new int[days][3];
        int r_len=requirements.length;
        int[] res=new int[r_len];
        for (int i=0;i<days;i++){
            for (int j=0;j<3;j++) {
                if(i==0) {
                    cur[i][j] = increase[i][j];
                    continue;
                }
                cur[i][j] = cur[i - 1][j] + increase[i][j];
            }
        }
        //这里要注意的是，对于那些res为-1的，那么也就是连cur的最后一组都满足不了
        //如果最后一组可以满足剧情触发时间，那么一定会有一组是满足的，所以可以使用二分查找找到这个位置
        for (int i=0;i<r_len;i++) {
            if(requirements[i][0]==0&&requirements[i][1]==0&&requirements[i][2]==0)
                res[i] = 0;
            else {
                if(!(cur[days-1][0]>=requirements[i][0]&&cur[days-1][1]>=requirements[i][1]&&cur[days-1][2]>=requirements[i][2])) {
                    res[i] = -1;
                    continue;
                }
                res[i]=findPlace(cur,0,days-1,requirements,i);
            }
        }
        return res;
    }

    /**
     *
     * @param cur 每天的数量
     * @param left 查找区间(第left天)
     * @param right
     * @param requirements
     * @return 找到第一个满足条件的day数据的位置
     */
    private int findPlace(int[][] cur,int left,int right,int[][] requirements,int cur_require_index){
        int mid=(left+right)>>1;
        if(left==right)
            return left+1;
        //如果中间那个都不满足
        if (!(cur[mid][0]>=requirements[cur_require_index][0]&&cur[mid][1]>=requirements[cur_require_index][1]&&cur[mid][2]>=requirements[cur_require_index][2]))
            return findPlace(cur,mid+1,right,requirements,cur_require_index);
        return findPlace(cur,left,mid,requirements,cur_require_index);
    }

    public static void main(String[] args) {
        int[][] increase={{2,8,4},{2,5,0},{10,9,8}};
        int[][] re={{2,11,3},{15,10,7},{9,17,12},{8,1,14}};
        int[] res=new Solution().getTriggerTime(increase,re);
        for (int i:res)
            System.out.print(i+" ");
        Scanner scanner = new Scanner(System.in);
    }
}
