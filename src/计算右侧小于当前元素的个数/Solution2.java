package 计算右侧小于当前元素的个数;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/7/11 14:07
 * @Description: 这里使用插入排序的思想，我们从尾部开始：
 * 1）从后往前依次取出数字去排序(因为这样做的话，第2步的时候已排序的数组内的所有元素就已经是新数后面的)
 * 2）使用二分查找找到新的数字排在已排序的数组的第几位，就是比几个数字大
 * 3）然后将这个数字插入到该位置
 * O(nlogn)~O(n^2)
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution2 {

    public List<Integer> countSmaller(int[] nums) {

        int len = nums.length;
        LinkedList<Integer> res = new LinkedList<>();
        if (len == 0) return res;

        ArrayList<Integer> sortNums = new ArrayList<>();
        sortNums.add(0, nums[len - 1]);
        res.addFirst(0);
        for (int i = len - 2; i >= 0; i--) {

            int insertIndex = binarySearch(0, len - 2 - i, sortNums, nums[i]);
            /**
             * 涉及到index插入元素，不可以使用LinkedList，会超时
             * LinkedList调用add(index,e)方法的时候，是先寻址(java的for循环遍历,o(N)复杂度)再插入(o(1))复杂度；
             * ArrayList的add方法也是先寻址(支持索引访问,o(1)复杂度)再插入(如果插入位置在中间或者需要扩容，
             * 会触发复制数组这样的o(N)复杂度操作，如果直接尾部插入而且不需要扩容，那么时间复杂度也是o(N)，
             * 前后两种情况一平均，复杂度还是o(N)) 从理论的角度来看，这两种结构有着各自的特点，在不同情况下都可以达到o(1)复杂度，
             * 也就是我们常说的'比较快' ;
             * 但是实际上，就算是相同的时间复杂度，但是差距还是很大，为什么呢？
             * 因为如下： ArrayList的o(N)复杂度对应的操作是数组复制，看源码实现调用的native方法，也就是c语言实现的玩意。
             * LinkedList的o(N)复杂度对应的操作是寻址也就是查找，看源码实现用的java的for循环。 所以从效率角度出发，
             * 我们必须追究的其实是c语言的这个数组复制快还是java的for循环快
             * 具体看源码,LinkedList的add调用了
             * Node<E> node(int index) {
             *         // assert isElementIndex(index);
             *
             *         if (index < (size >> 1)) {
             *             Node<E> x = first;
             *             for (int i = 0; i < index; i++)
             *                 x = x.next;
             *             return x;
             *         } else {
             *             Node<E> x = last;
             *             for (int i = size - 1; i > index; i--)
             *                 x = x.prev;
             *             return x;
             *         }
             *     }
             *     来查找插入的位置的后继，是O(n)的操作
             *     而ArrayList的add:
             *     public void add(int index, E element) {
             *         rangeCheckForAdd(index);
             *
             *         ensureCapacityInternal(size + 1);  // Increments modCount!!
             *         System.arraycopy(elementData, index, elementData, index + 1,
             *                          size - index);
             *         elementData[index] = element;
             *         size++;
             *     }
             *     是通过数组复制来复制进行，虽然也是O(n)的复杂度，但是public static native void arraycopy，native方法会比较快
             *     然后通过O(1)定位插入元素
             */
            sortNums.add(insertIndex, nums[i]);

            res.addFirst(insertIndex);
        }
        return res;
    }

    private int binarySearch(int l, int r, List<Integer> sortedNums, int target) {
        if (l > r) return l;
        int mid = (l + r) >> 1;
        if (sortedNums.get(mid) < target) return binarySearch(mid + 1, r, sortedNums, target);
        return binarySearch(l, mid - 1, sortedNums, target);
    }
}
