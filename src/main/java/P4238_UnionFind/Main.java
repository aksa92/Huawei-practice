package P4238_UnionFind;

import java.util.*;

public class Main {
    public static int Find(int[] p,int x) {
        if(p[x]==x) {
            return x;
        }
        else return p[x]=Find(p,p[x]);
    }
    public static void Union(int[] p,int x,int y) {
        int rootx=Find(p,x);
        int rooty=Find(p,y);
        if(rootx!=rooty) {
            p[rootx]=rooty;
        }

    }

    public static double cos(double[] e1,double[] e2) {
        double vectorMuit=0;
        double l1=0;
        double l2=0;

        for(int i=0;i<e1.length;i++) {
            vectorMuit += e1[i]*e2[i];
            l1 += Math.pow(e1[i],2);
            l2 += Math.pow(e2[i],2);
        }
        l1=Math.sqrt(l1);
        l2=Math.sqrt(l2);
        if(l1==0||l2==0) {
            return 0;
        }
        return vectorMuit/(l1*l2);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<double[]> embeddings=new ArrayList<>();
        int cnt=0;
        while(sc.hasNextLine()) {
            String line=sc.nextLine();
            if(line.isEmpty()) {
                continue;
            }
            String[] parts=line.split("\\s");
            double[] embedding=new double[parts.length-1];
            for(int i=1;i<parts.length;i++) {
                embedding[i-1]=Double.parseDouble(parts[i]);
            }
            embeddings.add(embedding);
            cnt++;
        }
        if(cnt==0) {System.out.print("0");return;}
        for(int i=1;i<cnt;i++) {
            int l1=embeddings.get(0).length;
            int l2=embeddings.get(i).length;
            if(l1!=l2) {
                System.out.print("0");
                return;
            }
        }

        int[] p=new int[cnt];
        for(int i=0;i<cnt;i++) {
            p[i]=i;
        }
        for(int i=0;i<cnt;i++) {
            for(int j=i+1;j<cnt;j++) {
                double cossim=cos(embeddings.get(i),embeddings.get(j));
                if(cossim>=0.95) {
                    Union(p,i,j);
                }
            }
        }
        //
        int[][] ans=new int[cnt][2];
        for(int i=0;i<cnt;i++) {
            ans[i][1]=i;
            int j=Find(p,i);
            ans[j][0]++;
        }

        Arrays.sort(ans,(a,b)->Integer.compare(b[0],a[0]));
        System.out.print(ans[0][0]);
    }
}