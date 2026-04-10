package P3479_KNN;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        int m=sc.nextInt();
        int n=sc.nextInt();
        int s=sc.nextInt();

        double[] target=new double[n];
        for(int i=0;i<n;i++) {
            target[i]=sc.nextDouble();
        }
        double[][] samples=new double[m][n+1];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                samples[i][j]=sc.nextDouble();
            }
            samples[i][n]=sc.nextDouble();
        }
        double[][] distance=new double[m][2];
        for(int i=0;i<m;i++) {
            double sum=0;
            for(int j=0;j<n;j++) {
                sum += Math.pow(target[j]-samples[i][j],2);
            }
            distance[i][0]=sum;
            distance[i][1]=samples[i][n];
        }
        Arrays.sort(distance,(a,b)->Double.compare(a[0],b[0]));
        int[] cnt=new int[s];
        for(int i=0;i<k;i++) {
            int dis=(int)distance[i][1];
            cnt[dis]++;
        }
        int max=0;
        int idx=0;
        for(int i=0;i<s;i++) {
            if(cnt[i]>max) {
                max=cnt[i];
                idx=i;
            }
        }
        System.out.println(idx+" "+max);
    }
}