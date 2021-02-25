package 全排列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description： 输入一组不重复的数字，返回它们的全排列
 * @url： 回溯法详解：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/9 23:42
 * @level：
 */
public class Solution {

    private static List<LinkedList<Integer>> res=new LinkedList<>();

    private static List<LinkedList<Integer>> f(int[] num){
        LinkedList<Integer> part=new LinkedList<>();
        backtrace(num,part);
        return res;
    }
    private static void backtrace(int[] num, LinkedList<Integer> part){
        if(part.size()==num.length){
            res.add(new LinkedList<>(part));
            return;
        }
        for(int i=0;i<num.length;i++){
            if(part.contains(num[i]))
               continue;
            part.add(num[i]);//作出选择
           backtrace(num,part); //进入决策
            part.removeLast(); //撤销选择
        }
    }

    public static void main(String[] args) {
        int[] num={1,2,3,4};
        List<LinkedList<Integer>> res=f(num);
        while (res.size()>0){
            Object[] tmp= res.get(0).toArray();
            for (int j=0;j<tmp.length;j++){
                System.out.print(tmp[j]+" ");
            }
            System.out.println();
            res.remove(0);
        }
    }
}
