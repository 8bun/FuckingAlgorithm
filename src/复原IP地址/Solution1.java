package 复原IP地址;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用dfs方式，来自官方题解
 */
public class Solution1 {

    private List<String> res = new ArrayList<>();

    private int SEG_COUNT = 4;
    private int[] segments = new int[SEG_COUNT];
    private int len;
    private String str;
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if(len < 4 || len > 12) {
            return res;
        }
        this.len = len;
        this.str = s;
        dfs(0, 0);
        return res;
    }

    private void dfs(int segIndex, int segStart) {
        if(segIndex == SEG_COUNT) {
            if (segStart == len) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < SEG_COUNT - 1; i++) {
                    builder.append(segments[i] + ".");
                }
                builder.append(segments[SEG_COUNT - 1]);
                res.add(builder.toString());
            }
            return;
        }

        if (segStart == len) return;

        if(str.charAt(segStart) == '0') {
            segments[segIndex] = 0;
            dfs(segIndex + 1, segStart + 1);
        }

        int addr = 0;

        for(int end = segStart; end < len; end++) {
            addr = addr * 10 + (str.charAt(end) - '0');
            if(addr > 0 && addr <= 255) {
                segments[segIndex] = addr;
                dfs(segIndex + 1, end + 1);
            }
            else {
                break;
            }
        }
    }
}
