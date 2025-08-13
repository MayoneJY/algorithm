import java.util.*;
import java.io.*;

public class Main {
    static int T;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N];
            for(int i = 0; i < N; i++){
                parent[i] = i;
            }
            int K = Integer.parseInt(br.readLine());
            for(int k = 0; k < K; k++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(parent, a, b);
            }
            int M = Integer.parseInt(br.readLine());
            System.out.println("Scenario " + t + ":");
            for(int m = 0; m < M; m++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(find(parent, a)== find(parent, b))
                    System.out.println(1);
                else
                    System.out.println(0);
            }
            System.out.println();
        }
    }

    static void union(int[] parent, int a, int b){
        a = find(parent, a);
        b = find(parent, b);

        if(a != b){
            parent[b] = a;
        }

    }

    static int find(int[] parent, int a){
        if(parent[a] == a)
            return a;
        else
            return parent[a] = find(parent, parent[a]);
    }
}
