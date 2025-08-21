import java.util.*;
import java.io.*;

public class Solution {
    static final int T = 10;
    static final int N = 100;

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= T; t++) {
            int tc = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visited = new boolean[N][N];

            int sy = 0, sx = 0;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        sy = i; sx = j;
                    }
                }
            }

            int ans = bfs(new Node(sy, sx));
            System.out.printf("#%d %d\n", tc, ans);
        }
    }

    static int bfs(Node start) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int d = 0; d < 4; d++) {
                Node next = new Node(now.y + dy[d], now.x + dx[d]);

                if (next.y < 0 || next.x < 0 || next.y >= N || next.x >= N) continue;
                if (visited[next.y][next.x] || map[next.y][next.x] == 1) continue;

                if (map[next.y][next.x] == 3) return 1;

                visited[next.y][next.x] = true;
                q.add(next);
            }
        }
        return 0;
    }

    static class Node {
        int y, x;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
