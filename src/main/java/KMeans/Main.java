package KMeans;

import java.util.Scanner;

/*某电商平台希望根据用户的购物行为对用户进行分群，以便制定差异化的运营策略。
每位用户有三个特征指标： purchase_amount（月均消费金额） visit_frequency（月均访问次数） return_rate（退货率，已归一化） 你需要实现 KMeans 聚类算法，将用户划分为若干个群体。
KMeans 算法的流程如下：给定 K 个初始聚类中心，计算每个数据点到各聚类中心的欧氏距离，将数据点分配到距离最近的聚类中心所在的组。然后对每个组重新计算中心点（即该组内所有数据点各维度的算术平均值），完成一轮迭代。 重复上述过程指定的迭代次数后，输出最终的 K 个聚类中心，每个维度的值保留两位小数（四舍五入）。 

输入描述：
第一行一个正整数 K，表示聚类中心的个数。
接下来 K 行，每行三个浮点数，表示初始聚类中心的三个特征值。
下一行一个正整数，表示迭代次数。
下一行一个正整数 m，表示数据点的个数。
接下来 m 行，每行三个浮点数，表示一个数据点的三个特征值。
输出描述：
输出 K 行，每行三个数值，表示迭代结束后各聚类中心的三个特征值，保留两位小数，四舍五入。*/

public class Main {
    public static class point {
        double x;
        double y;
        double z;
        int group=0;
        public point(double x,double y,double z) {
            this.x=x;
            this.y=y;
            this.z=z;
        }
    }

    public static class center {
        double x;
        double y;
        double z;
        int group;
        int count=0;
        public center(double x, double y, double z, int group) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.group = group;
        }
    }

    public static double distance(point a,center b) {
        double distance=Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)+(a.z-b.z)*(a.z-b.z));
        return distance;
    }

    public static void findG(point a,center[] centers) {
        double dis=distance(a,centers[0]);
        a.group=centers[0].group;
        for(int i=1;i<centers.length;i++)
        {
            double tdis;
            tdis=distance(a,centers[i]);
            if(tdis<dis) {
                dis=tdis;
                a.group=centers[i].group;
            }
        }
    }

    public static void newcenter(point[] points,center[] centers) {

        for(int i=0;i<centers.length;i++)
        {
            double x=0;
            double y=0;
            double z=0;
            for(int j=0;j<points.length;j++) {
                if(centers[i].group!=points[j].group) continue;
                x += points[j].x;
                y += points[j].y;
                z += points[j].z;
                centers[i].count++;
            }
            if(centers[i].count>0) {
                centers[i].x = x/centers[i].count;
                centers[i].y = y/centers[i].count;
                centers[i].z = z/centers[i].count;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();

        center[] centers=new center[k];

        for(int i=0;i<k;i++) {
            double x=sc.nextDouble();
            double y=sc.nextDouble();
            double z=sc.nextDouble();
            double group=i;
            centers[i] = new center(x, y, z, i);
        }

        int r=sc.nextInt();
        int m=sc.nextInt();

        point[] points=new point[m];

        for(int i=0;i<m;i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            points[i] = new point(x, y, z);
        }

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<m;j++)
            {
                findG(points[j],centers);
            }

            newcenter(points,centers);

            for(int j=0;j<k;j++) {
                centers[j].count=0;
            }
        }

        for(int i=0;i<k;i++) {
            System.out.printf("%.2f %.2f %.2f\n", centers[i].x, centers[i].y, centers[i].z);
        }
    }
}
