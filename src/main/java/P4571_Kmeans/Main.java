package P4571_Kmeans;

import java.util.*;

public class Main {
    public static double distance(double x1,double x2,double x3,double y1,double y2,double y3) {
        double dis=0;
        dis=Math.pow((x1-y1),2)+Math.pow((x2-y2),2)+Math.pow((x3-y3),2);
        return dis;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        double[][] c=new double[k][4];
        for(int i=0;i<k;i++) {
            c[i][0]=i;
            c[i][1]=sc.nextDouble();
            c[i][2]=sc.nextDouble();
            c[i][3]=sc.nextDouble();
        }
        int T=sc.nextInt();
        int m=sc.nextInt();
        double[][] p=new double[m][4];
        for(int i=0;i<m;i++) {
            p[i][0]=0;
            p[i][1]=sc.nextDouble();
            p[i][2]=sc.nextDouble();
            p[i][3]=sc.nextDouble();
        }
        for(int t=0;t<T;t++) {
            //findG
            for(int i=0;i<m;i++) {
                double mindis=distance(p[i][1],p[i][2],p[i][3],c[0][1],c[0][2],c[0][3]);
                p[i][0] = c[0][0];
                for(int j=1;j<k;j++) {
                    double dis=distance(p[i][1],p[i][2],p[i][3],c[j][1],c[j][2],c[j][3]);
                    if(mindis>dis) {
                        mindis=dis;
                        p[i][0]=c[j][0];
                    }
                }
            }
            //newCenter
            for(int i=0;i<k;i++) {
                double sum1=0;
                double sum2=0;
                double sum3=0;
                int cnt=0;
                for(int j=0;j<m;j++) {
                    if(p[j][0]!=c[i][0]) continue;
                    sum1 += p[j][1];
                    sum2 += p[j][2];
                    sum3 += p[j][3];
                    cnt++;
                }
                if(cnt>0) {
                    c[i][1]=sum1/cnt;
                    c[i][2]=sum2/cnt;
                    c[i][3]=sum3/cnt;
                }
            }
        }
        for(int i=0;i<k;i++) {
            System.out.printf("%.2f %.2f %.2f\n",c[i][1],c[i][2],c[i][3]);
        }
    }
}