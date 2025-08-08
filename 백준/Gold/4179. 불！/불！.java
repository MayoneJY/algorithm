
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map[][] map;
    static int[][] dist;
    static Queue<Node> J = new ArrayDeque<>();
    static Queue<Node> F = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Map[N][M];
        dist = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(dist[i], -1);
        }
        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = new Map(temp.charAt(j), -1);
                if(map[i][j].item == 'J'){
                    J.add(new Node(i, j, 'J', 0));
                    dist[i][j] = 1;
                    map[i][j].idx = 0;
                }
                if(map[i][j].item == 'F'){
                    F.add(new Node(i, j, 'F', 0));
                    map[i][j].idx = 0;
                }
            }
        }
        bfs(F);
        bfs(J);
        for(int p = 0; p < N; p++){
            for(int q = 0; q < M; q++){
                // System.out.print(dist[p][q]);
                if((p == 0 || p == N-1) ||
                    (q == 0 || q == M-1)){
                    if(dist[p][q] > 0)
                        min = Math.min(min, dist[p][q]);
                }
            }
            // System.out.println();
        }

        if(min == Integer.MAX_VALUE || min == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(min);
    }

    static void bfs(Queue<Node> q){
        while (!q.isEmpty()) {
            Node now = q.poll();
            for(int i = 0; i < 4; i++){
                Node next = new Node(now.y+dy[i], now.x+dx[i], now.item, now.idx+1);
                // System.out.println(map[next.y][next.x]);

                if(next.y < N && next.y >= 0 &&
                    next.x < M && next.x >= 0){
                    if(next.item == 'J'){
                        
                        if(map[next.y][next.x].item == '.' || map[next.y][next.x].item == 'F'){
                            if(map[next.y][next.x].idx > next.idx || map[next.y][next.x].idx == -1){
                                q.add(next);
                                dist[next.y][next.x] = dist[now.y][now.x] + 1;
                                map[next.y][next.x].item = 'J';
                                map[next.y][next.x].idx = next.idx;
                            }
                        }
                    }
                    else{
                        if(map[next.y][next.x].item == '.'){
                            q.add(next);
                            map[next.y][next.x].item = 'F';
                            map[next.y][next.x].idx = next.idx;
                        }
                    }
                    
                }
                else{
                }
            }
        }
    }

    static class Map{
        char item;
        int idx;
        Map(char item, int idx){
            this.item = item;
            this.idx = idx;
        }
    }

    static class Node{
        int y, x;
        char item;
        int idx;
        Node(int y, int x, char item, int idx){
            this.y = y;
            this.x = x;
            this.item = item;
            this.idx = idx;
        }
    }
}
