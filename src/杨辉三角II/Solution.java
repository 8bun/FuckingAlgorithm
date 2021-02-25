package 杨辉三角II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 19:01
 * @Description:
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行
 * @Url: https://leetcode-cn.com/problems/pascals-triangle-ii/
 * @限制:
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * //
 *  * 1 2 1
 *  * 1 3 3 1
 *  从这两行来看，如果
 *  从左到右计算得到新行，并且我们复用前面的List<Integer>
 *  改了一个数据后，这时会导致旧行变为1 3 1，2后面应该还要用到的，然而这里已经被新的计算结果给替换了，然后后面计算就会导致出错
 *  为此，我们从后往前计算，是可以的
 * @Level:
 */
public class Solution {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res=new ArrayList<>();

        res.add(1);
        for (int i=1;i<=rowIndex;i++){
            //从后往前面计算
            // 1 2 1 :res
            // 1 3 3 1
            //只更新3 3部分即可
            for (int j=res.size()-1;j>0;j--)
                res.set(j,res.get(j)+res.get(j-1));
            res.add(1);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> res=new Solution().getRow(3);
        for (int a:res){
            System.out.print(a+" ");
        }
    }
}
