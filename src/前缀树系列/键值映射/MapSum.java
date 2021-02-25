package 前缀树系列.键值映射;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/30 21:23
 * @Description:
 * @Url: https://leetcode-cn.com/problems/map-sum-pairs/
 * @限制:
 * @Level:
 */
class TireNode{
    boolean isWord;
    TireNode[] next;
    int val;
    public TireNode(){
        next=new TireNode[26];
        isWord=false;
        val=-1;
    }
}
public class MapSum {
    private TireNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root=new TireNode();
    }

    public void insert(String key, int val) {
        TireNode ptr=root;
        for (char ch:key.toCharArray()){
            if (ptr.next[ch-'a']==null)
                ptr.next[ch-'a']=new TireNode();
            ptr=ptr.next[ch-'a'];
        }
        ptr.isWord=true;
        ptr.val=val;
    }

    public int sum(String prefix) {
        TireNode ptr=root;
        boolean flag=true; //是否完整遍历prefix，完整则说明前缀树中包含prefix的前缀路径,不完整说明不含prefix的前缀路径
        //在前缀树中遍历前缀prefix
        for (char ch:prefix.toCharArray()){
            if (ptr.next[ch-'a']==null) {
                flag=false;
                break;
            }
            ptr=ptr.next[ch-'a'];
        }
        return !flag?0:dfs(ptr);
    }

    /**
     * dfs计算start结点开始的所有路径的val的和
     * @param start
     * @return
     */
    public int dfs(TireNode start){
        if (start==null)
            return 0;
        int cnt=0;
        if (start.isWord)
            cnt+=root.val;
        for (TireNode nextNode:start.next)
            cnt+=dfs(nextNode);
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapSum mapSum=new MapSum();
        mapSum.insert("acd",3);
        int sum1=mapSum.sum("acd");
        System.out.println(sum1);
        mapSum.insert("ac",1);
        int sum2=mapSum.sum("ac");
        System.out.println(sum2);
    }
}
