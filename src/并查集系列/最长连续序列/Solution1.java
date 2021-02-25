package 并查集系列.最长连续序列;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 15:10
 * @Description: 并查集思想
 * 由于这里数可能比较大，所以parent使用HashMap来存
 * 为了统计每个集合的大小，我们维护了一个size HashMap，key为元素，value为元素所在集合的大小
 * 为了提高查询性能，这里为每一个集合维护了一个size，在union时
 * 让大的size集合的parent作为小的size的集合的parent
 * 为了统计当前union之后，当前所有集合种元素最多的size,我们维护了一maxSize
 * @Url:
 * @限制:
 * @Level:
 */
//这个UnionFind中包含了可以包含多个元素的情况，可以看成多个元素在一个UnionFind中实现
class UnionFind{

    HashMap<Integer,Integer> size; //num:the_size_of_the_collection_where_num_is
    HashMap<Integer,Integer> parent; //num:the_parent_of_num
    int maxSize=1; //The size of the most elements in the current collection
    public UnionFind(int[] nums){
        parent=new HashMap<>();
        size=new HashMap<>();
        for (int num:nums) {
            parent.put(num, num);
            size.put(num,1);
        }
    }
    //这里parent中一定存在key为num的，因为构造函数已经初始化了
     int find(int num){
        int tmp=num;
        while (num!=parent.get(num)){
            num=parent.get(num);
        }
        //这里要进行更新，因为HashMap的指向不能只是通过赋值来改变
        parent.put(tmp,num);
        return num;
    }
     void union(int p, int q){
        int rootP=find(p);
        int rootQ=find(q);
        if (rootP==rootQ)return;
        int collectionSizeP=size.get(rootP);
        int collectionSizeQ=size.get(rootQ);
        //让小指向大,同时更新大小
        if (collectionSizeP<collectionSizeQ) {
            parent.put(rootP, rootQ);
            size.put(rootQ,collectionSizeP+collectionSizeQ);
        }
        else {
            parent.put(rootQ, rootP);
            size.put(rootP,collectionSizeP+collectionSizeQ);
        }
        //更新两个集合的size
        //在union之后更新maxSize
        maxSize=Math.max(collectionSizeP+collectionSizeQ,maxSize);
    }
}
public class Solution1 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        UnionFind uf=new UnionFind(nums);

        for (int i=0;i<nums.length;i++){
            if (uf.parent.containsKey(nums[i]-1)) //所有集合中有没有包含比它小1的数，有则合并它们所在的集合
                uf.union(nums[i]-1,nums[i]);
        }
        return uf.maxSize;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
