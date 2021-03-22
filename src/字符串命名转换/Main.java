package 字符串命名转换;

/**
 * @author cwq
 * @since 2021/03/17
 * @link https://www.nowcoder.com/questionTerminal/e971233adadb4a44b9ca076c019fd939
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        //标识大写字母或者'_'、'-'的下标，以此分割字母
        int specialIndex = 0;
        StringBuilder ans0 = new StringBuilder();
        StringBuilder ans1 = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'A' && c <= 'Z') || c == '-' || c == '_') {
                ans0.append(Character.toUpperCase(s.charAt(specialIndex))).append(s, specialIndex + 1, i);
                ans1.append(Character.toLowerCase(s.charAt(specialIndex))).append(s, specialIndex + 1, i).append('_');
                specialIndex = i;
                if (c == '-' || c == '_') {
                    specialIndex++;
                }
            }
        }
        // 例如输入PascalCaseTest，此时ans0=PascalCase，ans1=pascal_case_
        String s1 = ans0.append(Character.toUpperCase(s.charAt(specialIndex))).append(s, specialIndex + 1, s.length()).toString();
        String s2 = ans1.append(Character.toLowerCase(s.charAt(specialIndex))).append(s, specialIndex + 1, s.length()).toString();

        System.out.print(s1 + " ");
        System.out.print(s1.substring(0, 1).toLowerCase() + s1.substring(1) + " ");
        System.out.print(s2+" ");
        System.out.println(s2.replaceAll("_", "-"));

    }
}
