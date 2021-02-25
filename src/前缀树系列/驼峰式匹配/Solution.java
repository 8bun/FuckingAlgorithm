package 前缀树系列.驼峰式匹配;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/1 12:00
 * @Description:
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。
 * （我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 * 如pattern = "FoBaT"
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est"，则返回true
 * 成功匹配的串：
 * ①删掉匹配串中的字符一定后都是小写字母
 * ②模式串中的字符相对顺序和在匹配串不变
 * 根据这个相对顺序来建模式串的字典树
 * 对模式串从前往后拿匹配串进行依次匹配：
 * 如果匹配串当前遍历到了字符ch，
 * 如果与模式串的下一个要匹配的字符相同，那么字符匹配成功，进入下一次匹配
 * 如果不相同，判断匹配串的字符是否是小写字母，是的话略过，进入下一次匹配，不是的话
 * 就说明匹配串的下一个要匹配的字符是大写字母，就匹配失败，例如pattern=Facc[B],query=[Fa]m[c]ab[c]de{A}B
 * 当匹配到pattern的B时，query跳过小写m ab de到了A,此时不能跳过A到B,匹配失败
 * @Url: https://leetcode-cn.com/problems/camelcase-matching/
 * @限制:
 * @Level:
 */
class TireNode{

    TireNode[] next;
    boolean isEnd;
    public TireNode(){
        next=new TireNode[58]; //因为A:41,z:122,总共需要122-41+1个位置
        isEnd=false;
    }
}
public class Solution {

    private TireNode root;
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        buildTireTree(pattern);
        List<Boolean> res=new ArrayList<>();
        for (String query:queries)
            res.add(isMatch(query));
        return res;
    }

    private void buildTireTree(String pattern) {
        root=new TireNode();
        TireNode cur_root=root;
        for (char ch:pattern.toCharArray()){
            if (cur_root.next[ch-'A']==null)
                cur_root.next[ch-'A']=new TireNode();
            cur_root=cur_root.next[ch-'A'];
        }
        cur_root.isEnd=true;
    }
    private boolean isMatch(String query){
        TireNode ptr=root;
        for (char ch:query.toCharArray()){
            if (ptr.next[ch-'A']!=null)
                ptr=ptr.next[ch-'A'];
            else {
                //如果是小写字母则跳过
                //例如pattern=FB,query=[F]ooA[B]，建的pattern字典树的F的next['B'-'A']是不空的，而F的next['A'-'A']是空的，也就是
                //不存在先F后A这样的匹配
                if (ch<='Z') //这里不用ch>='A'因为我们的串已经是A-Z的范围，只要<=Z，就一定是大写字母
                    return false;
            }
        }
        return ptr.isEnd;  //判断是否走完模式串
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] queries={"FooBar","aaFooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
        String pattern="FoBaT";
        List<Boolean> res=new Solution().camelMatch(queries,pattern);
        System.out.println(res.toString());
    }
}
