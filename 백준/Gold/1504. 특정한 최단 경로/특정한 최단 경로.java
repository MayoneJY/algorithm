import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V1, V2;
    static List<Edge>[] edges;
    static int n = 0;
    
    static class Edge {
        int v, w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N+1];
        for(int i = 1; i <= N; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
            n++;
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        int distSV1 = bfs(1, V1);
        int distSV2 = bfs(1, V2);
        int distV1V2 = bfs(V1, V2);
        int distV1N = bfs(V1, N);
        int distV2N = bfs(V2, N);
        int ansF1 = distSV1 + distV1V2 + distV2N;
        int ansF2 = distSV2 + distV1V2 + distV1N;
        if(distSV1 == -1 || distSV2 == -1 || distV1N == -1 || distV1V2 == -1 || distV2N == -1){
            System.out.println(-1);
            return;
        }
        int ans = Math.min(ansF1, ansF2);
        System.out.println(ans);
    }

    static int bfs(int S, int E){
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        boolean[] visited = new boolean[N+1];
        pq.add(new Edge(S, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.v]) continue;
            visited[now.v] = true;

            for(Edge next : edges[now.v]){
                if(dist[next.v] > dist[now.v] + next.w){
                    dist[next.v] = dist[now.v] + next.w;

                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
        if(dist[E] == Integer.MAX_VALUE) return -1;

        return dist[E];
    }
}
