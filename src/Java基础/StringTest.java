package 基本问题;

/**
 * @description： String不可变：https://www.jianshu.com/p/b1d62928552d
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/8 20:55
 * @level：
 */

public class StringTest {
    public static void main(String[] args) {
        String a = "abc";
        String s = new String("abc");  //我们都知道字符串常量池的概念，
        // JVM 为了字符串的复用，减少字符串对象的重复创建，特别维护了一个字符串常量池。
        // 第一种字面量形式的写法，会直接在字符串常量池中查找是否存在值 123，
        // 若存在直接返回这个值的引用，若不存在创建一个值为 123 的 String 对象并存入字符串常量池中。
        // 而使用 new 关键字，则会直接在堆上生成一个新的 String 对象，并不会理会常量池中是否有这个值。所以本质上 str1 和 str2 指向的内存地址是不一样的
        System.out.println(a == s); //false，

        String b = "abc";
        System.out.println(b == a); //true,常量池找到
        System.out.println(b == "a" + "bc");  //true
        String m = "a";
        String n = "bc";
        System.out.println(b == m + n);  //false

//        char ch1=97;
//        char ch2='a';
//        System.out.println(" ch1="+ch1);
//        System.out.println(" ch2="+ch2);
    }

    public void f() {

    }
}
