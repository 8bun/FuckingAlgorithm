package 检查单词是否为句中其他单词的前缀;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/24 10:31
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public int isPrefixOfWord(String sentence, String searchWord) {
        if (!sentence.contains(searchWord)) return -1;
        String[] a = sentence.split("\\s");
        for (int i = 0; i< a.length;i++){
            System.out.println(a[i]);
            if (a[i].startsWith(searchWord))
                return i +1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
