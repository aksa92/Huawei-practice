package P3791_Kmeans;

import java.util.*;

public class Main {
    public static double distance(double x1,double y1,double x2,double y2) {
        double dis=0;
        dis=Math.pow(x1-x2,2)+Math.pow(y1-y2,2);
        return dis;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        double[][] p=new double[n][3];
        double[][] c=new double[k][3];
        for(int i=0;i<n;i++) {
            p[i][0]=0;
            p[i][1]=sc.nextDouble();
            p[i][2]=sc.nextDouble();
        }
        for(int i=0;i<k;i++) {
            c[i][0]=i;
            c[i][1]=p[i][1];
            c[i][2]=p[i][2];
        }
        for(int t=0;t<100;t++) {
            //findGroup
            for(int i=0;i<n;i++) {
                p[i][0]=0;
                double minDis=distance(p[i][1],p[i][2],c[0][1],c[0][2]);
                for(int j=1;j<k;j++) {
                    double dis=distance(p[i][1],p[i][2],c[j][1],c[j][2]);
                    if(minDis>dis) {
                        p[i][0]=j;
                        minDis=dis;
                    }
                }
            }
            //newCenter
            int diffcnt=0;
            double[][] oldc=new double[k][3];
            for(int i=0;i<k;i++) {
                oldc[i][0]=c[i][0];
                oldc[i][1]=c[i][1];
                oldc[i][2]=c[i][2];
            }

            for(int i=0;i<k;i++) {
                double sumx=0;
                double sumy=0;
                int cnt=0;
                for(int j=0;j<n;j++) {
                    if(p[j][0]==i) {
                        sumx += p[j][1];
                        sumy += p[j][2];
                        cnt++;
                    }
                }
                c[i][1]=sumx/cnt;
                c[i][2]=sumy/cnt;
                double diff=distance(oldc[i][1],oldc[i][2],c[i][1],c[i][2]);
                if(diff<=1e-6) diffcnt++;
            }
            //judge
            if(diffcnt==k) break;
        }
        for(int i=0;i<k;i++) {
            System.out.println(c[i][1]+" "+c[i][2]);
        }
    }
}