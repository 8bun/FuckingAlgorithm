package 基本问题.equals_hashCode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/13 23:20
 * @level：
 */
public class Student2 {
    private String name;
    private  int age;
    private  String QQ;

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;  //当前对象就是O
        if (o == null || getClass() != o.getClass()) return false;
        Student2 student = (Student2) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(QQ, student.QQ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, QQ); //调用官方的hash方法
    }

    /**
     * 用典型HashMap集合来存储对象，我们重写了HashCode
     * @param args
     */
    public static void main(String[] args) {
        Student2 student = new Student2();
        Student2 student2 = new Student2();
        /*
        用equals比较说明对象相同，但是在HashMap中却以不同的对象存储
        （没有重写hascode值，两个hascode值，在HashMap看来就是两个不同的对象)
        到底这两个对象相等不相等？
        * */
        System.out.println(student2.equals(student2));    //true，因为他们的字段数据都是null,所以都是相等的
        System.out.println(student.hashCode());            //29791，调用自研hashCode
        System.out.println(student2.hashCode());           //29791
        HashMap<Student2, String> map = new HashMap<>();
        map.put(student,"123");
        map.put(student2,"456");
        System.out.println(map.get(student));  //456（因为equals相等了，后面把值给更新了）
        System.out.println(map.get(student2));  //456

    }

}
