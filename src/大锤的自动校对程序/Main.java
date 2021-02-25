package 大锤的自动校对程序;

import java.util.Scanner;

/**
 * @description：
 * @url：https://www.nowcoder.com/test/question/42852fd7045c442192fa89404ab42e92?pid=16516564&tid=31540952
 * @限制：
 * @author：Jack
 * @createTime：2020/3/15 22:52
 * @level：
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < num; i++) {
                String s = scanner.next();
                String res = "";
                for (char ch : s.toCharArray()) {
                    int len = res.length();
                    if (len < 2) { //当AA的时候不能加了，因为可能会出现加上去后为AAA的情况
                        res += String.valueOf(ch); //此时长度为2
                    } else if (len == 2) {
                        if (ch == res.charAt(len - 1) && res.charAt(len - 1) == res.charAt(len - 2)) {  //要加上的A与res=AA
                            continue;
                        }
                        res += String.valueOf(ch);
                    } else {
                        if (ch == res.charAt(len - 1) && res.charAt(len - 1) == res.charAt(len - 2)) {  //要加上的A与res=BCAA
                            continue;
                        }
                        //要加上的B与res=AAB
                        if (ch == res.charAt(len - 1) && res.charAt(len - 2) == res.charAt(len - 3)) {
                            continue;
                        }
                        res += String.valueOf(ch);
                    }
                }
                System.out.println(res);
            }
        }
        scanner.close();
    }
}
