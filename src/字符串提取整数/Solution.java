package 字符串提取整数;

/**
 * 特殊情况:
 * str=""
 * str="-"
 * str="3.21"
 * str="+"
 * str="+-2"应输出0：理解题意：我们寻找到的第一个非空字符为正或者负号时，则将该符号"与之后面"尽可能多的连续数字组合起来
 * str="2a23"输出2
 * （214748364*10+8=-2147483648）,采用long存储res,原理：https://blog.csdn.net/zqjackking/article/details/55802796
 * 现在以8位的为例。（计算机中以补码存储，所以下面都是补码）
 * 255 01111111
 * -256 10000000
 * -255 10000001
 * -1 11111111
 * 0 00000000
 * 所以就有 255+1=-256（溢出），-256-1=255（溢出） -1+1=0。
 * 2147483647+(long)1=2147483648
 */
class Solution {
    public int myAtoi(String str) {
        str=str.trim();
        if(str.equals(""))
            return 0;
        char first=str.charAt(0);
        if((first!='-'&&first!='+')&&(first<'0'||first>'9'))
            return 0;
        char ch_str[]=str.toCharArray();
        int index=0;
        char ch=ch_str[index];
        int sign=1;  //符号为正
        if(ch=='-'&&(index!=ch_str.length-1)&&(ch_str[index + 1] >= '0' && ch_str[index + 1] <= '9')){
            sign=-1;
            ch=ch_str[++index];
        }
        if(ch=='+'&&(index!=ch_str.length-1)&&(ch_str[index + 1] >= '0' && ch_str[index + 1] <= '9')){
            sign=1;
            ch=ch_str[++index];
        }

        long res=0;
        while(ch>='0'&&ch<='9'){
            if((res>Integer.MAX_VALUE/10)) {
                if(sign==-1)
                    return Integer.MIN_VALUE;
                return Integer.MAX_VALUE;
            }
            if(index<ch_str.length-1) {
                res = res * 10 + Integer.parseInt(String.valueOf(ch));
                if (!(ch_str[index + 1] >= '0' && ch_str[index + 1] <= '9')) {
                    break;
                }
                else {
                    ch = ch_str[index+1];
                    index+=1;
                }
            }
            if(index==ch_str.length-1) {
                long temp=res*10+ (long)Integer.parseInt(String.valueOf(ch));
                System.out.println(temp);
                if(temp>Integer.MAX_VALUE) {
                    if(sign==-1)
                        return Integer.MIN_VALUE;
                    else
                        return Integer.MAX_VALUE;
                }
                return sign*(int)temp;  //(long)防止溢出
            }
        }
        return sign*(int)res;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().myAtoi(""));
    }
}
