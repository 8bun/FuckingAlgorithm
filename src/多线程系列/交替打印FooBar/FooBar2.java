package 多线程系列.交替打印FooBar;


import java.util.concurrent.Semaphore;

/**
 * 信号量：当前共享资源可以被使用的个数
 *
 * @author cwq
 * @since 2021/02/02
 */
public class FooBar2 {

    private final Semaphore fooSema = new Semaphore(1);
    private final Semaphore barSema = new Semaphore(0);
    private int n;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSema.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barSema.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barSema.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooSema.release();
        }
    }

}
