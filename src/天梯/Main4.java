package 天梯;

import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 14:53
 * @level：
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        for(int i=0;i<n;i++){
            int num=scanner.nextInt();
            if(num%2==0){
                if(num<0){
                    if((String.valueOf(num).length()-1)%2==0)
                        System.out.println(num);
                }
                else if ((String.valueOf(num).length())%2==0)
                    System.out.println(num);
            }
        }
    }

}
