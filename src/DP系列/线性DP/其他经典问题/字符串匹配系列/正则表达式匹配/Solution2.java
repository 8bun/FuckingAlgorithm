package DP系列.线性DP.其他经典问题.字符串匹配系列.正则表达式匹配;

/**
 * 前面使用回溯法实现正则表达式，
 * 假设s和p的长度分别为len_s,len_p
 * 里面的 public boolean isMatch(String s, String p)是将s和p的前len_s和len_p进行匹配；
 * 由此我们定义dp[i][j]的boolean二维数组，其值表示s的前i个字符和p的前j个字符串是否成功匹配;
 * dp[0][0]==true，因为都是""，
 * 求s与p是否能够成功匹配，在极端的情况下，需要找到s的前len_s个与p的前len_p个是否成功匹配，也就是
 * 找到dp[len_s][len_p]的值；
 * 例如，len_s=3，要求数组的一维出现dp[3]，而下标从0开始，则需要数组一维长度为4，也就是len_s+1
 * 同理得到最后的dp数组维度为dp[len_s+1][len_p+1]；
 *
 */
public class Solution2 {
    //这里的s和p不再被更新，所以需要传入位置，比较首部是否相同
    boolean first_match(String s,String p,int i,int j){
        return s.charAt(i)==p.charAt(j) || p.charAt(j)== '.';
    }

    boolean isMatch(String s,String p){
       Boolean dp[][]=new Boolean[s.length()+1][p.length()+1];
       dp[0][0]=true;  //""
       //初始化dp[0][2],dp[0][3]...dp[0][len_p]，表示p的
        //前2，3...len_p个字符是否与""匹配，我们知道，
        //如果p为xxxx...y*，那么如果xxxx...与""匹配，那么
        //就说xxxx...y*与""匹配，因为y*可以为0个y。
       for(int j=2;j<=p.length();j++){
            dp[0][j] = p.charAt(j-1) =='*' && dp[0][j-2];
       }
       for(int i=0;i<s.length();i++){
           for(int j=0;j<p.length();j++){
               //p中包含*
               if(p.charAt(j)=='*'){
                    //p中包含*的前j+1个与s的前i+1（范围[1,len_s]，也就是s的从第一个字符开始的任意长度连续子串）个进行匹配
                   //例如p：aab*，包含*的字符串有aa,aab,aabb,aabbb...
                   //成功与否，根据Solution.Java有两种逆推，就是
                   // ①(*之前的元素在s中没有出现)
                   // p去掉*和其前一个字符所剩的前(j+1-2)个字符是否
                   //与s匹配；
                   //例如s：dabcc
                   //p：mcda*abcc,p[3]=*，反过来看规则，mcda*与da,去掉p的a*，比对mcd与d
                   //看mcd是否与d成功匹配
                   // ②(*之前的元素在s中出现一次或多次，去掉s首部的前提是s首部与p首部相同)
                   //判断s[i]（i在外围，一个i循环所有j，所以i是相对固定的，是否含有p的*前面的元素）与p[j-1]（*前面的元素）是否相同（p[j]为*）
                   //在查看s中是否包含p的*前面的元素之后,
                   //例如s: bcaabb，s[2]=a
                   //p:bca*bb,p[3]=*
                   //匹配first_match为true的i为2，我们反过来看规则，将s[2]看成首元素，去掉其即看bc是否
                   //与bca*成功匹配。若匹配成功，则第二种途径dp[3][4]=true，即bca与bca*匹配成功
                    dp[i+1][j+1] = dp[i+1][j+1-2] || first_match(s,p,i,j-1) && dp[i][j+1];
               }
               //正常情况
               else{
                   dp[i+1][j+1]=first_match(s,p,i,j) && dp[i][j];
               }

           }
       }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa","a*n"));
    }
}
