package P4343_UnionFind;

import java.util.*;

public class Main {
    static class UnionFind {
        Map<String,String> parents=new HashMap<>();

        String find(String x) {
            if(!parents.get(x).equals(x)) {
                parents.put(x,find(parents.get(x)));
            }
            return parents.get(x);
        }
        void union(String x,String y) {
            if(!find(x).equals(find(y))) {
                parents.put(find(x),find(y));
            }
        }
    };

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        UnionFind uf=new UnionFind();
        sc.nextLine();
        List<List<String>> system=new ArrayList<>();
        for(int i=0;i<n;i++) {
            String line=sc.nextLine().trim();
            String[] parts=line.split("\\s");
            List<String> eachl=new ArrayList<>();
            for(int j=0;j<parts.length;j++) {
                eachl.add(parts[j]);
            }
            system.add(eachl);
            for(String s:eachl) uf.parents.putIfAbsent(s,s);
        }

        for(List<String> list : system) {
            if(list.isEmpty()) continue;
            for(int i=0;i<list.size();i++) {
                uf.union(list.get(0),list.get(i));
            }
        }

        Map<String,List<String>> group=new HashMap<>();
        for(String key : uf.parents.keySet()) {
            String root=uf.find(key);
            if(!group.containsKey(root)) {
                group.put(root,new ArrayList<>());
            }
            group.get(root).add(key);
        }

        List<List<String>> res=new ArrayList<>();
        for(List<String> g : group.values()) {
            Collections.sort(g);
            res.add(g);
        }

        res.sort((a,b)-> {
            int len=Math.min(a.size(),b.size());
            for(int i=0;i<len;i++) {
                int cmp=a.get(i).compareTo(b.get(i));
                if(cmp!=0) return cmp;
            }
            return Integer.compare(a.size(),b.size());
        });

        for(List<String> list : res) {
            System.out.println(String.join(" ",list));
        }
    }
}