package 基本问题.并发原子类练习;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description：
 * AtomicInteger 类主要利用 CAS (compare and swap) + volatile 和 native 方法来保证原子操作，从而避免 synchronized
 * 的高开销，执行效率大为提升。
 * CAS的原理是拿期望的值和原本的一个值作比较，如果相同则更新成新的值。UnSafe 类的 objectFieldOffset() 方法是一个本地方法，
 * 这个方法是用来拿到“原来的值”的内存地址。另外 value 是一个volatile变量，在内存中可见，
 * 因此 JVM 可以保证任何时刻任何线程总能拿到该变量的最新值。
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 21:02
 * @level：
 */
public class AtomicIntegerTest {

    private AtomicInteger count=new AtomicInteger();

    public void increment(){
        count.incrementAndGet();  //获取当前的值，并自增，使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
    }
    public int getCount(){
        return count.get(); //获取当前的值
    }
    public static void main(String[] args) {

    }
}
