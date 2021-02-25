package 多线程系列.交替打印字符串;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author cwq
 * @since 2021/02/10
 * @link
 */

public class FizzBuzz {

    private int n;
    private Semaphore fizzSem = new Semaphore(0);
    private Semaphore buzzSem = new Semaphore(0);
    private Semaphore fizzBuzzSem = new Semaphore(0);
    private Semaphore numSem = new Semaphore(1);
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizzSem.acquire();
                printFizz.run();
                numSem.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                buzzSem.acquire();
                printBuzz.run();
                numSem.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSem.acquire();
                printFizzBuzz.run();
                numSem.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            numSem.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSem.release();
            }
            else if (i % 3 == 0) {
                fizzSem.release();
            }
            else if (i % 5 == 0) {
                buzzSem.release();
            }
            else {
                printNumber.accept(i);
                numSem.release();
            }
        }
    }
}
