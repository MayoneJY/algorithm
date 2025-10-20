
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E, max;
    static Map<Integer, List<Edge>> edges = new HashMap<>();
    static class Edge{
        int v, w, m = 0;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
        Edge(int v, int w, int m){
            this.v = v;
            this.w = w;
            this.m = m;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        try{
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            max = Integer.parseInt(st.nextToken());
        }
        catch (NumberFormatException e){
            return;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.putIfAbsent(s, new ArrayList<>());
            edges.putIfAbsent(e, new ArrayList<>());

            edges.get(s).add(new Edge(e, v));
            edges.get(e).add(new Edge(s, v));
        }

        bfs();
    }

    static void bfs(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.w-o2.w;
        });

        int[] visited = new int[N+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        visited[S] = 0;
        pq.add(new Edge(S, 0));
        int result = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(now.v == E)
                result = Math.min(result, now.m);
            for(Edge e: edges.get(now.v)){
                if(visited[now.v] + e.w <= max && visited[e.v] > visited[now.v] + e.w){
                    int mm = Math.max(now.m, e.w);
                    visited[e.v] = visited[now.v] + e.w;
                    pq.add(new Edge(e.v, e.w, mm));
                }
            }
        }
        if(result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);
    }
}
