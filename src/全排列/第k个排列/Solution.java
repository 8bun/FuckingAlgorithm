package 全排列.第k个排列;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description：
 * 使用定位法：
 * 以任何数字开头的排列有(n-1)!种
 * 例如例如： n = 6, k = 373
 * 初始化数组 nums = [1, 2, 3, 4, 5, 6];
 * 首先应该明白，以 1 开头的全排列有 5! 个，以 2 开头的全排列有 5! 个 …… 共 5! * 6 = 6! 个；
 * @url：
 * 定位法：https://leetcode-cn.com/problems/permutation-sequence/solution/cyu-yan-chao-jian-dan-de-chu-fa-ding-wei-by-dingji/
 * @限制： 待优化
 * @author：Jack
 * @createTime：2020/4/12 11:39
 * @level：
 */
public class Solution {

    private List<List<String>> res=new LinkedList<>();
    public String getPermutation(int n, int k) {
        int avg=1;
        for (int i=2;i<n;i++)
            avg*=i;  //算出(n-1)!
        //如果k=4,n=3，刚好k%avg==0，这个时候要注意是第二个阶乘
        int left=k%avg==0?avg:k%avg;
        int prefix_num=left==avg?k/avg:k/avg+1;

        List<String> part=new LinkedList<>();
        part.add(String.valueOf(prefix_num));
        backtrace(prefix_num,part,n,left);

        String s=res.get(left-1).toString();
        return s.substring(1,s.length()-1).replace(",","").replace(" ","");
    }
    private void backtrace(int prefix, List<String> cur_str,int max_str_len ,int max_Str_num){
        if (res.size()==max_Str_num)
            return;
        if(cur_str.size()==max_str_len){
            res.add(new LinkedList<>(cur_str));
            return;
        }
        for (int i=1;i<=max_str_len;i++){
            if(cur_str.contains(String.valueOf(i)))
                continue;
            cur_str.add(String.valueOf(i));
            backtrace(prefix,cur_str,max_str_len,max_Str_num);
            cur_str.remove(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        int n=3,k=3;
        String res=new Solution().getPermutation(n,k);
        System.out.println(res);
    }
}
