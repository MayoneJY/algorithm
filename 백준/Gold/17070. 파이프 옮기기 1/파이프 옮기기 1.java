
import java.io.*;
import java.util.*;

class Main {
    static int[][] pipe = new int[][] {{0, 2}, {1, 2}, {0, 1, 2}};
    static int[][] line = new int[][]{{1, 0}, {0, 1}, {1, 1}};
    static int[][] map;
    static int N;
    static int count = 0;
    // static int[] where = new int[] {1, 0};
    
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try{
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            move(0, 1, 0);
            bw.write(Integer.toString(count));
            bw.flush();
            bw.close();

        }
        catch(Exception e){

        }
    }

    public static void move(int startType, int x, int y){
        switch (startType) {
            case 0:
                if(map[y][x] == 1) return;
                break;
            case 1:
                if(map[y][x] == 1) return;
                break;
            case 2:
                if(map[y-1][x] == 1) return;
                if(map[y][x-1] == 1) return;
                if(map[y][x] == 1) return;
                break;
            default:
                break;
        }
        if(x == N - 1 && y == N - 1){
            count++;
            return;
        }
        for(int j = 0; j < pipe[startType].length; j++){
            int dx = line[pipe[startType][j]][0];
            int dy = line[pipe[startType][j]][1];
            if(x + dx < N && y + dy < N){
                move(pipe[startType][j], x + dx, y + dy);
            }
        }
    }
}
