package 基本问题.多线程;

import java.util.concurrent.CountDownLatch;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 10:54
 * @level：
 */
public class ThreadTest2 {

    private CountDownLatch countDownLatchA;  //两把门闩
    private CountDownLatch countDownLatchB;

    public ThreadTest2() {
        countDownLatchA = new CountDownLatch(1);
        countDownLatchB = new CountDownLatch(1);
    }

    // First will run first. Leave a mark after task done.
    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchA.countDown(); //latch的值减1到0，唤醒等待的线程，此线程进入等待
    }

    // Keep waiting util First task set its mark.第一个任务完成轮到第二个任务
    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatchA.await();  //上锁，+1
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatchB.countDown();
    }

    // Same as Second task.
    public void third(Runnable printThird) throws InterruptedException {
        countDownLatchB.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
