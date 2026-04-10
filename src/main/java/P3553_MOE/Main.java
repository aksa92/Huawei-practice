package P3553_MOE;

import java.util.*;

public class Main {
    public static double distance(double x1, double y1, double x2, double y2) {
        double dis = 0;
        dis = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        return dis;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        double[][] p = new double[n][3];
        double[][] c = new double[k][3];
        for (int i = 0; i < n; i++) {
            p[i][0] = 0;
            p[i][1] = sc.nextDouble();
            p[i][2] = sc.nextDouble();
        }
        for (int i = 0; i < k; i++) {
            c[i][0] = i;
            c[i][1] = p[i][1];
            c[i][2] = p[i][2];
        }
        for (int t = 0; t < 100; t++) {
            //findGroup
            for (int i = 0; i < n; i++) {
                p[i][0] = 0;
                double minDis = distance(p[i][1], p[i][2], c[0][1], c[0][2]);
                for (int j = 1; j < k; j++) {
                    double dis = distance(p[i][1], p[i][2], c[j][1], c[j][2]);
                    if (minDis > dis) {
                        p[i][0] = j;
                        minDis = dis;
                    }
                }
            }
            //newCenter
            int diffcnt = 0;
            double[][] oldc = new double[k][3];
            for (int i = 0; i < k; i++) {
                oldc[i][0] = c[i][0];
                oldc[i][1] = c[i][1];
                oldc[i][2] = c[i][2];
            }

            for (int i = 0; i < k; i++) {
                double sumx = 0;
                double sumy = 0;
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (p[j][0] == i) {
                        sumx += p[j][1];
                        sumy += p[j][2];
                        cnt++;
                    }
                }
                c[i][1] = sumx / cnt;
                c[i][2] = sumy / cnt;
                double diff = distance(oldc[i][1], oldc[i][2], c[i][1], c[i][2]);
                if (diff <= 1e-6) diffcnt++;
            }
            //judge
            if (diffcnt == k) break;
        }
        double[][] Spa = new double[n][2];
        double[][] Spb = new double[n][2];
        //Spa
        for (int i = 0; i < n; i++) {
            Spa[i][0] = p[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (p[i][0] == p[j][0]) {
                    cnt++;
                    Spa[i][1] += distance(p[i][1], p[i][2], p[j][1], p[j][2]);
                }
            }
            if(cnt-1==0) {
                Spa[i][1]=0;
                continue;
            }
            Spa[i][1] /= (cnt-1);
        }
        //Spb
        for (int i = 0; i < n; i++) {
            Spb[i][0] = p[i][0];
            double mindis = Double.MAX_VALUE;
            int close = 0;
            for (int j = 0; j < k; j++) {
                if (p[i][0] == j) continue;
                double dis = distance(p[i][1], p[i][2], c[j][1], c[j][2]);
                if (mindis > dis) {
                    mindis = dis;
                    close = j;
                }
            }
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (p[j][0] == close) {
                    Spb[i][1] += distance(p[i][1], p[i][2], p[j][1], p[j][2]);
                    cnt++;
                }
            }
            Spb[i][1] /= cnt;
        }
        //Si
        double[][] Si = new double[k][2];
        for (int i = 0; i < k; i++) {
            double sum = 0;
            Si[i][0] = i;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (p[j][0] != i) continue;
                double si = (Spb[j][1] - Spa[j][1]) / Math.max(Spb[j][1], Spa[j][1]);
                sum += si;
                cnt++;
            }
            if(cnt<=1) {
                Si[i][1]=1;
            }
            Si[i][1] = sum / cnt;
        }
        Arrays.sort(Si, (a, b) -> Double.compare(a[1], b[1]));
        for (int i = 0; i < k; i++) {
                if(c[i][0]==Si[0][0]) {
                    System.out.printf("%.2f,%.2f",c[i][1],c[i][2]);
                    break;
                }
        }
    }
}