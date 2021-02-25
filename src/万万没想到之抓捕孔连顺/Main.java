package 万万没想到之抓捕孔连顺;
import java.util.Scanner;
/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/16 9:04
 * @level：
 */
public class Main{
    public static void main(String[] args){
        int num,max_d;
        Scanner scanner=new Scanner(System.in);
        num=scanner.nextInt();
        max_d=scanner.nextInt();
        int left=0;
        int right=0;
        int[] arr=new int[num];
        for(;right<num;right++){
            arr[right]=scanner.nextInt();
        }
        right=0;
        long cnt=0;
        for(;right<num;right++){
            while(right>=2 && arr[right]-arr[left]>max_d){
                left++;
            }
            long n=right-left;
            if(n>=2)
                cnt+=(n*(n-1))/2;
        }
        System.out.println(cnt%99997867);
        scanner.close();
    }
}
