package P3874_UnionFind;

import java.util.*;

public class Main {
    public static double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static int find(int[] p, int x) {
        if (p[x] != x) p[x] = find(p, p[x]);
        return p[x];
    }

    public static void union(int[] p, int x, int y) {
        int fx = find(p, x);
        int fy = find(p, y);
        if (fx != fy) p[fy] = fx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double eps = sc.nextDouble();
        int minPts = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine(); // 吃掉换行

        double[][] points = new double[n][];
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split("\\s+");
            double[] p = new double[s.length];
            for (int j = 0; j < s.length; j++) {
                p[j] = Double.parseDouble(s[j]);
            }
            points[i] = p;
        }

        // 邻域
        List<List<Integer>> neigh = new ArrayList<>();
        for (int i = 0; i < n; i++) neigh.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distance(points[i], points[j]) <= eps) {
                    neigh.get(i).add(j);
                }
            }
        }

        // 核心点
        boolean[] core = new boolean[n];
        for (int i = 0; i < n; i++) {
            core[i] = neigh.get(i).size() >= minPts;
        }

        // 并查集
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int i = 0; i < n; i++) {
            if (core[i]) {
                for (int j : neigh.get(i)) {
                    union(parent, i, j);
                }
            }
        }

        // 统计
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) cnt[find(parent, i)]++;

        int cluster = 0, noise = 0;
        for (int i = 0; i < n; i++) {
            if (find(parent, i) != i) continue;

            boolean hasCore = false;
            for (int j = 0; j < n; j++) {
                if (find(parent, j) == i && core[j]) {
                    hasCore = true;
                    break;
                }
            }
            if (hasCore) cluster++;
            else noise++;
        }

        System.out.println(cluster + " " + noise);
    }
}