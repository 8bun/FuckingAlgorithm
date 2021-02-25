package 前缀树系列.数组中两个数的最大异或值;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 10:07
 * @Description:
 * @Url: https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * @限制:
 * @Level:
 */
//二叉前缀节点
class TireNode{
    TireNode zero; //指向0
    TireNode one; //指向1
    int val; //0或1
    public TireNode(int x){
        val=x;zero=null;one=null;
    }
}

public class Solution {

    private TireNode root;

    public int findMaximumXOR(int[] nums) {
        buildTireBinaryTree(nums);
        return searchFromtop(nums);
    }
    //暴力：找到每个数和其他数相异或达到最大值的那个数，求出每个数的最大异或结果，最后求最大值即可
    //贪心：而要找到每个数和其他数相异或达到最大值的那个数，根据贪心策略，"尽量维持"当前选择的方向能保证与当前位异或的结果为1即可,除非树的儿子结点只有一个，那就没得选择
    //例如数num为[11]101,其他的数为[00]111,[00]011，那么其他的两个数在树中的前两位共有前缀，到第三位时，应该优先选择0的，因为
    //num第三位为0，应该优先选择1的走，这里不用担心会走自己的前缀，因为走自己的话结果是最小的0，所以是不可能的(0 ≤ ai < 2^31 。)
    private int searchFromtop(int[] nums) {
        int curMax=0;
        for (int num:nums){
            TireNode ptr=root;//辅助指针，确定当前走的位置
            for (int placeIndex=31;placeIndex>=0;placeIndex--){
                int binaryNum=num&(1<<placeIndex);//确定编号placeIndex处的二进制位
                if (binaryNum==0)
                    ptr=ptr.one!=null?ptr.one:ptr.zero;
                else
                    ptr=ptr.zero!=null?ptr.zero:ptr.one;
            }
            curMax=Math.max(curMax,num^ptr.one.val);
        }
        return curMax;
    }

    /**
     * 构建nums数组的二进制二叉前缀树
     * @param nums
     */
    private void buildTireBinaryTree(int[] nums) {

        root=new TireNode(-1);
        for (int num:nums){
            TireNode ptr=root;//辅助指针，确定当前需要插入节点的位置
            //对num进行二进制的32位表示，按照编号[31,30, .... 1，0]二进制数字串
            //然后高位的连接前缀树根结点，最低位为叶子结点
            for (int placeIndex=31;placeIndex>=0;placeIndex--){
                int binaryNum=num&(1<<placeIndex); //确定编号placeIndex处的二进制位是否为0，如果不为0的话，结果应该是2的幂次

                if (binaryNum==0){
                    if (ptr.zero==null)
                        ptr.zero=new TireNode(0);
                    ptr=ptr.zero;
                }else {
                    if (ptr.one==null)
                        ptr.one=new TireNode(1);
                    ptr=ptr.one;
                }
            }
            //最后num数值放在底部节点的one位置
            ptr.one=new TireNode(num);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={3, 10, 5, 25, 2, 8};
        int res=  new Solution().findMaximumXOR(nums);
        System.out.println(res);
    }
}
