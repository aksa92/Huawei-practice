package P4547_2pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        int[] last=new int[256];
        int l=0;
        int max=0;
        int cnt=0;
        for(int r=0;r<s.length();r++) {
            int idx=(int)s.charAt(r);
            if(last[idx]>=l) {
                l=last[idx]+1;
            }
            last[idx]=r;
            cnt=r-l+1;
            if(max<cnt) max=cnt;
        }
        System.out.println(max);
    }
}