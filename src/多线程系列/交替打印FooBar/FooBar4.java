package 多线程系列.交替打印FooBar;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：一个门有多把门锁，只有把所有的锁都解开了之后（值为0）才能执行
 * @author cwq
 * @since 2021/02/02
 */
public class FooBar4 {
    private int n;
    /**
     * 初始条件：
     * foo 这个“线程”的门现在就可以执行
     * bar 这个“线程”的门需要解开一把钥匙才可以执行
     */
    private CountDownLatch fooC = new CountDownLatch(0);
    private CountDownLatch barC = new CountDownLatch(1);
    public FooBar4(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // 上锁，不会+1
            fooC.await();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            // CountDownLatch是一次性的，countDown到0之后不会自己恢复成1，所以要每次new一个CountDownLatch对象。
            fooC = new CountDownLatch(1);
            // -1，bar这个“线程”需要0把钥匙才能执行
            barC.countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barC.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            barC = new CountDownLatch(1);
            fooC.countDown();
        }
    }
}
