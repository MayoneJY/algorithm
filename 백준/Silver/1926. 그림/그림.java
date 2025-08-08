
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int islandCount = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1 && !visited[i][j])
                    bfs(new Node(j, i));
            }
        }
        
        if(max == Integer.MIN_VALUE) max = 0;

        System.out.println(islandCount);
        System.out.println(max);

    }

    static void bfs(Node node) {
        islandCount++;
        Queue<Node> q = new ArrayDeque<>();
        int count = 0;
        q.add(node);
        visited[node.y][node.x] = true;
        count++;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                Node next = new Node(now.x + dx[i], now.y + dy[i]);

                if(next.x < M && next.x >= 0 &&
                    next.y < N && next.y >= 0 &&
                    map[next.y][next.x] == 1 &&
                    !visited[next.y][next.x]){
                        q.add(next);
                        visited[next.y][next.x] = true;
                        count++;
                    }
            }
        }
        max = Math.max(max, count);
    }

    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
