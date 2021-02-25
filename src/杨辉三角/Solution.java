package 杨辉三角;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 18:38
 * @Description:
 * @Url: https://leetcode-cn.com/problems/pascals-triangle/
 * @限制:
 * 1
 * 1 1
 * 1 2 1 //i=2,[1,1]
 * 1 3 3 1 //i=3,[1,2]
 * 1 4 6 4 1//i=4,[1,3]
 * @Level:
 */
public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        if (numRows==0)
            return res;
        List<Integer> firstRow=new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i=1;i<numRows;i++){
            List<Integer> cur=new ArrayList<>();
            List<Integer> pre=res.get(i-1);
            cur.add(1);
            for (int j=1;j<i;j++) //下标[1,i-1]
                cur.add(pre.get(j-1)+pre.get(j));
            cur.add(1);
            res.add(cur);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
