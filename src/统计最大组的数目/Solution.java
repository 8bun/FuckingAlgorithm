package 统计最大组的数目;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/11 10:46
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {
    private HashMap<Integer,Integer> map=new HashMap<>();
    public int countLargestGroup(int n) {
        for(int i=1;i<=n;i++){
            map.put(count(i),map.getOrDefault(count(i),0)+1);
        }
        int res=Integer.MIN_VALUE;
        for(Integer m:map.keySet()){
            res=Math.max(map.get(m),res);
        }
        int cnt=0;
        for(Integer m:map.keySet()){
            if(res==map.get(m))cnt++;
        }
        return cnt;
    }
    private int count(int num){
        int sum=0;
        String s=String.valueOf(num);
        for(char ch:s.toCharArray()){
            sum+=(ch-'0');
        }
        return sum;
    }
}