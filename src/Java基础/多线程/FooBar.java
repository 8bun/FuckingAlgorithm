package 基本问题.多线程;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description：
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，
 * 另一个线程将会调用 bar() 方法。
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * @url：https://leetcode-cn.com/problems/print-foobar-alternately/
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 10:58
 * @level：
 */
public class FooBar {

    private int n;

    private Semaphore semaphore = new Semaphore(1); //信号量控制的是线程并发的数量,参数permits就是允许同时运行的线程数目;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++ ) {
            semaphore.acquire();  // 从信号量中获取一个允许机会
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //查看现在可用的信号量，当semaphore.availablePermits()=0时，可以理解为缓存为空，可以放入内容
            while (semaphore.availablePermits() != 0) {

            }

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore.release(); // 释放允许，将占有的信号量归还
        }
    }
}
