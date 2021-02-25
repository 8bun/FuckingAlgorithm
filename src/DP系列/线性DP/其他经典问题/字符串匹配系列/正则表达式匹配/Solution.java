package DP系列.线性DP.其他经典问题.字符串匹配系列.正则表达式匹配;


/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * 递归回溯法：
 * 通过不断地剪去s和p相同的首元素，直到某一个或两个都被剪为空，就可以得到结论
 * 根据p分几种情况：
 * ① p没有包含*，只需要比较首部相同（即s[i]=p[i]）则剪去，依次更新p和s的值
 * ② p包含*，(假设p的第i个元素的下一个元素为*,即p[i-1]='*') 分两种情况：
 *    i. *前面的元素在s中出现0次：保持s不变，将p剪去2个元素（*和前面的元素），继续更新p和s递归
 *     例如，s:bc
 *           p:a*bc
 *           s中没有包含a，则p将a*去掉，变为bc，然后剪首对比
 *    ii.*前面的元素在s中出现1次或多次：此时，比较
 *      例如，s:aabb
 *            p:a*bb
 *            s中有包含a，并且首元素都是a，则剪去s的首元素a,变为abb，再进行比对，
 *            s:abb
 *            p:a*bb
 *            首元素又相同，剪去s的首元素为bb,
 *            s:bb
 *            p:a*bb
 *            此时适用i的情况，进行应用即可
 *     iii.特殊情况：
 *            s:abb
 *            p:a*abb
 *            此时满足ii情况，将s变为bb
 *            s:bb
 *            p:a*abb
 *            此时满足i情况，p变为abb
 *            此时判定为无法匹配，但是实际是可以匹配的
 *            如果看成满足i的情况，将p变为abb，则可以成功
 *            即i和ii全试一次，看能否成功即可
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        //剪到规则p为空，s为空则匹配成功，s不为空则不匹配
        if(p.isEmpty()){
            return s.isEmpty();
        }
        //首元素是否一致，s非空（首元素相同，或者p对应位置为任意字符）
        boolean first_match= !s.isEmpty() && (s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        //如果p的下一个字符是'*',这里递归保证每次的*都会至少出现在第2个位置上
        if(p.length()>=2 && p.charAt(1)=='*'){
            //i和ii两种方法都试一下
            return isMatch(s,p.substring(2))|| first_match && isMatch(s.substring(1),p);
        }
        //一般情况，都各自剪去首部
        else{
            return first_match && isMatch(s.substring(1),p.substring(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("abb","a*abb  "));
    }
}