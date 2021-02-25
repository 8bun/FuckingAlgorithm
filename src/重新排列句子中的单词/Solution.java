package 重新排列句子中的单词;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/17 10:36
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public String arrangeWords(String text) {
        String[] strings = text.split(" ");
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return Integer.compare(o1.length(),o2.length());
                else return 0;
            }
        });
        StringBuilder res = new StringBuilder();
        for (String string : strings) {
            res.append(string.substring(0, 1).toLowerCase() + string.substring(1));
            res.append(" ");
        }
        String r = res.deleteCharAt(res.length()-1).toString();
        return r.substring(0, 1).toUpperCase() + r.substring(1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
