package 多线程系列.交替打印FooBar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：一把门栓需要10把钥匙都集齐才能打开，也就是10个线程都调用await阻塞后才能冲破屏障，继续向下执行
 * 如果所有等待线程执行完毕，重置CyclicBarrier的状态后它可以被重用，而CountDownLatch则不可以
 *
 * @author cwq
 * @since 2021/02/02
 */
public class FooBar5 {
    private int n;
    /**
     * 初始条件：2个方法分别执行一次为一个循环，然后再进行下一次循环
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    /**
     * 控制先后顺序
     */
    private volatile int flag = 0;

    public FooBar5(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            printFoo.run();
            flag = 1;
            try {
                // 挂起当前线程，直至所有线程都到达时再同时执行后续任务
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (flag != 1) {
                ;
            }
            printBar.run();
            flag = 0;
            try {
                // 2个等待线程集合完成，全部方法执行完成
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
