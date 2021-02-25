package 数位之积;

/**
 * @description：不是很懂
 * 现给定任意正整数n,请寻找并输出最小的正整数m(m>9),使得m的各位（个位、十位…）之乘积等于n,若不存在则输出-1.
 * 输入样例： 132
 * 输出样例：
 * 求最小的正整数，那么结果一定是1-9中的任意组合，因为要保证尽量小，那么像9，8之类的比较大的就应该靠
 * 得越后，小的就靠前。n=128
 * f(128)
 *   |
 * [f(16)]*10+8，计算返回结果:28*10+8=288
 *   |
 * [f(2)]*10+8，2<10，f(2)返回2，所以返回2*10+8=28
 *
 * @url： https://www.nowcoder.com/question/next?pid=22390442&qid=925105&tid=31953554
 * @限制：
 * @author：Jack
 * @createTime：2020/3/26 19:52
 * @level：
 */
public class Solution {

    public static void main(String[] args) {
        Solution vivo = new Solution();
        int res = vivo.f(128);
        System.out.println(res);
    }

    public int f(int n){
        int res = resolve(n);
        if(res>0)
        {
            return res;
        }
        return -1;
    }
    public int resolve(int n)
    {
        if(n<10)
        {
            return n;
        }
        for (int i = 9; i >1 ; --i) {
            if(n%i == 0)
            {
                return resolve(n/i)*10+i;
            }
        }
        return -1;
    }
}
