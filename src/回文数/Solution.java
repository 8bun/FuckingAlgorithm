package 回文数;

class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int rev_x=0;
        int temp_x=x;
        int r;
        while(temp_x!=0){
            r=temp_x%10;
            temp_x/=10;
            rev_x=rev_x*10+r;
        }
        return rev_x==x;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().isPalindrome(10));
    }
}
