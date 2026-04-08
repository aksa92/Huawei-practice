package P3553_MOE;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int p=sc.nextInt();
        int k=sc.nextInt();

        if(n%m!=0||p>m) {
            System.out.println("error");
            return;
        }

        double[][] exp=new double[n][2];
        int num=n/m;
        //输入概率值,初始化组别
        for(int i=0;i<n;i++) {
            exp[i][0]=sc.nextDouble();
            exp[i][1]=i/num;
        }
        //更新每组的最大概率和对应专家编号
        double[][] group=new double[m][3];
        for(int i=0;i<m;i++) {
            double max=-1;
            for(int j=0;j<n;j++) {
                if(exp[j][1]!=i) continue;
                if(max<exp[j][0]) {
                    max=exp[j][0];
                    group[i][1]=j;
                }
            }
            group[i][0]=max;
            group[i][2]=i;
        }
        Arrays.sort(group,(a,b)->Double.compare(b[0],a[0]));
        int[] fgid=new int[p];
        for(int i=0;i<p;i++) {
            fgid[i]=(int)group[i][2];
        }
        int c=p*num;
        if(c<k) {System.out.println("error");return;}
        double[][] fexp=new double[c][2];
        int cnt=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<p;j++) {
                if(exp[i][1]!=fgid[j]) continue;
                fexp[cnt][0]=exp[i][0];
                fexp[cnt][1]=i;
                cnt++;
            }
        }
        Arrays.sort(fexp,(a,b)->Double.compare(b[0],a[0]));
        int[] ans=new int[k];
        for(int i=0;i<k;i++) {
            ans[i]=(int)fexp[i][1];
        }
        Arrays.sort(ans);
        for(int i=0;i<k-1;i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println(ans[k-1]);
    }
}