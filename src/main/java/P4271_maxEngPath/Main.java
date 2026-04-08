package P4271_maxEngPath;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int h=sc.nextInt();
        int w=sc.nextInt();
        int k=sc.nextInt();
        int k2=sc.nextInt();

        double[][] grp=new double[h][w];
        double[][] stg=new double[k][k];
        double[][] eng=new double[h][w];

        for(int i=0;i<h;i++) {
            for(int j=0;j<w;j++) {
                grp[i][j]=sc.nextDouble();
            }
        }
        for(int i=0;i<k;i++) {
            for(int j=0;j<k;j++) {
                stg[i][j]=sc.nextDouble();
            }
        }

        int mid;

        for(int i=0;i<h;i++) {
            for(int j=0;j<w;j++) {
                double sum=0;

                for(int ii=0;ii<k;ii++) {
                    for(int jj=0;jj<k;jj++) {
                        int x=i-(k/2)+ii;
                        int y=j-(k/2)+jj;

                        if(x>=0&&x<h&&y>=0&&y<w) {
                            sum += grp[x][y]*stg[ii][jj];
                        }
                    }
                }
                eng[i][j]=sum;
            }
        }

        double[][] dp=new double[h][w];
        //初始化
        for(int i=0;i<h;i++) {
            dp[i][0]=eng[i][0];
        }

        for(int j=1;j<w;j++) {
            for(int i=0;i<h;i++) {
                if(i!=0&&i!=h-1) dp[i][j]=eng[i][j]+Math.max(Math.max(dp[i-1][j-1],dp[i][j-1]),dp[i+1][j-1]);
                if(i==0) dp[i][j]=eng[i][j]+Math.max(dp[i][j-1],dp[i+1][j-1]);
                if(i==h-1) dp[i][j]=eng[i][j]+Math.max(dp[i-1][j-1],dp[i][j-1]);
            }
        }

        double ans=0;
        for(int i=0;i<h;i++) {
            ans=Math.max(ans,dp[i][w-1]);
        }
        System.out.printf("%.1f\n",ans);
    }
}