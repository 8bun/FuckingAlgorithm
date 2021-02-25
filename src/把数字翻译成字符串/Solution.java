package 把数字翻译成字符串;

/**
 * @description：
 * 假设函数backtrack(n)表示求[n,len-1]长度的翻译种类数，那么
 * 自上而下，从最大的问题开始，递归 ：
 *                      12258 backtrack(0)
 *                    /       \
 *   backtrack(1)b+2258       m+258 backtrack(2)
 *               /   \         /   \
 *           bc+258 bw+58  mc+58  mz+8
 *           /  \      \        \     \
 *       bcc+58 bcz+8   bwf+8   mcf+8  mzi
 *         /        \       \     \
 *    bccf+8      bczi    bwfi   mcfi
 * backtrack(4)
 *      /
 *  bccfi,backtrace(5)结束,return 1
 * 有很多子问题被多次计算，比如258被翻译成几种这个子问题就被计算了两次。
 * 注意有两条路径选择和一条路径选择的情况
 * @url：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/3/3 22:28
 * @level：中等
 */
public class Solution {

    private String num_str;
    private int len;
    public int translateNum(int num) {
        this.num_str=String.valueOf(num);
        this.len=num_str.length();
        return backtrace(0);
    }

    private int backtrace(int pos){
        if(pos==len)  //递归走到了最后一层，肯定算一种情况
            return 1;
        if(pos==len-1||num_str.charAt(pos)=='0'||num_str.substring(pos,pos+2).compareTo("25")>0) { //只能单独不能组合的情况：最后一个数字或者走到的数字为0或者该数字与后面不能组合
            return backtrace(pos+1);  //只有一条路径可以选择
        }
        return backtrace(pos+1) + backtrace(pos+2);
    }
    public static void main(String[] args) {
        System.out.println(new Solution().translateNum(624));
    }
}
