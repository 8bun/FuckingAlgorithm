package 粤港澳;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 15:30
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Q {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int [] a=new int[n+1];
            a[0]=1;
            for(int i=1;i<=n;i++){
                if(i==1||i==2)
                    a[i]=i;
                else
                    a[i]=a[i-1]+a[i-2];
            }
            System.out.println(a[n]);
        }
    }
}
