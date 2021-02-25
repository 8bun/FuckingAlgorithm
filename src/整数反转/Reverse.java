package 整数反转;

/**
 * url: https://leetcode-cn.com/problems/reverse-integer/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
 */
public class Reverse {

    public int reverse(int x){
        int temp_x=x;
        int rev_x=0;
        int r;
        while(temp_x!=0){
            r=temp_x%10;
            temp_x/=10;
            if((rev_x>Integer.MAX_VALUE/10)||(rev_x<Integer.MIN_VALUE/10)) { //先溢出判断，这里要注意放在这里，放循环外可能已经发生溢出了,除10表示后面还得*10
                rev_x=0;
                break;
            }
            rev_x=rev_x*10+r;
        }
        return rev_x;
    }

    public static void main(String[] args) {
        System.out.println(new Reverse().reverse(123));
    }
}
