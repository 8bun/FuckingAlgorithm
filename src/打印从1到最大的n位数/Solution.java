package 打印从1到最大的n位数;

/**
 * @description:按顺序打印出从 1 到最大的 n 位十进制数
 * @url:https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 * @author:Jack
 * @createTime:2020/2/24 10:34
 * @level:简单
 */
public class Solution {
    //n为位数
    public int[] printNumbers(int n) {

        int max=(int) Math.pow(10,n);
        int arr[]=new int[max-1];
        for(int i=0;i<max-1;i++){
            arr[i]=i+1;
        }
        return arr;
    }


    public static void main(String[] args) {

    }
}
