package 天梯;

import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 17:06
 * @level：
 */
public class Main8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int num[]=new int[n];
        int sum=0;
        for(int i=0;i<n;i++){
            num[i]=scanner.nextInt();
            sum+=num[i];
        }
        int cnt=0;  //能连成直径的条数
        for(int i=0;i<n;i++){
           int t=num[i];
           for(int j=i+1;j<n-1;j++){
               t+=num[j];
               if(t*2==sum){
                   cnt++;
                   break;
               }
           }
       }
//       int res=0; //两两组合形成矩形
//       for (int i=1;i<=cnt;i++)
//           res+=(i-1);
//        System.out.println(res);
        System.out.println((cnt-1)*cnt/2);
    }
}
