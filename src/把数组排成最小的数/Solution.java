package 把数组排成最小的数;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description：重新定义 String 列表内的排序方法，若拼接 s1 + s2 > s2 + s1，那么显然应该把 s2 在拼接时放在前面，
 * 以此类推，将整个 String 列表排序后再拼接起来。
 * @url：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 * @限制：0 < nums.length <= 100，
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * @author：Jack
 * @createTime：2020/3/3 20:49
 * @level：中等
 */
public class Solution {
    public String minNumber(int[] nums) {

        int len=nums.length;
        String[] str_nums=new String[len];
        //转String数组
        for(int i=0;i<len;i++){
            str_nums[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str_nums, new Comparator<String>() {
            //比较两数的大小并调整在它们数组中的顺序
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        String res="";
        for(String str:str_nums){
            res+=str;
        }
        return res;
    }

}
