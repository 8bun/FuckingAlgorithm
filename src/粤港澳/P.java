package 粤港澳;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 17:26
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class P {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days=scanner.nextInt();
        int[][] open=new int[days][4];
        for (int i=0;i<days;i++){
            for (int j=0;j<4;j++){
                open[i][j]=scanner.nextInt();
            }
        }
        int res=0;
        for (int i=0;i<4;i++){
            if (open[0][i]==1)
                res=Math.max(res,dfs(0,i,open,1));
        }
        System.out.println(res);
    }
    private static int dfs(int i,int j,int[][] open,int days){
        if (i==open.length)
            return days;
        int res=0;
        if (i>0&&open[i-1][j]==0) {
            for (int index = 0; index < 4; index++) {
                res = Math.max(res, dfs(i + 1, index, open, days +  ((i+1)==open.length?0:open[i+1][index])));
                if (res==open.length)
                    break;
            }
        }
        else {
            for (int index = 0; index < 4; index++) {
                if (index==j)
                    continue;
                res = Math.max(res, dfs(i + 1, index, open, days+((i+1)==open.length?0:open[i+1][index])));
                if (res==open.length)
                    break;
            }
        }
        return res;
    }
}
