import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    static class Edge {
        int s, v, w;
        Edge(int s, int v, int w){
            this.s = s;
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }

        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }
        
        int result = 0;
        while (!pq.isEmpty()) {
            Edge n = pq.poll();
            int a = find(n.s);
            int b = find(n.v);

            if(a != b){
                union(a, b);
                result += n.w;
            }
        }

        System.out.println(result);
    }

    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }
}
