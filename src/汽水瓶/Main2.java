package 汽水瓶;

import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/5 10:45
 * @level：
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){  //牛客好像写成(left=scanner.nextInt()!=0会出错？)
            int left=scanner.nextInt();
            System.out.println(f(left,0));
        }
    }
    private static int f(int left,int drink){
        if(left<=3){
            return drink+1;
        }
        return f(left/3+left%3,drink+left/3);
    }
}
