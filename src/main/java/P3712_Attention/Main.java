package P3712_Attention;

import java.util.*;

public class Main {
    public static int[][] matrixMuit(int[][] a,int[][] b) {
        int n=a.length;//a行数
        int m=a[0].length;//a列数，b行数
        int h=b[0].length;//b列数

        int[][] ans=new int[n][h];
        for(int i=0;i<n;i++) {
            for(int j=0;j<h;j++) {
                int sum=0;
                for(int k=0;k<m;k++) {
                    sum += a[i][k]*b[k][j];
                }
                ans[i][j]=sum;
            }
        }
        return ans;
    }
    public static void main(String[] args) {


        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int h=sc.nextInt();

        int[][] x=new int[n][m];
        int[][] w1=new int[m][h];
        int[][] w2=new int[m][h];
        int[][] w3=new int[m][h];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                x[i][j]=1;
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<=i&&j<h;j++) {
                w1[i][j]=0;
                w2[i][j]=0;
                w3[i][j]=0;
            }
            for(int j=i;j<h;j++) {
                w1[i][j]=1;
                w2[i][j]=1;
                w3[i][j]=1;
            }
        }
        int[][] q=new int[n][h];
        int[][] k=new int[n][h];
        int[][] v=new int[n][h];

        q=matrixMuit(x,w1);
        for(int i=0;i<n;i++) {
            for(int j=0;j<h;j++) {
                k[i][j]=q[i][j];
                v[i][j]=q[i][j];
            }
        }

        int[][] kt=new int[h][n];
        for(int i=0;i<h;i++) {
            for(int j=0;j<n;j++) {
                kt[i][j]=k[j][i];
            }
        }

        double H=Math.sqrt(h);
        double[][] M=new double[n][n];
        int[][] qkt=new int[n][n];
        qkt=matrixMuit(q,kt);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                M[i][j]=(double)(qkt[i][j]/H);
            }
        }
        for(int i=0;i<n;i++) {
            double sum=0;
            for(int j=0;j<n;j++) {
                sum += M[i][j];
            }
            for(int j=0;j<n;j++) {
                M[i][j] /= sum;
            }
        }
        double[][] M1=new double[n][h];
        for(int i=0;i<n;i++) {
            for(int j=0;j<h;j++) {
                double sum=0;
                for(int z=0;z<n;z++) {
                    sum += M[i][z]*v[z][j];
                }
                M1[i][j]=sum;
            }
        }
        /*for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(M1[i][j]+" ");
            }
            System.out.println();
        }*/
        double ans=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<h;j++) {
                ans += M1[i][j];
            }
        }
        System.out.println(Math.round(ans));
    }
}