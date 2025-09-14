import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        for(int n = 0; n <= N; n++){
            Arrays.fill(map[n], INF);
            map[n][n] = 0;
        }
        for(int m = 0; m < M; m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b], c);
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    map[i][j] = Math.min(map[i][j], map[k][j] + map[i][k]);
                }
            }
        }
        
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                System.out.printf("%d ", (map[i][j] == INF)?0:map[i][j]);
            }
            System.out.println();
        }
    }
}
