package 括号;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 广度优先搜索
 * @url：https://leetcode-cn.com/problems/bracket-lcci
 * https://leetcode-cn.com/problems/bracket-lcci/solution/mian-shi-ti-0809-gua-hao-zu-he-hui-su-by-jiang_kun/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 23:07
 * @level：
 */
public class Solution {
    private List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {

        bfs(n,n,"");
        return res;
    }

    private void bfs(int open_left, int close_left, String s) {
        if(open_left==0&&close_left==0){
            res.add(s);return;
        }
        if(open_left<=close_left) {
            //一个递归有两种选择，这个处理方法无论先后顺序
            if (close_left > 0) bfs(open_left, close_left - 1, s + ")");
            if (open_left > 0) bfs(open_left - 1, close_left, s + "(");
//            if (close_left > 0) bfs(open_left, close_left - 1, s + ")");
        }
    }

}
