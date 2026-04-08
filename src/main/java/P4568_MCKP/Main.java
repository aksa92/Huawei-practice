package P4568_MCKP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int l=sc.nextInt();
        double T=sc.nextDouble();
        int t=(int) (T*1000);
        final int INF = Integer.MAX_VALUE / 2;

        int[] loss8=new int[l];
        int[] loss16=new int[l];
        int[] mem8=new int[l];
        int[] mem16=new int[l];

        for(int i=0;i<l;i++) {
            int k=sc.nextInt();
            if(k==2) {
                sc.next();
                double loss8b=sc.nextDouble();
                double mem8b=sc.nextDouble();

                sc.next();

                double loss16b=sc.nextDouble();
                double mem16b=sc.nextDouble();

                loss8[i]=(int)(loss8b*1000);
                mem8[i]=(int)(mem8b*1000);
                loss16[i]=(int)(loss16b*1000);
                mem16[i]=(int)(mem16b*1000);
            }
            if(k==1) {
                sc.next();
                double loss8b=sc.nextDouble();
                double mem8b=sc.nextDouble();

                loss8[i]=(int)(loss8b*1000);
                mem8[i]=(int)(mem8b*1000);
                loss16[i]=INF;
                mem16[i]=INF;
            }
        }

        int[][] dp=new int[l+1][t+1];
        dp[0][0]=0;

        for(int i=1;i<=l;i++) {
            for(int j=0;j<=t;j++) {
                dp[i][j]=INF;
            }
        }

        for(int i=1;i<=l;i++) {
            for(int j=0;j<=t;j++) {
                if(dp[i-1][j]==INF) continue;

                //8bit
                if(j+loss8[i-1]<=t) {
                    dp[i][j+loss8[i-1]]=Math.min(dp[i][j+loss8[i-1]],dp[i-1][j]+mem8[i-1]);
                }
                //16bit
                if(j+loss16[i-1]<=t) {
                    dp[i][j+loss16[i-1]]=Math.min(dp[i][j+loss16[i-1]],dp[i-1][j]+mem16[i-1]);
                }
            }
        }

        int ans=INF;
        for(int j=0;j<=t;j++) {
            ans=Math.min(ans,dp[l][j]);
        }
        System.out.printf("%.2f\n", ans/1000.0);

        sc.close();
    }

}
