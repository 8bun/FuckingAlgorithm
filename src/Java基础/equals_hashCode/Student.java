package 基本问题.equals_hashCode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/13 23:13
 * @level：
 */
public class Student {
    private String name;
    private  int age;
    private  String QQ;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;  //当前对象就是O
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(QQ, student.QQ);
    }

    /**
     * 用典型HashMap集合来存储对象，看看只重写equals不重写hashcode有什么问题
     * HashMap在插入数据时会根据HashCode来确定位置
     * @param args
     */
    public static void main(String[] args) {
        Student student = new Student();
        Student student2 = new Student();
        /*
        用equals比较说明对象相同，但是在HashMap中却以不同的对象存储
        （没有重写hashcode值，两个hashcode值，在HashMap看来就是两个不同的对象)
        到底这两个对象相等不相等？
        * */
        System.out.println(student.equals(student2));    //true
        /*这两个HashCode跟我们的自定义的类对象的内容相不相等没有任何关联，但是它们都不同了，
        HashMap会认为它们都不相等,而它们的equals又返回true，那么就矛盾了
        */
        System.out.println(student.hashCode());            //1735600054，调用public native int hashCode();
        System.out.println(student2.hashCode());           //21685669
        HashMap<Student, String> map = new HashMap<>();
        map.put(student,"123");
        map.put(student2,"456");
        System.out.println(map.get(student));  //123
        System.out.println(map.get(student2));  //456

    }
}
