package 粤港澳;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/25 13:00
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums=scanner.nextInt();
        List<Student> students=new ArrayList<>();
        for (int i=0;i<nums;i++){
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int c=scanner.nextInt();
            Student student=new Student(i+1,a,b,c);
            students.add(student);
        }
        Collections.sort(students,new Student.StuCompare());
        for (int i=0;i<5;i++){
            Student student=students.get(i);
            System.out.println(student.getNum()+" "+student.getA()+" "+student.getB()+" "+student.getC());
        }
    }
}

// 学生对象
class Student {
    private int num;
    private int a, b, c, sum;

    // 学生对象建立需要传入姓名和3门课的成绩
    Student(int num, int a, int b, int c) {
        this.num = num;
        this.a = a;
        this.b = b;
        this.c = c;
        sum = a + b + c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getsum() {
        return sum;
    }

    // 定义比较器，按总分排序
    static class StuCompare implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1.getsum()!=s2.getsum())
                return Integer.compare(s2.getsum(), s1.getsum());
            else {
                if (s1.getA()!=s2.getA()){
                    return Integer.compare(s2.getA(), s1.getA());
                }
                else {
                    return Integer.compare(s1.getNum(), s2.getNum());
                }
            }
        }
    }

}
