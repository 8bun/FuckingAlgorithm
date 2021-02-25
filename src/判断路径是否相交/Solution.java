package 判断路径是否相交;

import java.util.HashSet;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/28 18:51
 * @Description: 
 * @Url: 
 * @限制: 
 * @Level: 
 */
public class Solution {

    public boolean isPathCrossing(String path) {
        HashSet<String> set = new HashSet<>();
        int x = 0, y = 0;
        set.add(x + "," + y);
        for(char ch : path.toCharArray()) {
            if(ch == 'N') y++;
            else if(ch == 'S') y--;
            else if(ch == 'W') x--;
            else if(ch == 'E') x++;
            if(set.contains(x + "," + y)) return true;
            set.add(x + "," + y);
        }
        return false;
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         boolean res = new Solution().isPathCrossing("NESWW");
        System.out.println(res);
    }
}
