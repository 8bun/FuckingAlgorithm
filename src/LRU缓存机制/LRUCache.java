package LRU缓存机制;

import java.util.HashMap;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/lru-cache/
 * @since 2021/03/24
 */

public class LRUCache {
    private class Node {
        public int key, val;
        public Node next, prev;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
    /**
     * 构造一个双向链表api
     */
    private class DoubleList {
        private Node head, tail;
        private int size;
        public void addFirst(Node node) {
            if(head == null) {
                head = tail = node;
            }
            else {
                Node n = head;
                n.prev = node;
                node.next = n;
                head = node;
            }
            size++;
        }
        public void remove(Node node) {
            if(node == head && node == tail) {
                head = tail = null;
            }
            else if(tail == node) {
                tail.prev.next = null;
                tail = tail.prev;
            }
            else if(head == node) {
                head.next.prev = null;
                head = head.next;
            }
            else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
        public Node removeLast() {
            Node node = tail;
            remove(tail);
            return node;
        }
        public int size() {
            return size;
        }
    }
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            int val = map.get(key).val;
            put(key, val);
            return val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node x = new Node(key, value);
        if(map.containsKey(key)) {
            cache.remove(map.get(key));
        }
        else {
            if(cap == cache.size()) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(x);
        map.put(key, x);
    }
}
