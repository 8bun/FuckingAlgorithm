package 旅行终点站;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/3 11:47
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-187/problems/destination-city/
 * @限制:
 * @Level:
 */
public class Solution {

    public String destCity(List<List<String>> paths) {
        HashMap<String, String> map=new HashMap<>();
        for (List<String> list:paths){
            map.put(list.get(0),list.get(1));
        }
        String des=map.get(paths.get(0).get(0));
        while (des!=null){
            String tmp=des;
            des=map.get(des);
            if (des==null)
                return tmp;
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
