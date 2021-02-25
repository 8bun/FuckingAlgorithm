package 避免洪水泛滥;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/6/25 20:34
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public int[] avoidFlood(int[] rains) {

        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int len = rains.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);

        for (int i = 0; i < len; i++) {

            if (rains[i] == 0) { //存储所有值为0的下标
                set.add(i);
            }
            //如果今天有下雨
            else {
                ans[i] = -1;

                //如果今天之前该湖泊有下过雨
                if(map.containsKey(rains[i])) {
                    int firstFull_day = map.get(rains[i]); //找到rains[i]号湖泊在今天之前“第一次满”的位置
                    //在第一次满和今天的区间内找第一个0（可以抽干rains[i]号湖泊'第一次满'那天下的雨）
                    int pos = -1;
                    for (Integer p : set) {
                        if (p > firstFull_day) {
                            pos = p;
                            break;
                        }
                    }
                    if (pos == -1) return new int[]{};
                    ans[pos] = rains[i];
                    set.remove(pos);
                }
                map.put(rains[i], i);
            }
        }
        return ans;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         int[] arr = {1,0,2,0,2,1};
         int[] ans = new Solution().avoidFlood(arr);
         for (int a : ans) System.out.print(a + " ");
        System.out.println();
    }
}
