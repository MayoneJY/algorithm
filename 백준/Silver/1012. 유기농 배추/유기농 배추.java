
import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int count;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int X, Y, K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            count = 0;
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            boolean[][] map = new boolean[Y][X];
            K = Integer.parseInt(st.nextToken());
            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }
            for(int i = 0; i < Y; i++){
                for(int j = 0; j < X; j++){
                    if(map[i][j])
                        bfs(map, new Node(i, j));
                }
            }

            System.out.println(count);
        }
    }

    static void bfs(boolean[][] map, Node n){
        Queue<Node> q = new ArrayDeque<>();
        q.add(n);
        map[n.y][n.x] = false;

        count++;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                Node next = new Node(now.y+dy[i], now.x+dx[i]);

                if(next.y >= 0 && next.y < Y &&
                    next.x >= 0 && next.x < X &&
                    map[next.y][next.x]){
                        q.add(next);
                        map[next.y][next.x] = false;
                    }
            }
        }
    }

    static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
