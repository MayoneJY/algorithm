
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] visited = new boolean[100001];
    static int[] count = new int[100001];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(min);
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(N);

        while (!q.isEmpty()) {
            int now = q.poll();

            if(now == M){
                min = Math.min(min, count[now]);
                return;
            }

            int next = now+1;
            if(next >= 0 && next <= 100000 && !visited[next]){
                visited[next] = true;
                count[next] = count[now] + 1;
                q.add(next);
            }
            next = now-1;
            if(next >= 0 && next <= 100000 && !visited[next]){
                visited[next] = true;
                count[next] = count[now] + 1;
                q.add(next);
            }
            next = now*2;
            if(next >= 0 && next <= 100000 && !visited[next]){
                visited[next] = true;
                count[next] = count[now] + 1;
                q.add(next);
            }
        }
    }
}
