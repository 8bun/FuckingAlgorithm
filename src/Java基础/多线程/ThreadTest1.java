package 基本问题.多线程;

import java.util.ArrayList;

/**
 * @description：
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 10:17
 * @level：
 */
public class ThreadTest1 {

    static volatile ArrayList<Object> list=new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }
    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {

        ThreadTest1 threadTest1=new ThreadTest1();

        Object lock=new Object();
        new Thread(()->{
             synchronized (lock){
                 if(threadTest1.getSize()!=5){
                     try {
                         lock.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 System.out.println("t2结束");
                 lock.notify();
             }
        },"t2").start();
        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock){
                for (int i=0;i<10;i++){
                    threadTest1.add(new Object());
                    System.out.println("add " + i);

                    if (threadTest1.getSize()==5){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1").start();
    }
}
