import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static long[] X, Y;
    static double E;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());

            X = new long[N];
            Y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) 
                X[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) 
                Y[i] = Long.parseLong(st.nextToken());
                
            E = Double.parseDouble(br.readLine().trim());

            long mstDist2Sum = prim();
            long answer = Math.round(E * mstDist2Sum);

            sb.append(String.format("#%d %d\n", t, answer));
        }
        System.out.print(sb);
    }

    // 프림(우선순위큐) — 정점 0에서 시작
    static long prim() {
        visited = new boolean[N];
        PriorityQueue<Node> q = new PriorityQueue<>((n1, n2) -> Long.compare(n1.cost, n2.cost));

        q.add(new Node(0, 0L));
        long total = 0L;
        int picked = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (visited[now.idx]) continue;
            visited[now.idx] = true;
            total += now.cost;
            picked++;
            if (picked == N) break;

            
            for (int next = 0; next < N; next++) {
                if (visited[next]) continue;
                long dx = X[now.idx] - X[next];
                long dy = Y[now.idx] - Y[next];
                long dist2 = dx * dx + dy * dy;
                q.add(new Node(next, dist2));
            }
        }
        return total;
    }

    static class Node {
        int idx;
        long cost;
        Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
