package P3842_YOLO_Kmeans;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main{
    public static class frame {
        double w=0;
        double h=0;
        int group=0;
        public frame(double w,double h) {
            this.w = w;
            this.h = h;
        }
    }

    public static class center {
        double w=0;
        double h=0;
        int group=0;
    }

    public static double distance(frame a,center b) {
        double intersection=Math.min(a.w,b.w)*Math.min(a.h,b.h);
        double union=a.w*a.h+b.w*b.h-intersection;
        double iou=intersection/(union+1e-16);
        double d=1-iou;
        return d;
    }

    public static void findGroup(frame[] f,center[] c) {
        for(int i=0;i<f.length;i++) {
            double dis=distance(f[i],c[0]);
            f[i].group = c[0].group;
            for(int j=1;j<c.length;j++) {
                double ndis=distance(f[i],c[j]);
                if(ndis<dis) {
                    dis=ndis;
                    f[i].group=c[j].group;
                }
            }
        }
    }

    public static void newCenter(frame[] f,center[] c) {
        for(int i=0;i<c.length;i++) {
            double nw=0;
            double nh=0;
            int cnt=0;
            for(int j=0;j<f.length;j++) {
                if(f[j].group!=c[i].group) continue;
                nw += f[j].w;
                nh += f[j].h;
                cnt++;
            }
            if(cnt>0) {
                c[i].w = Math.floor(nw/cnt);
                c[i].h = Math.floor(nh/cnt);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int t=sc.nextInt();

        frame[] f=new frame[n];
        for(int i=0;i<n;i++) {
            double w=sc.nextDouble();
            double h=sc.nextDouble();
            f[i]=new frame(w,h);
        }

        center[] c=new center[k];
        for(int i=0;i<k;i++) {
            c[i] = new center();
            c[i].w=f[i].w;
            c[i].h=f[i].h;
            c[i].group=i;
        }

        for(int z=0;z<t;z++) {
            findGroup(f,c);
            center[] oldc=new center[k];
            oldc=c;
            newCenter(f,c);
            for(int i=0;i<k;i++) {
                frame a = new frame(0,0);
                a.w=oldc[i].w;
                a.h=oldc[i].h;
                if(distance(a,c[i])<1e-4) break;
            }
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i][0] = (int)Math.floor(c[i].w);
            ans[i][1] = (int)Math.floor(c[i].h);
        }

        Integer[] idx = new Integer[k];
        for (int i = 0; i < k; i++) idx[i] = i;
        Arrays.sort(idx, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                long areaB = 1L * ans[b][0] * ans[b][1];
                long areaA = 1L * ans[a][0] * ans[a][1];
                return Long.compare(areaB, areaA);
            }
        });

        for (int i = 0; i < k; i++) {
            System.out.println(ans[idx[i]][0] + " " + ans[idx[i]][1]);
        }
    }
}