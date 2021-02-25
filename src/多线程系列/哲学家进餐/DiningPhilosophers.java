package 多线程系列.哲学家进餐;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 为避免死锁，最多只允许4个哲学家去持有叉子，可保证至少有1个哲学家能吃上意大利面。
 * 最差情况下是：4 个哲学家都各自持有1个叉子，此时还 剩余 1 个叉子 可供使用，这 4 个哲学家中必然有1人能获取到这个 剩余的 1 个叉子，从而手持 2 个叉子，可以吃意大利面。
 * 即：4 个人中，1 个人有 2 个叉子，3 个人各持 1 个叉子，共计 5 个叉子。
 * @author cwq
 * @since 2021/02/10
 * @link https://leetcode-cn.com/problems/the-dining-philosophers/solution/1ge-semaphore-1ge-reentrantlockshu-zu-by-gfu/
 */
public class DiningPhilosophers {

    private final ReentrantLock[] locks = {new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};

    /**
     *  限制 最多只有4个哲学家去持有叉子
     */
    private Semaphore eatLimit = new Semaphore(4);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftFork = (philosopher + 1) % 5;
        eatLimit.acquire();
        locks[leftFork].lock();
        locks[philosopher].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        locks[leftFork].unlock();
        locks[philosopher].unlock();
        eatLimit.release();
    }
}
