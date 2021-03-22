package 多线程系列.交替打印FooBar;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/print-foobar-alternately
 * @since 2021/02/02
 */
public class FooBar {

    private final AtomicInteger flag = new AtomicInteger(0);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition fooCon = lock.newCondition();
    private final Condition barCon = lock.newCondition();
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (flag.get() != 0) {
                    fooCon.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag.set(1);
                barCon.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (flag.get() != 1) {
                    barCon.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag.set(0);
                fooCon.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
