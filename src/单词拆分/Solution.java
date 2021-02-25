package 单词拆分;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/26 16:59
 * @Description:
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * @Url: https://leetcode-cn.com/problems/word-break/
 * @限制:
 * @Level:
 */
public class Solution {

    /**
     * 记忆化递归写法，为了找到解，我们可以检查字典单词中每一个单词的可能前缀，如果在字典中出现过，
     * 那么去掉这个前缀后剩余部分回归调用。同时，如果某次函数调用中发现整个字符串都已经被拆分且在字典中出现过了，
     * 函数就返回 true
     * @param s
     * @param wordDict
     * @return
     */
    private HashMap<String,Boolean> map=new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if ("".equals(s))
            return true;
        boolean res=false;
        if (map.containsKey(s))
            return false;
        //可截取的长度
        //这里有一个超时的问题：
        //就是例如s=20个a+1个b组成的,a是属于dict的
        //那么遍历找的顺序a+[19a+b]->然后19a+b又会调用a+[18a+b]...
        //第二层aa+[18a+b]找18a+b是否可以拆分成功，但是这个已经在第一层算过了，所以造成了重复的计算
        //所以可使用HashMap<s,boolean>来保存要判断的字符串是否已经被算过了，如果算过并且字符串不可以被拆分那就
        //把字符串记录给map，每次递归先判断map是否有含字符串，如果有就直接返回false
        //由于这里是走不通的情况大概率大于走得通的情况，所以map来记录失败的字符串还是更优的
        for (int len = 1; len <= s.length(); len++) {
            String prefix = s.substring(0, len); //截取前缀
            if (wordDict.contains(prefix)){
                res = res || wordBreak(s.substring(len),wordDict);
                if (!res)
                    map.put(s.substring(len),false);
            }
        }
        return res;
    }

    /**
     * BFS做法
     * 树存储还没有拆分的字符串的起始下标
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Queue<Integer> queueIndex=new LinkedList<>();
        int end=s.length();
        Boolean[] visited=new Boolean[end];
        Arrays.fill(visited,false);
        queueIndex.add(0);
        while (!queueIndex.isEmpty()){
            int startIndex=queueIndex.poll();
            if (!visited[startIndex]) {
                //开始遍历还没有拆分的字符串
                for (int i = startIndex + 1; i <= end; i++) {
                    String prefix = s.substring(startIndex, i); //截取前缀
                    if (wordDict.contains(prefix)) {
                        queueIndex.add(i);
                        //如果下一个开始拆分的字符串的起始下标为s.length
                        if (i == end)
                            return true;
                    }
                }
            }
            visited[startIndex]=true;
        }
        return false;
    }

    /**
     * 动态规划
     * 这个方法的想法是对于给定的字符串（s）可以被拆分成子问题 s1 和 s2 。
     * 如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 ss 也可以满足。
     * 也就是，如果 "catsanddog" 可以拆分成两个子字符串 "catsand" 和 "dog" 。
     * 子问题 "catsand" 可以进一步拆分成 "cats" 和 "and" ，
     * 这两个独立的部分都是字典的一部分，所以 "catsand" 满足题意条件，再往前， "catsand"
     * 和 "dog" 也分别满足条件，所以整个字符串 "catsanddog" 也满足条件。
     * 设dp[i]表示a[0]...a[i]能否成功拆分
     * 则a[0]...a[i]如果不进行拆分，也就是组成的字符串就存在于dict里面，那么dp[i]=true
     * 如果对a[0]...a[i]进行拆分，那么假设从头开始拆分，以l为拆分后第一部分字符串（有可能是多个拆分）的末尾，a[0]...a[l] | a[l+1]...a[i],
     * l的范围是[0,i-1]
     * 如果前半部分是可以成功拆分的，也就是dp[l]=true，并且后半部分在字典内，那么dp[i]=true
     * 同样的，也可以反过来分析
     * 具体分析：https://leetcode-cn.com/problems/word-break/solution/dong-tai-gui-hua-python-dai-ma-by-liweiwei1419-2/
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len=s.length();
        boolean[] dp=new boolean[len];

//        for (int i=0;i<len;i++){ //dp[i]表示s[0]...s[i]能否成功拆分
//            //s[0]...s[i]如果不进行拆分，也就是组成的字符串就存在于dict里面，那么dp[i]=true
//            if (wordDict.contains(s.substring(0,i+1))){
//                dp[i]=true;continue;
//            }
//            //如果对s[0]...s[i]进行拆分，并且分为s[0]..s[l],s[l+1]..s[i]两部分
//            for (int l=0;l<i;l++){
//                String suffix=s.substring(l+1,i+1);//后半部分
//                if (dp[l]&&wordDict.contains(suffix)){
//                    dp[i]=true;break; //满足条件之后跳出循环即可，因为只需要找到一种方案满足
//                }
//            }
//        }
        for (int i=len-1;i>=0;i--){ //dp[i]表示s[i]...s[len-1]能否成功被拆分
            //s[i]...s[len-1]如果不进行拆分，也就是组成的字符串就存在于dict里面，那么dp[i]=true
            if (wordDict.contains(s.substring(i,len))){
                dp[i]=true;continue;
            }
            //如果对s[i]...s[len-1]进行拆分，并且分为s[i]...s[l], s[l+1]...s[len-1]两部分,l的范围是[i,len-2]
            //根据前面的，这次反过来应该是取前缀部分，因为后半部分的dp是先求的
            for (int l=i;l<len-1;l++){
                String prefix=s.substring(i,l+1);//前半部分
                if (dp[l+1]&&wordDict.contains(prefix)){
                    dp[i]=true;break; //满足条件之后跳出循环即可，因为只需要找到一种方案满足
                }
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> wordList=new ArrayList<>();
        String[] words={"a","b","ab","cd","cdc","c"};
        for (String word:words){
            wordList.add(word);
        }
        boolean res=new Solution().wordBreak2("ababcdc",wordList);
        System.out.println(res);
    }

}
