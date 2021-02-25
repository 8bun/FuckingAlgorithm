package 天梯;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @description：将含有某个字符的字符串放到ArrayList的对应位置，例如ab，放到0,1的位置
 * 然后下一个a放到0的位置，然后更新ans
 * @url：http://47.107.241.102/problem/GPLT2020-L2-1
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 19:39
 * @level：
 */
public class Main9 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();

        ArrayList<ArrayList<String>> allList=new ArrayList<>();
        for(int i=0;i<26;i++){
            ArrayList<String> list=new ArrayList<>();
            allList.add(list);
        }
        TreeSet<String> strings=new TreeSet<>(); //保存去重字符串
        int ans=0;
        for(int i=0;i<n;i++){
            String str=scanner.next();
            if(!strings.contains(str)) {
                strings.add(str);
                ans++;
                Set<Character> charSets = getSet(str);  //获取字符串的所有不同字符
                ArrayList<Integer> places=new ArrayList<>(); //所有插入的位置，如果插入的位置已经有字符串了，那么等效
                for (Character ch : charSets) { //这些ch位置字符串等效
                   places.add(ch-'a');
                }
                for (Integer place:places){
                    if(allList.get(place).size()!=0&& ans>1){  //如果插入的位置已经有字符串了，那么等效
                        ans--;
                    }
                    allList.get(place).add(str);//加入相应位置
                }
            }
        }
        System.out.println(ans);
    }

    //去重字符
    public static Set<Character> getSet(String str){
        TreeSet<Character> set=new TreeSet<>();
        for(char ch:str.toCharArray()){
            set.add(ch);
        }
        return set;
    }
}
