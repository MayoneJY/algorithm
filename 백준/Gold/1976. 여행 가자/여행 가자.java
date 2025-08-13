import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N];
        for(int n = 0; n < N; n++){
            parent[n] = n;
        }
        for(int a = 0; a < N; a++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int b = 0;
            while (st.hasMoreTokens()) {
                int c = Integer.parseInt(st.nextToken());
                if(c == 1){
                    union(a, b);
                }
                b++;
            }
        }
        int prevTrip = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            if(prevTrip == 0){
                prevTrip = Integer.parseInt(st.nextToken());
                continue;
            }
            int nowTrip = Integer.parseInt(st.nextToken());
            if(find(prevTrip-1) != find(nowTrip-1)){
                System.out.println("NO");
                return;
            }
            prevTrip = nowTrip;
        }
        System.out.println("YES");
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b)
            parent[b] = a;
    }

    static int find(int a){
        if(parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }
}
