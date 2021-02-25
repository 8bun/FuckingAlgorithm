package 单词接龙;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/26 14:13
 * @Description:
 * @Url: https://leetcode-cn.com/problems/word-ladder/
 * @限制:
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * @Level:
 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        int len=endWord.length();
        Map<String,List<String>> match_words_map=generate_Matchwords_Map(wordList,len);
        Queue<Pair<String,Integer>> pairQueue=new LinkedList<>();
        pairQueue.add(new Pair<>(beginWord,1));
        Map<String,Boolean> visited=new HashMap<>();
        visited.put(beginWord,true);
        return bfs(match_words_map,pairQueue,visited, endWord,len);
    }

    /**
     * 生成<通配符，{对应字符串}>map
     * @param wordList
     * @param len
     * @return
     */
    private Map<String,List<String>> generate_Matchwords_Map(List<String> wordList,int len){
        Map<String,List<String>> map=new HashMap<>();
        for (String word:wordList){
            for (int i=0;i<len;i++){ //每个单词有len种通配
                String match=word.substring(0,i)+"*"+word.substring(i+1);
                List<String> list=map.getOrDefault(match,new ArrayList<>());
                list.add(word);
                map.put(match,list);
            }
        }
        return map;
    }

    /**
     * 可优化方向：双向BFS
     * 算法与之前描述的标准广搜方法相类似。
     * 唯一的不同是我们从两个节点同时开始搜索，同时搜索的结束条件也有所变化。
     * 我们现在有两个访问数组，分别记录从对应的起点是否已经访问了该节点。
     * 如果我们发现一个节点被两个搜索同时访问，就结束搜索过程。因为我们找到了双向搜索的交点。过程如同从中间相遇而不是沿着搜索路径一直走。
     * 双向搜索的结束条件是找到一个单词被两边搜索都访问过了。
     * 最短变换序列的长度就是中间节点在两边的层次之和。因此，我们可以在访问数组中记录节点的层次
     * 这里给出单向BFS遍历
     * @param match_words_map
     * @param pairQueue
     * @param visited
     * @param endWord
     * @param len
     * @return
     */
    private int bfs(Map<String, List<String>> match_words_map, Queue<Pair<String, Integer>> pairQueue, Map<String, Boolean> visited, String endWord, int len){
        while (!pairQueue.isEmpty()){
            Pair<String,Integer> firstPair=pairQueue.poll();
            String word=firstPair.getKey();
            Integer level=firstPair.getValue();
            //遍历该单词的所有通配
            for (int i=0;i<len;i++){
                String match=word.substring(0,i)+"*"+word.substring(i+1);
                //找到相同通配的其他单词
                List<String> list = match_words_map.getOrDefault(match, new ArrayList<>());
                for (String simpleWord:list){
                    if (simpleWord.equals(endWord))
                        return level+1;
                    //没有访问过的相似单词加入队列
                    if (!visited.containsKey(simpleWord)){
                        pairQueue.add(new Pair<>(simpleWord,level+1));
                        visited.put(simpleWord,true);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String beginWord="hit";
        String endWord="cog";
        List<String> wordList=new ArrayList<>();
        String[] words={"hot","dot","dog","lot","log","cog"};
        for (String word:words){
            wordList.add(word);
        }
        int res=new Solution().ladderLength(beginWord,endWord,wordList);
        System.out.println(res);
    }
}
