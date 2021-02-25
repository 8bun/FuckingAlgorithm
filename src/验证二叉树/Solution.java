package 验证二叉树;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/23 17:48
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-177/problems/validate-binary-tree-nodes/
 * @限制:
 * @Level:
 */
public class Solution {


    boolean[] flag;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        flag = new boolean[n];
        for (int i = 0; i < n; i++){
            if (leftChild[i] != -1 || rightChild[i] != -1){
                boolean tmp = dfs(n, leftChild, rightChild, i);
                if (!tmp) return false;
                for (int j = 0; j < n; j++){
                    if (!flag[j]) return false;
                }
                return true;
            }
        }
        return n==1;
    }
    private boolean dfs(int n, int[] leftChild, int[] rightChild,int index){
        if (flag[index]) return false;
        flag[index] = true;
        boolean res = true;
        if (leftChild[index] != -1) {
            res = dfs(n, leftChild, rightChild, leftChild[index]);
        }
        if (rightChild[index] != -1) {
            res &= dfs(n, leftChild, rightChild, rightChild[index]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] leftChild={-1,0} ;
        int[] rightChild={-1,-1};
        int n = 2;
        boolean res = new Solution().validateBinaryTreeNodes(n,leftChild,rightChild);
        System.out.println();
    }
}
