package 基本问题.并发原子类练习;

/**
 * @description：
 * 创建了一个 Person 对象，然后把 Person 对象设置进 AtomicReference 对象中，
 * 然后调用 compareAndSet 方法，该方法就是通过 CAS 操作设置 ar。
 * 如果 ar 的值为 person 的话，则将其设置为 updatePerson
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 21:34
 * @level：
 */
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("SnailClimb", 22);
        ar.set(person);
        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(person, updatePerson);  //如果 ar 的值为 person(预期的原来在内存的值) 的话，则将其设置为 updatePerson（新的值）

        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

