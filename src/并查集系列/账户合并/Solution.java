package 并查集系列.账户合并;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/8 17:17
 * @Description: name:email存在一对多的关系，只要是email存在相同的,即使
 * 它们的name不同，也认为它们属于同一个人的，可以进行合并，最后返回合并后的结果
 * 我们用输入账户的序号来代表账户。
 * 例如：
 * [
 * 0 [“John”,”johnsmith@mail.com”,”john_newyork@mail.com”],
 * 1 [“John”,”johnsmith@mail.com”,”john00@mail.com”],
 * 2 [“Mary”,”mary@mail.com”],
 * 3 [“John”,”johnnybravo@mail.com”]
 * ]
 * 则有4个账户。
 * 用哈希表建立<邮箱,账户1,…,账户x>的关联。
 * “johnsmith@mail.com” 0 1
 * “john_newyork@mail.com” 0
 * “john00@mail.com” 1
 * “mary@mail.com” 2
 * “johnnybravo@mail.com” 3
 *
 * 对账户建立并查集。同一个邮箱的账户属于同一类。
 * 则分成三类：{0,1};{2};{3}。
 *
 * 将同一类的账户和邮箱收集即可。
 * [
 *  [“John”,”john00@mail.com”,”john_newyork@mail.com”,”johnsmith@mail.com”],
 *  [“Mary”,”mary@mail.com”],
 *  [“John”,”johnnybravo@mail.com”]
 * ]
 * @Url: https://leetcode-cn.com/problems/accounts-merge/
 * @限制:
 * @Level:
 */
public class Solution {

    private class UnionFind{
        private int[] parent;

        public UnionFind(int n){
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i]=i;
        }
        public int find(int x){
            while (x!=parent[x]){
                parent[x]=parent[parent[x]];
                x=parent[x];
            }
            return x;
        }
        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)return;
            parent[rootP] = rootQ;
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        List<List<String>> res = new ArrayList<>();
        if (n == 0)return res;

        UnionFind uf = new UnionFind(n);
        Map<String, Integer> email_IdMap=new HashMap<>();

        for (int id = 0; id < n; id++){
            List<String> curAccount = accounts.get(id);
            int size = curAccount.size();
            //先不管name
            for (int index = 1; index < size; index++){
                String email = curAccount.get(index);
                Integer otherId = email_IdMap.get(email);
                if (otherId == null) email_IdMap.put(email, id);
                else uf.union(id, otherId); //如果当前id有与其他Id相同的email，那么就把它们的id联合起来
            }
        }
        //执行下面的代码，
        //只要email相同，不管name是否相同，都取第一次出现该email的name的id
        //email_IdMap:记录所有email及其第一次出现时所在name的id
        //此前的email_IdMap为：
        //{
        //  {{"johnnybravo@mail.com"},1},
        //  {{"johnsmith@mail.com"},0},  //这个元素也在id=2的行出现过，所以在前面已经将id为0和2联合起来了
        //  {{"john00@mail.com"},0},
        //  {{"john_newyork@mail.com"},2},  //执行到这里，找到联合id=0，将"john_newyork@mail.com"加入到联合id为0的email列表中
        //  {{"mary@mail.com"},3}
        // }

        //将某id a联合的id b的其他不同内容补充到a中
        Map<Integer, List<String>> id_emailsMap=new HashMap<>(n);
        for (Map.Entry<String, Integer> email_Id : email_IdMap.entrySet()){

            String email = email_Id.getKey();
            Integer id = email_Id.getValue();

            //找到这个id关联的其他id
            int rootId = uf.find(id);
            List<String> rootEmails = id_emailsMap.get(rootId);
            //如果rootId在补改版的Map:id_emailsMap中第一次出现
            if (rootEmails == null){
                List<String> list = new ArrayList<>();
                list.add(email);
                id_emailsMap.put(rootId, list);
            }else {
                rootEmails.add(email);
            }
        }
        //最终得到的id_emailsMap为:
        //{
        //  {0:{"johnsmith@mail.com","john00@mail.com","john_newyork@mail.com"}},
        //  {1:{"johnnybravo@mail.com"}},
        //  {3:{"mary@mail.com"}}
        // }

        //将id_emailsMap的每个value组成一个个List，封装到res中
        for (int id : id_emailsMap.keySet()){
            List<String> emails = id_emailsMap.get(id);
            Collections.sort(emails); //对所有email进行排序

            List<String> account = new ArrayList<>();
            account.add(accounts.get(id).get(0)); //根据id先加name
            account.addAll(emails); //将value加进去
            res.add(account);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();

        List<String> account1 = new ArrayList<>();
        account1.add("John");
        account1.add("johnsmith@mail.com");
        account1.add("john00@mail.com");

        List<String> account2 = new ArrayList<>();
        account2.add("John");
        account2.add("johnnybravo@mail.com");

        List<String> account3 = new ArrayList<>();
        account3.add("John");
        account3.add("johnsmith@mail.com");
        account3.add("john_newyork@mail.com");

        List<String> account4 = new ArrayList<>();
        account4.add("Mary");
        account4.add("mary@mail.com");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);

        Solution solution = new Solution();
        List<List<String>> res = solution.accountsMerge(accounts);
        System.out.println(res);
    }
}
