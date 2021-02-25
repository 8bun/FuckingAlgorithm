package LRU缓存;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @description：
 * 取出不是remove，放入可能涉及删除
 * 有的话，取出之后放在最前，其他下沉，没有返回-1
 * 放入要考虑是否key已经存在，存在的话就是更新，要O(1)找到该存在的key
 * 未满放入，放入的在最前，其他下沉
 * 满后放入，放入的在最前，最底的置换出来，其他下沉
 * 定义HashMap<key,value>，保存放入的所有key,value
 * 定义LinkedList<key>，保存放入的key的位置关系，双向，可以实现O(1)的查找以及删除
 * @url： https://leetcode-cn.com/problems/lru-cache-lcci/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 11:29
 * @level：
 */
public class LRUCache {

    private HashMap<Integer,Integer> saveMap;
    private LinkedList<Integer> keyList;

    private int capacity;

    public LRUCache(int capacity) {
        saveMap=new HashMap<>(capacity);
        keyList=new LinkedList<>();
        this.capacity=capacity;
    }

    public int get(int key) {
        if(saveMap.containsKey(key)){
            keyList.remove((Integer)key);
            keyList.addFirst(key);  //添加在最前面
            return saveMap.get(key);
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if(saveMap.containsKey(key)){
            saveMap.put(key,value);
            keyList.remove((Integer) key);
            keyList.addFirst(key);
        }
        //不存在key，有两种情况，满后放入和未满后放入
        else {
            //满后放入，删除最底的那个
            if(keyList.size()==capacity) {
                int removeKey=keyList.removeLast();
                saveMap.remove(removeKey);
                keyList.addFirst(key);
                saveMap.put(key,value);
            }
            else {
                keyList.addFirst(key);
                saveMap.put(key,value);
            }
        }
    }
}
