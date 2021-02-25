package Java基础.并发原子类练习;

/**
 * @description：AtomicStampedReference解决ABA问题，
 * ABA问题的解决办法就是使用版本号，在变量前面追加版本号，每次变量更新时把版本号加1
 * 这个类的compareAndSet方法的作用首先检查当前引用是否等于预期引用，并且检查当前标志是否等于预期标志，
 * 如果都相等，则以原子方式将该引用和标志的值设为给定的更新值。
* @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 21:40
 * @level：
 */
public class AtomicStampedReferenceDemo {


    public static void main(String[] args) {
//        AtomicStampedReference<Foo>  asr = new AtomicStampedReference<>(null,0);  // 创建AtomicStampedReference对象，持有Foo对象的引用，初始为null，版本为0

//        int[] stamp=new  int[1];
//        Foo  oldRef = asr.get(stamp);   // 调用get方法获取引用对象和对应的版本号
//        int oldStamp=stamp[0];          // stamp[0]保存版本号

//        asr.compareAndSet(oldRef, null, oldStamp, oldStamp + 1);   //尝试以CAS方式更新引用对象，并将版本号+1
    }
}
