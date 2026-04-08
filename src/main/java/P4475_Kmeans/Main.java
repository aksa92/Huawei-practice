package P4475_Kmeans;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        int m=sc.nextInt();
        int n=sc.nextInt();

        double[][] p=new double[m][4];
        double[][] c=new double[k][4];
        int[] g=new int[m];

        for(int i=0;i<m;i++){
            for(int j=0;j<4;j++){
                p[i][j]=sc.nextDouble();
            }
        }

        for(int i=0;i<k;i++){
            for(int j=0;j<4;j++){
                c[i][j]=p[i][j];
            }
        }

        int[] cnt=new int[k];
        for(int r=0;r<n;r++) {
            for(int i=0;i<k;i++) {
                cnt[i]=0;
            }
            //findG
            for(int i=0;i<m;i++) {
                double dis=Math.pow(p[i][0]-c[0][0],2)+Math.pow(p[i][1]-c[0][1],2)+Math.pow(p[i][2]-c[0][2],2)+Math.pow(p[i][3]-c[0][3],2);
                g[i]=0;
                for(int j=1;j<k;j++) {
                    double ndis=Math.pow(p[i][0]-c[j][0],2)+Math.pow(p[i][1]-c[j][1],2)+Math.pow(p[i][2]-c[j][2],2)+Math.pow(p[i][3]-c[j][3],2);
                    if(ndis<dis) {
                        dis=ndis;
                        g[i]=j;
                    }
                }
            }
            //newC
            double[][] oldc=new double[k][4];
            for(int z=0;z<k;z++) {
                oldc[z]=c[z].clone();
            }
            for(int i=0;i<k;i++) {
                double c0=0;
                double c1=0;
                double c2=0;
                double c3=0;
                for(int j=0;j<m;j++) {
                    if(g[j]==i) {
                        c0 += p[j][0];
                        c1 += p[j][1];
                        c2 += p[j][2];
                        c3 += p[j][3];
                        cnt[i]++;
                    }
                }
                if(cnt[i]>0) {
                    c[i][0] = c0 / cnt[i];
                    c[i][1] = c1 / cnt[i];
                    c[i][2] = c2 / cnt[i];
                    c[i][3] = c3 / cnt[i];
                }
                else {
                    c[i][0]=oldc[i][0];
                    c[i][1]=oldc[i][1];
                    c[i][2]=oldc[i][2];
                    c[i][3]=oldc[i][3];
                }
            }
            //check
            double maxdis=0;
            for(int i=0;i<k;i++) {
                double dis=Math.pow(oldc[i][0]-c[i][0],2)+Math.pow(oldc[i][1]-c[i][1],2)+Math.pow(oldc[i][2]-c[i][2],2)+Math.pow(oldc[i][3]-c[i][3],2);
                maxdis=Math.max(maxdis,dis);
            }
            if(maxdis<1e-8) break;
        }
        Arrays.sort(cnt);
        for(int i=0;i<k;i++) {
            System.out.print(cnt[i]+" ");
        }
    }
}