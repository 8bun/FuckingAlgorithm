package 前缀树系列.键值映射;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/4/30 22:36
 * @Description: 使用HashMap简单解决
 * @link
 * @限制:
 * @Level:
 */
public class MapSum2 {
    private HashMap<String, Integer> map;

    public MapSum2() {
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapSum2 mapSum2 = new MapSum2();
        mapSum2.insert("acd", 3);
        int sum1 = mapSum2.sum("acd");
        System.out.println(sum1);
        mapSum2.insert("ac", 1);
        int sum2 = mapSum2.sum("ac");
        System.out.println(sum2);
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int ans = 0;
        for (String str : map.keySet()) {
            if (str.startsWith(prefix))
                ans += map.get(str);
        }
        return ans;
    }
}
