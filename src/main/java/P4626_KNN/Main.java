package P4626_KNN;

import java.util.*;

public class Main {
    public static double distance(double x1,double x2,double x3,double y1,double y2,double y3) {
        double dis=0;
        dis=Math.sqrt(Math.pow((x1-y1),2)+Math.pow((x2-y2),2)+Math.pow((x3-y3),2));
        return dis;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();

        double[][] v=new double[n][4];
        for(int i=0;i<n;i++) {
            v[i][0]=sc.nextDouble();
            v[i][1]=sc.nextDouble();
            v[i][2]=sc.nextDouble();
            v[i][3]=sc.nextDouble();
        }

        double x=sc.nextDouble();
        double y=sc.nextDouble();
        double z=sc.nextDouble();

        double[][] dis=new double[n][2];
        for(int i=0;i<n;i++) {
            dis[i][0]=distance(x,y,z,v[i][0],v[i][1],v[i][2]);
            dis[i][1]=v[i][3];
        }

        Arrays.sort(dis,(a,b)->Double.compare(a[0],b[0]));
        int[][] clas=new int[10000][2];
        for(int i=0;i<n;i++) {
            clas[i][1]=i;
        }
        for(int i=0;i<k;i++) {
            int j=(int)dis[i][1];
            clas[j][0]++;
        }
        Arrays.sort(clas,(a,b)->Integer.compare(b[0],a[0]));
        System.out.println(clas[0][1]);
    }
}