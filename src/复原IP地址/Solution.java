package 复原IP地址;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式
 * @url： https://leetcode-cn.com/problems/restore-ip-addresses/
 * https://leetcode-cn.com/problems/restore-ip-addresses/solution/hui-su-suan-fa-hua-tu-fen-xi-jian-zhi-tiao-jian-by/
 * 每一个结点表示了求解这个问题的不同阶段，需要的状态变量有：
 * splitTimes：已经分割出多少个 ip 段（为了判断还剩的位数是否超过了最大可分配的位数）
 * begin：截取 ip 段的起始位置；
 * path：记录从根结点到叶子结点的一个路径（回溯算法常规变量，是一个栈）；
 * res：记录结果集的变量，常规变量。
 * @author： Jack
 * @createTime： 2020/4/10 23:23
 * @level：
 */
public class Solution {

    private List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len < 4 || len > 12)  //字符串的长度小于 4 或者大于 12 ，一定不能拼凑出合法的 ip 地址
            return res;
        backtrace(s, len, 0, "");
        return res;
    }

    /**
     * @param to_spilt       待分配的字符串
     * @param all_len        一开始最大的字符串长度
     * @param cur_spilt_cnt  4个中，当前已分配的个数
     * @param cur_split_path 当前分配的路径
     */
    private void backtrace(String to_spilt, int all_len, int cur_spilt_cnt, String cur_split_path) {

        if ("".equals(to_spilt) && cur_split_path.split("\\.").length == 5) {
            res.add(cur_split_path.substring(1));
            return;
        }
        //三种选择，截1,2,3个
        for (int i = 1; i <= (to_spilt.length() >= 3 ? 3 : to_spilt.length()); i++) {
            String left = to_spilt.substring(0, i);  //分割出来的
            String right = to_spilt.substring(i);  //剩余未分配的
            if (!isValid(left, right, cur_spilt_cnt + 1))
                continue;
            cur_split_path += "." + left; //选择分割
            cur_spilt_cnt++;

            backtrace(right, all_len, cur_spilt_cnt, cur_split_path);

            cur_split_path = cur_split_path.substring(0, cur_split_path.length() - i - 1); //不选择分割，回溯
            cur_spilt_cnt--;
        }
    }

    /**
     * 从当前未分割的字符串，最大长度，4个单位已分配的单位数判断是否符合条件
     *
     * @param to_spilt
     * @param cur_spilt_cnt
     * @return
     */
    private boolean isValid(String split, String to_spilt, int cur_spilt_cnt) {
        //是否有前导0
        if (split.length() > 1 && split.charAt(0) == '0')
            return false;
        int s = Integer.parseInt(split);
        return to_spilt.length() <= (4 - cur_spilt_cnt) * 3 && s >= 0 && s <= 255;
    }

    public static void main(String[] args) {
        List<String> res = new Solution().restoreIpAddresses("010010");
        for (String s : res)
            System.out.println(s);
    }

}
