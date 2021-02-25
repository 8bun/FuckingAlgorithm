package Java基础.jdk8新特性;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @description：
 * lambda表达式可以理解为一种匿名函数的代替，允许将函数作为一个方法的参数，将代码像数据一样传达，简化代码的编写
 * @url：
 * @限制：
 * @author：Jack
 * @createTime： 2020/3/27 14:23
 * @level：
 */
public class LambdaTest {
    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("run1...");
            }
        };

        new Thread(runnable).start();
        //等效于上面的写法()里面写参数，->{}里面写函数内容
        Runnable runnable2=()->{System.out.println("run2...");};
        new Thread(runnable2).start();

        //或者
        new Thread(()->{System.out.println("run3...");}).start();

        //由于函数体只有一条语句，也可以将{}去掉
        new Thread(()->System.out.println("run4...")).start();

        //传统自定义比较器
        Comparator<String> comparator=new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length(); //比较o1和o2大小，自定义为比较它们的长度大小
            }
        };
        TreeSet<String> treeSet=new TreeSet<>(comparator);

        //使用lambda表达式,->后面加返回值
        TreeSet<String> treeSet1=new TreeSet<>((o1,o2)->o1.length()-o2.length());

//        treeSet1.add("a");
//        treeSet1.add("122");
//        System.out.println(treeSet1);
    }
}
