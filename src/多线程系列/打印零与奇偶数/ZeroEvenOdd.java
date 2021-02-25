package 多线程系列.打印零与奇偶数;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Author： Jack
 * @Date： 2020/09/28
 * @Description：
 * @Url： https://leetcode-cn.com/problems/print-zero-even-odd/
 */
public class ZeroEvenOdd {

    private int n;

    //许可数>=0，表示共享资源可用
    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);
    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    
    //for循环表示需要打印相应数字的个数

    // printNumber.accept(x) outputs "x", where x is an integer.
    //仅打印0（需要打印n个）
    //打印后，接着可能要打印奇数也可能打印偶数，所以需要唤醒奇数线程或偶数线程，取决于当前的i
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if(i % 2 == 0) {
                //要打印奇数了，让偶数线程让出许可
                even.release();
            }
            else odd.release();
        }
    }

    //仅打印除0之外的偶数
    //打印完偶数后需要打印0，需要唤醒0线程
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release(); //因为前面刚打印完0,zero刚acquire完，odd线程是在zero acquire之后被release的
        }
    }
    //仅打印奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}
