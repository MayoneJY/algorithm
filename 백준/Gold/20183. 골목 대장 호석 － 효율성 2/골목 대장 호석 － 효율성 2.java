
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static long max;
    static Map<Integer, List<Edge>> edges = new HashMap<>();
    static class Edge{
        int v;
        long w, m = 0;
        Edge(int v, long w){
            this.v = v;
            this.w = w;
        }
        Edge(int v, long w, long m){
            this.v = v;
            this.w = w;
            this.m = m;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        max = Long.parseLong(st.nextToken());
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());
            edges.putIfAbsent(s, new ArrayList<>());
            edges.putIfAbsent(e, new ArrayList<>());

            edges.get(s).add(new Edge(e, v));
            edges.get(e).add(new Edge(s, v));
        }

        bfs();
    }

    static void bfs(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.m != o2.m)
                return Long.compare(o1.m, o2.m);
            return Long.compare(o1.w, o2.w);
        });

        long[] visited = new long[N+1];
        Arrays.fill(visited, Long.MAX_VALUE);
        
        visited[S] = 0;
        pq.add(new Edge(S, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if(now.v == E){
                System.out.println(now.m);
                return;
            }

            if(visited[now.v] < now.w) continue;

            for(Edge e: edges.get(now.v)){
                if(visited[now.v] + e.w <= max && visited[e.v] > visited[now.v] + e.w){
                    long mm = Math.max(now.m, e.w);
                    visited[e.v] = visited[now.v] + e.w;
                    pq.add(new Edge(e.v, visited[e.v], mm));
                }
            }
        }
        System.out.println(-1);
    }
}
