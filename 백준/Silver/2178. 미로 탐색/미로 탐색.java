
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char map[][];
    static boolean visited[][];
    static int[][] distance;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        distance = new int[N][M];

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = temp.charAt(j);
            }
        }
        bfs();
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;
        distance[0][0] = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                Node next = new Node(now.y+dy[i], now.x+dx[i]);
                if(next.y < N && next.y >= 0 &&
                    next.x < M && next.x >= 0 &&
                    map[next.y][next.x] == '1' &&
                    !visited[next.y][next.x]){
                        q.add(next);
                        visited[next.y][next.x] = true;
                        distance[next.y][next.x] = distance[now.y][now.x] + 1;
                    }
            }
        }
        System.out.println(distance[N-1][M-1]);
    }

    static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
