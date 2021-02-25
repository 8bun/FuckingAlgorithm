package 是否所有1都至少相隔k个元素;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/3 12:38
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean kLengthApart(int[] nums, int k) {

        int pre=-1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==1){
                if (pre!=-1){
                    if (i-pre-1<k){
                        return false;
                    }
                }
                pre=i;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
