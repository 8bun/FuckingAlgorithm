package Java基础.二进制在算法中的应用.求子集;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/subsets-ii/
 * @限制： 数组元素有重复
 * @author：Jack
 * @createTime：2020/4/10 18:42
 * @level：
 */
public class Solution2  extends ArrayList{

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res=new ArrayList<>();
        int len=nums.length;
        int all=1<<len; //总共的子集个数2^len
        for (int i=0;i<all;i++){  //子集编号
            List<Integer> part=new ArrayList<>();
            for (int j=0;j<len;j++){
                if(((i>>j)&1)==1){  //例如num={A,B,C}，那么001表示A,010表示B,100表示C，然后分别跟i(范围[1,8])进行相与
                    //例如，此时i=3，也就是011，那么就表示子集{B,C}，如何表示呢，用相与的方法求
                    //011分别>>0,1,2位，分别和001相与
                    part.add(nums[j]);
                }
            }
            Collections.sort(part);  //排序
            if(!res.contains(part)&&part.size()>0)  //添加
                res.add(part);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num={1,2,3};
        List<List<Integer>> res=new Solution2().subsetsWithDup(num);
        //自定义排序
//        Collections.sort(res, new Comparator<List<Integer>>() {
//            @Override
//            public int compare(List<Integer> o1, List<Integer> o2) {
//                return o1.get(0).compareTo(o2.get(0));
//            }
//        });
        for (List<Integer> list:res){
            for (Integer a:list){
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }

}
