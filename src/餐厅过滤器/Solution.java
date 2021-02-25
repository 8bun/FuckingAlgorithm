package 餐厅过滤器;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/24 09:37
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-173/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
 * @限制:
 * @Level:
 */
public class Solution {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ids = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        if (veganFriendly == 1) {
            int index = 0;
            for (int[] r : restaurants) {
                if (r[2] == 1) {
                    if (r[3] <= maxPrice && r[4] <= maxDistance)
                        ids.add(r[0]);
                }
                map.put(r[0], index++);
            }
        }
        else{
            int index = 0;
            for (int[] r : restaurants) {
                if (r[3] <= maxPrice && r[4] <= maxDistance)
                    ids.add(r[0]);
                map.put(r[0], index++);
            }
        }
        System.out.println(ids);

        Collections.sort(ids, (o1, o2) -> {
            if (restaurants[map.get(o1)][1] == restaurants[map.get(o2)][1]) {
                return Integer.compare(o2, o1);
            }
            return Integer.compare(restaurants[map.get(o2)][1], restaurants[map.get(o1)][1]);
        });
        return ids;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
