package BFS系列.获取你好友已观看的视频;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/25 17:34
 * @Description:
 * 不能用dfs。比如A的好友是B和C；而B的好友是A和C。如果用dfs的搜索路径是A->B->C，这样B是level 1，C是level 2，是错误的。其实B和C都是 level 1好友。
 * 也就是说，在人群中，同一个人，可能有多个 level，必须找到level最小的值才是真实的 level，
 * dfs 搜到的 level 不是最小 level。
 * 如
 * [["A","B"],["C"],["B","C"]]
 * [[1,2],[0,2],[0,1]]
 * 0
 * 2
 * 这个样例，应该输出的是[]
 * 就跟在迷宫中找两点之间的最短路径一样，不能用dfs，要用bfs。
 * 下面给出了当时做的错误dfs，待补bfs
 * @Url: https://leetcode-cn.com/contest/weekly-contest-170/problems/get-watched-videos-by-your-friends/
 * @限制:
 * @Level:
 */
public class Solution {
    /**
     * 错误做法
     */
    private List<Integer> ids = new ArrayList<>();
    private boolean[] vis;
    HashMap<String,Integer> map = new HashMap<>();
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        vis = new boolean[friends.length];
        dfs(id, 0, watchedVideos, friends, level);
        List<String> res = new ArrayList<>();

        for (int i : ids) {

            for (String x : watchedVideos.get(i)) {
                int val = map.getOrDefault(x, 0);
                map.put(x, val + 1);
            }
//            res = Stream.of(res, watchedVideos.get(i))
//                    .flatMap(Collection::stream)
//                    .distinct()
//                    .collect(Collectors.toList());
            Set<String> set = new HashSet<>(watchedVideos.get(i));
            set.addAll(res);
            res = new ArrayList<>(set);
        }
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o1).intValue() == map.get(o2).intValue()) {
                    return o1.compareTo(o2);
                }
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        return res;
    }

    private void dfs(int id, int curLevel, List<List<String>> watchedVideos, int[][] friends, int level) {
        if(level == curLevel) {
            ids.add(id);
            return;
        }
        vis[id] = true;
        for (int f : friends[id]) {
            System.out.println(f);
            if (!vis[f])
                dfs(f, curLevel + 1, watchedVideos, friends, level);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list2.add("A");
        list2.add("D");
//        System.out.println(res.toString());
    }
}

