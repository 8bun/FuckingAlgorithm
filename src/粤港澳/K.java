package 粤港澳;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 14:35
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class K {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums=scanner.nextInt();
        int[][] pro=new int[nums][2];
        int teacher[]=new int[nums];
        for (int i=0;i<nums;i++){
            pro[i][0]=scanner.nextInt();
            pro[i][1]=scanner.nextInt();
        }
        Arrays.sort(pro, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1],o1[1]);
            }
        });
        int max=0;
        int[] dp=new int[nums];
        dp[0]=pro[0][0]+pro[0][1];
        teacher[0] = pro[0][0];
        for (int i=1;i<nums;i++){
            teacher[i]=pro[i][0]+teacher[i-1];
           dp[i]=Math.max(dp[i-1],teacher[i]+pro[i][1]);
        }
        System.out.println("Project "+nums+": "+dp[nums-1]);
    }
}
