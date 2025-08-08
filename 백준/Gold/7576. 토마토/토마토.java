
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static Queue<Node> q = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) q.add(new Node(i, j));
            }
        }

        bfs();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, map[i][j]);
            }
        }
        if(max == Integer.MIN_VALUE) max = 0;
        System.out.println(max-1);

    }

    static void bfs(){

        while(!q.isEmpty()) {
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                Node next = new Node(now.y+dy[i], now.x+dx[i]);

                if(next.y < N && next.y >= 0 &&
                    next.x < M && next.x >= 0 &&
                    map[next.y][next.x] == 0){
                        q.add(next);
                        map[next.y][next.x] = map[now.y][now.x] + 1;
                    }
            }
        }
    }

    static class Node {
        int y, x;

        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
