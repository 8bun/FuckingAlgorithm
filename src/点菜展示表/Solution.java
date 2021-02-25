package 点菜展示表;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/19 10:58
 * @Description:
 * @Url: https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/submissions/
 * @限制:
 * @Level:
 */
public class Solution {

    public List<List<String>> displayTable(List<List<String>> orders) {

        //table:<餐名:数量>//每个相同的key可以有多个HashMap，因为他们的地址不一样,如table={3:{Ceviche:2, Fried Chicken:1...}}
        TreeMap<Integer,HashMap<String,Integer>> map=new TreeMap<>();
        TreeSet<String> dishNameSet=new TreeSet<>();
        for (List<String> order : orders){
            Integer tableNum=Integer.valueOf(order.get(1));
            String dishName=order.get(2);
            dishNameSet.add(dishName);
            //如果桌子已经有人点过餐
            HashMap<String, Integer> dishMap;
            if(map.containsKey(tableNum)){
                dishMap = map.get(tableNum);
                //如果该桌上没有人点过相同的餐
                if(!dishMap.containsKey(dishName)){
                    dishMap.put(dishName,1);
                }else {
                    dishMap.put(dishName, dishMap.get(dishName) + 1);
                }
            }
            //该桌上没有人点过餐
            else {
                dishMap = new HashMap<>();
                dishMap.put(dishName,1);
            }
            map.put(tableNum,dishMap);
        }
        List<List<String>> res=new ArrayList<>();  //结果

        List<String> dishNameList=new ArrayList<>();

        dishNameList.add("Table");
        while (!dishNameSet.isEmpty())
            dishNameList.add(dishNameSet.pollFirst());
        res.add(dishNameList);
        //遍历所有table
        for (Integer tableNum:map.keySet()){
            //每个桌的对应各种食品的点餐情况
            List<String> tableList=new ArrayList<>();
            tableList.add(String.valueOf(tableNum));
            //遍历所有食品
            for (int i=1;i<dishNameList.size();i++){
                if (!map.get(tableNum).containsKey(dishNameList.get(i))){
                    tableList.add("0");
                }
                else
                    tableList.add(String.valueOf(map.get(tableNum).get(dishNameList.get(i))));
            }
            res.add(tableList);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> list=new ArrayList<>();
        List<String> s1=new ArrayList<>();
        s1.add("David");s1.add("3");s1.add("Ceviche");
        List<String> s2=new ArrayList<>();
        s2.add("Corina");s2.add("10");s2.add("Beef Burrito");
        List<String> s3=new ArrayList<>();
        s3.add("David");s3.add("3");s3.add("Fried Chicken");
        List<String> s4=new ArrayList<>();
        s4.add("Carla");s4.add("5");s4.add("Water");
        List<String> s5=new ArrayList<>();
        s5.add("Carla");s5.add("5");s5.add("Ceviche");
        List<String> s6=new ArrayList<>();
        s6.add("Rous");s6.add("3");s6.add("Ceviche");
        List<String> s7=new ArrayList<>();
        s7.add("Carla");s7.add("5");s7.add("Ceviche");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        List<List<String>> res=new Solution().displayTable(list);
        for (List<String> list1:res){
            for (String string:list1){
                System.out.print(string+" ");
            }
            System.out.println();
        }
    }
}
