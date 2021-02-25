package 不同整数的最少数目;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/6/14 10:36
 * @Description: 
 * @Url: 
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int len = arr.length;
        ArrayList<Integer> a = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            a.add(arr[i]);
            Integer cnt = map.getOrDefault(arr[i], 0);
            cnt++;
            map.put(arr[i], cnt);
        }
        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (!map.get(o1).equals(map.get(o2)))
                    return Integer.compare(map.get(o1), map.get(o2));
                return Integer.compare(o1, o2);
            }
        });
        for(int i = k; i < len; i++) {

            set.add(a.get(i));
        }
        return set.size();
    }
    public static void main(String[] args){

         int[] arr = {24,119,157,446,251,117,22,168,374,373,323,311,441,213,120,412,200,236,328,24,164,104,331,32,19,223,89,114,152,82,456,381,355,343,157,245,443,368,229,49,82,16,373,142,240,125,8};
         int res = new Solution().findLeastNumOfUniqueInts(arr, 41);
         System.out.println(res);
    }
}
