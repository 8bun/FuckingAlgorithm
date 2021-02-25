package 计算器;

import java.util.Stack;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/calculator-lcci
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 13:03
 * @level：
 */
public class Solution {

    public int calculate(String s) {
        char[] chars=s.trim().toCharArray();
        char sign='+'; //当前符号的标识
        int num=0;  //每个要入栈的数字
        Stack<Integer> stack=new Stack<>();
        //0+12-2*6可以看成+0 +12 -2 *6，sign--num
        for (int i=0;i<chars.length;i++){
            char ch=chars[i];
            if(ch==' ')
                continue;
            if(isDigit(ch))// 如果是数字，连续读取到 num
                num=num*10+(ch-'0');
            if(ch=='(')
                //如果遇到(，开始递归计算后面的部分，直到遇到)结束，返回结果
                num=calculate(s.trim().substring(i+1));
            // 如果不是数字，就是遇到了下⼀个符号。
            // 之前的数字和符号就要存进栈中
            if(!isDigit(ch) || i==chars.length-1 ) { //遇到符号或者到结尾
                switch (sign){
                    //num永远是符号之后的数字
                    case '+':
                        stack.push(num);break;
                    case '-':
                        stack.push(-num);break;
                    case '*':
                        int top=stack.pop();
                        stack.push(top*num);break;
                    case '/':
                        int top2=stack.pop();
                        stack.push(top2/num);break;
                }
                sign=ch;//对符号进行标记，例如+3+12，遍历到第二个+时，sign='+'，然后才记忆数字
                num=0;//处理完符号,num清0
                if (ch==')')// 遇到右括号返回递归结果
                    break;
            }
        }
        return sum(stack);
    }

    private int sum(Stack<Integer> stack){
        int sum=0;
        while (!stack.isEmpty()){
            sum+=stack.pop();
        }
        return sum;
    }

    //是否是数字
    private boolean isDigit(char ch){
        return ch>='0'&&ch<='9';
    }


}
