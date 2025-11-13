import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static List<Edge>[] edges;
    
    static class Edge {
        int v, w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        bfs();
    }    

    static void bfs(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        pq.add(new Edge(S, 0));
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            if(visited[now.v])
                continue;
            visited[now.v] = true;

            for(Edge next : edges[now.v]){
                if(dist[next.v] > dist[now.v] + next.w){
                    dist[next.v] = dist[now.v] + next.w;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        System.out.println(dist[E]);

    }
}
