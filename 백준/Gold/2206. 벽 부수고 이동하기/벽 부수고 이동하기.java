
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][][] visite;
    static int[] dx = {1, 0, -1 ,0};
    static int[] dy = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visite = new int[N][M][2];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        if(min == Integer.MAX_VALUE) min = -1;
        else min += 1;
        // for(int i = 0; i < N; i++){
        //     for(int j = 0; j < M; j++){
        //         System.out.print(visite[i][j]);
        //     }
        //     System.out.println();
        // }
        System.out.println(min);
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(0, 0, 1));
        visite[0][0][0] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if(now.y == N-1 && now.x == M-1){
                min = Math.min(min, visite[now.y][now.x][now.breakable]);
                // if(min == 0) min = -1;
                return;
            }

            for(int i = 0; i < 4; i++){
                Node next = new Node(now.y+dy[i], now.x+dx[i], now.breakable);
                if(inRange(next) && !isVisited(next)){
                    if(isWall(next)){
                        if(now.breakable == 1){
                            next.breakable = 0;
                            q.add(next);
                            visite[next.y][next.x][next.breakable] = visite[now.y][now.x][now.breakable] + 1;
                        }
                    }
                    else{
                        q.add(next);
                        visite[next.y][next.x][next.breakable] = visite[now.y][now.x][now.breakable] + 1;
                    }
                }
            }
        }
    }

    static boolean isVisited(Node n){
        if(visite[n.y][n.x][n.breakable] == 0) return false;
        return true;
    }

    static boolean inRange(Node n){
        if(n.y >= 0 && n.x >= 0 && n.y < N && n.x < M) return true;
        return false;
    }

    static boolean isWall(Node n){
        if(map[n.y][n.x] == 1) return true;
        return false;
    }

    static class Node{
        int y, x;
        int breakable;
        Node(int y, int x, int b){
            this.y = y;
            this.x = x;
            this.breakable = b;
        }
    }
}
