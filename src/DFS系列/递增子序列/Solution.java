package DFS系列.递增子序列;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/6 10:37
 * @Description:
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * (注意，子序列可以不连续)
 * 严格来说本题采用DFS
 * @Url: https://leetcode-cn.com/problems/increasing-subsequences/
 * @限制:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 * @Level:
 */
public class Solution {
    //Set去重
    private Set<List<Integer>> ans = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums,0,new ArrayList<>());
        return new ArrayList<>(ans);
    }

    /**
     *
     * @param nums
     * @param index [nums[index],...,nums[len-1]]多种选择
     * @param list
     */
    private void dfs(int[] nums,int index,List<Integer> list){

        //这里注意,因为结果是多个List的组合，每个List的地址不同，所以要分别new
        if (list.size()>=2){
            ans.add(new ArrayList<>(list));
        }
        //[nums[index],...,nums[len-1]]多种选择
        for (int i=index;i<nums.length;i++){
            //当前list为空或者最后一个元素<=num[i]
            if (list.size()==0||list.get(list.size()-1)<=nums[i]){
                list.add(nums[i]); //选择num[i]
                dfs(nums,i+1,list); //以i+1为起点，传入加入nums[i]的list
                list.remove(list.size()-1);  //不选择num[i]，此时也是以i+1为起点
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={4,6,7,7};
        List<List<Integer>> res=new Solution().findSubsequences(nums);
        for (List<Integer> list:res){
            System.out.println(list.toString());
        }
    }
}
