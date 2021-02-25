package 天梯;

import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 14:17
 * @level：
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        System.out.print("Proposed change to: ");
        System.out.println(str.replace("", " ").trim());
    }
}
