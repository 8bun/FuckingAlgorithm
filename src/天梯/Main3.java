package 天梯;
import java.util.Scanner;
/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 14:34
 * @level：
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        int l1=str.length();
        String len=String.valueOf(l1);
        int cnt=0;
        for(int i=0;i<len.length();i++){
            if(len.charAt(i)=='4')
                cnt++;
        }
        for (int i=0;i<l1;i++){
            if(str.charAt(i)=='4')
                cnt++;
        }
        System.out.println(cnt);
    }

}
