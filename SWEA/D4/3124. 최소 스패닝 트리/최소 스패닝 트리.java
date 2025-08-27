import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int V, E;
    static List<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                graph[a].add(new Edge(b, w));
                graph[b].add(new Edge(a, w)); // 무방향
            }

            long ans = prim(1);
            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.print(sb);
    }

    static long prim(int start) {
        visited = new boolean[V + 1];
        PriorityQueue<Edge> q = new PriorityQueue<>((e1, e2) -> Long.compare(e1.w, e2.w));

        q.add(new Edge(start, 0L));
        long total = 0L;
        int picked = 0;

        while (!q.isEmpty() && picked < V) {
            Edge cur = q.poll();
            int u = cur.to;
            if (visited[u]) continue;

            visited[u] = true;
            total += cur.w;
            picked++;

            for (Edge e : graph[u]) {
                if (!visited[e.to]) q.add(new Edge(e.to, e.w));
            }
        }
        return total;
    }

    static class Edge {
        int to;
        long w; // PQ에서는 "해당 정점으로 들어가는 현재 최소 비용"으로 해석
        Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }
}
