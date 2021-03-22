package 判断路径是否相交;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author cwq
 * @Description:
 * @link
 * @限制:
 * @Level:
 * @since 2020/6/28 18:51
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean res = new Solution().isPathCrossing("NESWW");
        System.out.println(res);
    }

    public boolean isPathCrossing(String path) {
        HashSet<String> set = new HashSet<>();
        int x = 0, y = 0;
        set.add(x + "," + y);
        for (char ch : path.toCharArray()) {
            if (ch == 'N') y++;
            else if (ch == 'S') y--;
            else if (ch == 'W') x--;
            else if (ch == 'E') x++;
            if (set.contains(x + "," + y)) return true;
            set.add(x + "," + y);
        }
        return false;
    }
}
