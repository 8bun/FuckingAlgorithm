package 最长不含重复字符的子字符串;
import java.util.Scanner;
import java.util.HashMap;
/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/20 22:53
 * @level：
 */
public class Solution2 {

        public static void main(String[] args){
            Scanner scanner=new Scanner(System.in);
            String str=scanner.next();
            int len=str.length();
            int left=0;
            int right=0;
            int max_len=0;
            HashMap<Character,Integer> map=new HashMap<>();
            for(;right<len;right++){
                char ch=str.charAt(right);
                if(!map.containsKey(ch)){
                    map.put(ch,right);
                    max_len=(right-left+1>max_len)?(right-left+1):max_len;  //注意pwwkewpa这种情况当left=3，right=5的时候，
                    //后面加上的新元素之后还要继续更新最大长度
                }
                else{
                    max_len=(right-left>max_len)?(right-left):max_len;

                    //这里注意要先保存map.get(ch)，因为如果直接写进去可能会造成Null指针异常（被删掉了）
                    int tmp=map.get(ch);
                    for(int j=left;j<=tmp;j++) {
                        map.remove(str.charAt(j));
                    }
                    left=tmp+1;
                    map.put(ch,right);
                }
            }
            System.out.println(max_len);
        }

}
