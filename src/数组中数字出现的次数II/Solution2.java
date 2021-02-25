package 数组中数字出现的次数II;

/**
 * @description：
 * 法2：位运算
 * 根据位运算的性质：
 *  如果一个数字出现三次，则它二进制表示的每一位也出现三次。
 *  如果把所有出现三次的数字的二进制表示的每一位都分别加起来，则每一位的和都能被3整除。
 *  将数组中的所有数字的二进制表示的每一位都加起来。如果某一位的和能被3整除，
 *  则只出现一次数字的二进制表示中对应的那一位是0，否则就是1。
 *  例如[3,3,3,4]：
 *  011
 *  011
 *  011
 *  100
 *  133[各位出现的次数]
 *  所以出现一次的数字为100代表的4
 *  如果得到133这个统计情况如何得到4呢
 *  res=0，
 *  一次从高位到低位遍历133，其实100可以看成1向左移2位的结果
 *  我们先让res左移一位，然后res再加上b%3，
 *  例如b=1，res=0<<1=0，res+=1%3=1
 *  b=3,res<<1=10，res+=3%3=10
 *  b=3,res<<1=100,res+=3%3=100
 *
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/2 22:11
 * @level：
 */
public class Solution2 {
    public int singleNumber(int[] nums) {

        int[] mark=new int[32]; //如mark[0]表示从低位到高位第0位的1出现的总次数
        for (int num:nums){
            int index_in_num=0; //遍历num的二进制位，从低位开始
            while (num!=0){ //num=0时不需要统计，没有任何的位为1
                if((num&1)==1){//例如num=3,011&001==1，表示num最低位为1
                    mark[index_in_num]++;
                }
                index_in_num++;
                num>>=1; //num的二进制表示右移一位，表示num/=2
            }
        }
        int res=0;
        for (int i=31;i>=0;i--){
            res<<=1;
            res+=(mark[i]%3);
        }
        return res;
    }
}
