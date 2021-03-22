package 多线程系列.H2O生成;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 现在给你例如"OOHHHH"这样的H和O组合，请按照H2O（HOH、HHO、OHH...都可以）的形式三三组合
 * "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 都是有效解
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 *
 * @author cwq
 * @since 2021/02/10
 */
public class H2O {

    private Semaphore hSem = new Semaphore(2);
    private Semaphore oSem = new Semaphore(1);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hSem.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        hSem.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oSem.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        oSem.release();
    }
}
