package LFU缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * 在缓存满的时候，删除缓存里使用次数最少的元素，然后在缓存中放入新元素；
 * 数据的访问次数很重要，访问次数越多，就越不容易被删除；
 * 根据题意，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除，
 * 即在「访问次数」相同的情况下，按照时间顺序，先删除在缓存里时间最久的数据。
 * 说明：本题其实就是在「力扣」第 146 题：LRU缓存机制 的基础上，在删除策略里多考虑了一个维度（「访问次数」）的信息。
 * 核心思想：先考虑访问次数，在访问次数相同的情况下，再考虑缓存的时间
 * @author cwq
 * @link https://leetcode-cn.com/problems/lfu-cache/solution/java-13ms-shuang-100-shuang-xiang-lian-biao-duo-ji/#o1-%E8%A7%A3%E6%B3%95-%E2%80%94%E2%80%94-%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8F%8C%E5%90%91%E9%93%BE%E8%A1%A8
 * @since 2021/03/24
 */

public class LFUCache {

    private static class Node {
        int key;
        int value;
        int freq = 1;
        Node pre;
        Node post;

        public Node() {}

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoublyLinkedList {
        Node head;
        Node tail;

        /**
         * 初始化两个空的头、尾
         */
        public DoublyLinkedList() {
            head = new Node();
            tail = new Node();
            head.post = tail;
            tail.pre = head;
        }

        void removeNode(Node node) {
            node.pre.post = node.post;
            node.post.pre = node.pre;
        }

        /**
         * 头插法，所以越久未被访问的会放到离尾部越近的位置
         */
        void addNode(Node node) {
            node.post = head.post;
            head.post.pre = node;
            head.post = node;
            node.pre = head;
        }
    }
    /**
     * 存储缓存内容
     */
    private final Map<Integer, Node> cache;

    /**
     * 存储每个频次对应的双向链表
     */
    private final Map<Integer, DoublyLinkedList> freqMap;
    private int size;
    private final int cap;
    /**
     * 存储当前最小的频次
     */
    private int minFreq;
    public LFUCache(int capacity) {
        cache = new HashMap<>();
        freqMap = new HashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }
        freqUpdate(node);
        return node.value;
    }
    /**
     * 从freqMap中freq对应的链表里移除node, 并更新minFreq
     */
    private void freqUpdate(Node node) {
        int freq = node.freq;
        DoublyLinkedList list = freqMap.get(freq);
        list.removeNode(node);
        // 如果node的频次链表在freqMap中的key为最低，也就是频次最低的
        // 并且删掉node之后链表为空，那么更新minFreq
        if(minFreq == freq && list.head.post == list.tail) {
            minFreq = freq + 1;
        }
        // 加入新freq到对应的链表
        node.freq++;
        list = freqMap.getOrDefault(freq + 1, new DoublyLinkedList());
        list.addNode(node);
        freqMap.put(freq + 1, list);
    }

    public void put(int key, int value) {
        if(cap == 0) {
            return;
        }
        Node node = cache.get(key);
        if(node != null) {
            node.value = value;
            freqUpdate(node);
        }
        // 如果不存在
        else {
            if(size == cap) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                // 结合addNode的思想,这里删除的应该是越久被访问的(tail是一个空结点，根据Node定义)
                cache.remove(minFreqList.tail.pre.key);
                minFreqList.removeNode(minFreqList.tail.pre);
                size--;
            }

            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 新node的freq为1
            DoublyLinkedList linkedList = freqMap.getOrDefault(1, new DoublyLinkedList());
            linkedList.addNode(newNode);
            freqMap.put(1, linkedList);
            size++;
            minFreq = 1;
        }
    }
}
