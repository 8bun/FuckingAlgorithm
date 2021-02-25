package 收藏清单;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/17 10:46
 * @Description: 比较暴力
 * @Url: https://leetcode-cn.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/
 * @限制:
 * @Level:
 */
public class Solution {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> res =new ArrayList<>();
        for (int i = 0; i < favoriteCompanies.size(); i++){
            boolean flag=false;
            for (int j = 0; j< favoriteCompanies.size(); j++){

                if (j==i) continue;
                List<String> son = favoriteCompanies.get(i);
                List<String> father = favoriteCompanies.get(j);
                if (isValid(son, father)) {
                    flag=true;break;
                }
            }
            if (!flag)
                res.add(i);
        }
        return res;
    }
    private boolean isValid(List<String> son, List<String> father){
        HashSet<String> sons=new HashSet<>();
        HashSet<String> fas=new HashSet<>();
        for (String string : son) sons.add(string);
        for (String string : father) fas.add(string);
        return fas.containsAll(sons);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> son = new ArrayList<>();
        List<String> f = new ArrayList<>();
        son.add("google");
        son.add("facebook");
        f.add("leetcode");
        f.add("google");
        f.add("facebook");
        boolean is = new Solution().isValid(son,f);
        System.out.println(is);
    }
}
