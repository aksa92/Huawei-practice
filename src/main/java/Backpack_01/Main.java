package Backpack_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[] w=new int[n+1];
        int[] v=new int[n+1];
        for(int i=1;i<=n;i++)
        {
            w[i]=sc.nextInt();
            v[i]=sc.nextInt();
        }
        //数组清零

        int[][] dp=new int[n+1][m+1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                //如果当前容量小于当前物品的容量
                //则当前容量的背包价值等于上一层背包价值
                if(j<w[i]) {
                    dp[i][j]=dp[i-1][j];
                    continue;
                }
                //如果当前容量大于当前物品的容量
                //不选的话的话继承上一层状态
                //选的话，则当前容量的背包价值等于上一层背包价值和当前物品价值
                //和上一层容量减去当前物品容量的背包价值之和
                else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
