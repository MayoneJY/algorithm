import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for(int i = 1; i < N; i++){
            parent[i] = i;
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0){
                union(b, c);
            }
            else{
                if(find(b) == find(c))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }

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
