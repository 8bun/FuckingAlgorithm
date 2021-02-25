package 接雨水;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description：单调栈
 * https://leetcode-cn.com/problems/trapping-rain-water/solution/bao-li-jie-fa-yi-kong-jian-huan-shi-jian-zhi-zhen-/
 *
 * ArrayDeque是Deque接口的一个实现，使用了可变数组，所以没有容量上的限制。
 * 同时，ArrayDeque是线程不安全的，在没有外部同步的情况下，不能再多线程环境下使用。
 * ArrayDeque是Deque的实现类，可以作为栈来使用，效率高于Stack；
 * 也可以作为队列来使用，效率高于LinkedList。
 * 需要注意的是，ArrayDeque不支持null值。
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 15:30
 * @level：
 */
public class Solution4 {
    public int trap(int[] height) {
        int len=height.length;
        if(len<3)  //三个无法构成凹槽
            return 0;
        int res=0;
        Deque<Integer> index_Stack=new ArrayDeque<>();
        for(int i=0;i<len;i++){
            //当前访问的高度比栈顶要高了，可以接到雨水了
            while (!index_Stack.isEmpty()&&height[index_Stack.peek()]<height[i]){
                int top_index=index_Stack.pop(); //逐渐弹出栈顶比当前柱高要低的
                if(index_Stack.isEmpty()) //由于后面要取次栈顶的高度，这里要判空
                    break;
                //这个top_index与当前柱子所能接的雨水，取决于区间宽度，以及与次栈顶柱子的高度差有关，看不懂请看图理解
                int currentWidth=i-index_Stack.peek()-1;
                int currentHeight=Math.min(height[i],height[index_Stack.peek()])-height[top_index]; //计算高度的时候要注意，当前柱子高度比次栈顶柱子高度要低的情况
                res+=currentHeight*currentWidth;
            }
            index_Stack.push(i);
        }
        return res;
    }
}
