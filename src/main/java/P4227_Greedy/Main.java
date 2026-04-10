package P4227_Greedy;

import java.util.*;

public class Main {
    public static double[][] RMSNorm(int n,int d,double[][] vector) {
        double[][] norm=new double[n][d];
        for(int i=0;i<n;i++) {
            double sum=0;
            for(int j=0;j<d;j++) {
                sum += vector[i][j]*vector[i][j];
            }
            sum=Math.sqrt(sum/d);
            for(int j=0;j<d;j++) {
                norm[i][j]=vector[i][j]/sum;
            }
        }
        return norm;
    }

    public static double[][] AS(int n,int d,double[][] norm) {
        double[][] Ascore=new double[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                double muit=0;
                for(int k=0;k<d;k++) {
                    muit += norm[i][k]*norm[j][k];
                }
                double Aij=muit/Math.sqrt(d);
                Ascore[i][j]=Aij*Aij;
            }
        }
        return Ascore;
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int d=sc.nextInt();
        double[][] vector=new double[n][d];
        for(int i=0;i<n;i++) {
            for(int j=0;j<d;j++) {
                vector[i][j]=sc.nextDouble();
            }
        }

        int[] capacities=new int[n];
        for(int i=0;i<n;i++) {
            capacities[i]=sc.nextInt();
        }

        double[][] norm=new double[n][d];
        norm=RMSNorm(n,d,vector);

        double[][] Ascore=new double[n][n];
        Ascore=AS(n,d,norm);

        double S=0;
        for(int i=1;i<n;i++) {
            List<Double> scores=new ArrayList<>();
            for(int j=0;j<i;j++) {
                scores.add(Ascore[j][i]);
            }

            Collections.sort(scores,Collections.reverseOrder());
            for(int j=0;j<Math.min(capacities[i],scores.size());j++) {
                S += scores.get(j);
            }
        }
        int ans=(int)Math.round(100*S);
        System.out.println(ans);
    }
}