package 复杂链表的复制;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @url: https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 * @author: Jack
 * @createTime: 2020/2/29 0:23
 * @level: 中等
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node node = head; //遍历原链表的指针
        while (node != null) {
            //复制节点
            Node newNode = new Node(node.val);
            //使用原链表节点来找到复制链表的结点（因为一开始newNode都是散的，通过索引查找可节省时间）
            map.put(node, newNode);
            node = node.next;
        }
        node = head;
        //建立copyNode之间的联系
        while (node != null) {
            Node copyNode = map.get(node);//找到node对应的复制结点
            copyNode.next = map.get(node.next); //找到node.next对应的复制结点
            copyNode.random = map.get(node.random); //找到node.random对应的复制结点
            node = node.next;
        }
        return map.getOrDefault(head, null);  //如果head为null，返回null
    }
}
