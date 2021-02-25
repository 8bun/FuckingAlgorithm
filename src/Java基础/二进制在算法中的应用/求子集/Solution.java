package Java基础.二进制在算法中的应用.求子集;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 编写算法求一个集合的所有子集
 * 比如集合set={a,b,c,d}的所有子集是：
 * {},
 * {a}, {b}, {c}, {d},
 * {a, b}, {a, c}, {a, d}, {b, c}, {b, d}, {c, d},
 * {a, b, c}, {a, b, d}, {a, c, d}, {b, c, d},
 * {a, b, c, d}
 * 一共16个，事实上n个元素的集合的子集共有2^n个(包含空集)。
 * 如果一个集合为{A,B,C}
 * 那么子集为16个，使用四位的二进制来表示，定义如果该位为1，那么代表该位的元素可加入子集
 * 例如1101，子集为{A,B,D}
 * 思路来源https://blog.csdn.net/gh6267/article/details/88116764?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4
 * @url： https://leetcode-cn.com/problems/subsets/
 * @限制： 数组元素不重复
 * @author：Jack
 * @createTime：
 * 2020/4/10 17:30
 * @level：
 */
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int all = 1 << len; //总共的子集个数2^len
        for (int i = 0; i < all; i++) {  //子集编号
            List<Integer> part = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if (((i >> j) & 1) == 1) {  //例如num={A,B,C}，那么001表示A,010表示B,100表示C，然后分别跟i(范围[1,8])进行相与
                    //例如，此时i=3，也就是011，那么就表示子集{B,C}，如何表示呢，用相与的方法求
                    //011分别>>0,1,2位，分别和001相与
                    part.add(nums[j]);
                }
            }
            res.add(part);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 2};
        List<List<Integer>> res = new Solution().subsets(num);
        for (List<Integer> list : res) {
            for (Integer a : list) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
