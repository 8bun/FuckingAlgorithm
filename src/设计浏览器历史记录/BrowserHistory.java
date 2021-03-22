package 设计浏览器历史记录;

import java.util.LinkedList;

/**
 * @author cwq
 * @since 2020/6/8 23:28
 * @Description:
 * @link
 * @限制:
 * @Level:
 */
public class BrowserHistory {

    int curPos;
    private LinkedList<String> list;

    public BrowserHistory(String homepage) {
        list = new LinkedList<>();
        list.addFirst(homepage);
        curPos = 0;
    }

    public void visit(String url) {
        int cnt = list.size() - curPos - 1;
        for (int i = 0; i < cnt; i++)
            list.removeLast();
        list.addLast(url);
        curPos += 1;
    }

    public String back(int steps) {
        if (list.isEmpty()) {
            curPos = - 1;
            return null;
        }
        curPos = Math.max(curPos - steps, 0);
        return list.get(curPos);
    }

    public String forward(int steps) {
        if (list.isEmpty()) {
            curPos = - 1;
            return null;
        }
        if (curPos == list.size() - 1)
            return list.get(curPos);
        curPos = curPos + Math.min(list.size() - 1 - curPos, steps);
        return list.get(curPos);
    }
}
