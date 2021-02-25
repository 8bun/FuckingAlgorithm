package 基本问题.可重入锁;

/**
 * @description： “可重入锁”的概念：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，
 * 此时锁还没释放，当再次获取这个对象锁的时候还可以获取。（如果不能获取，就会造成死锁）
 * 父类的方法用synchronized修饰后，子类的重写的父类方法也要手动加synchronize的关键字。
 * synchronized运行出现异常，自动释放锁
 * 1.重入锁实现可重入性原理或机制是：每一个锁关联一个线程持有者和计数器，
 * 当计数器为 0 时表示该锁没有被任何线程持有，那么任何线程都可能获得该锁而调用相应的方法；当某一线程请求成功后，
 * JVM会记下锁的持有线程，并且将计数器置为 1；此时其它线程请求该锁，则必须等待；而该“持有锁的线程”如果再次请求这个锁，
 * 就可以再次拿到这个锁，同时计数器会递增；当线程退出同步代码块时，计数器会递减，如果计数器为 0，则释放该锁。
 * 2.Synchronized进过编译，会在同步块的前后分别形成monitorenter和monitorexit这个两个字节码指令
 *
 *
 * 3.ReenTrantLock的实现是一种自旋锁，通过循环调用CAS操作来实现加锁。
 * 它的性能比较好也是因为避免了使线程进入内核态的阻塞状态
 * 4.在资源竞争不是很激励的情况下，synchronized的性能要优于ReentrantLock，
 * 带在资源紧张很激烈的情况下，synchronized的性能会下降的很快，而ReentrantLock的性能基本保持不变
 *
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/18 19:28
 * @level：
 */
public class Test {

    public static void main(String[] args) {
        new MyThread().start();
    }
}
class Service1{
    public synchronized void f1(){
        System.out.println("service.f1");
        f2();
    }
    public synchronized void f2(){
        System.out.println("service.f2");
        f3();
    }
    public synchronized void f3(){
        System.out.println("service.f3");
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        new Service1().f1();
    }
}
