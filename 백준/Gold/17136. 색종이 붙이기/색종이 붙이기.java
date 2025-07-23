import java.util.*;
import java.io.*;

class Main{
    static int N = 10;
    static int[] PAPER = new int[]{5,5,5,5,5};
    static int[][] map = new int[N][N];
    static int min = Integer.MAX_VALUE;
    static public void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // for(int i = 0; i < N; i++){
        //     for(int j = 0; j < N; j++){
        //         for(int p = 5; j > 0; p--){
        //             int[] paper = PAPER.clone();
        //             int[][] copyMap = new int[10][10];
        //             for(int m = 0; m < 10; m++) {
        //                 copyMap[m] = map[m].clone();
        //             }
        //             dfs(copyMap, paper, j, i, p);
        //         }
        //     }
        // }
        int[] paper = PAPER.clone();
        int[][] copyMap = new int[N][N];
        for(int m = 0; m < N; m++) {
            copyMap[m] = map[m].clone();
        }
        for(int i = 5; i > 0; i--)
            dfs(copyMap, paper, 0, 0, i);
        if(min == Integer.MAX_VALUE) min = -1;
        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
    }

    static void dfs(int[][] map, int[] paper, int X, int Y, int P){
        if(paper[P-1] == 0) return;
        
        if(X >= N){
            // for(int i = 5; i > 0; i--)
            dfs(map, paper, 0, Y+1, P);
            return;
        }
        if(Y >= N){
            boolean check = false;
            outBreak:
            for(int pY = 0; pY < N; pY++){
                for(int pX = 0; pX < N; pX++){
                    if(map[pY][pX] == 1){
                        check = true;
                        break outBreak;
                    }
                }
            }
            if (!check) {
                int count = 0;
                for (int i : paper) {
                    count += 5-i;
                }
                min = Math.min(min, count);
            }
            return;
        }
        
        if(map[Y][X] == 0)
            dfs(map, paper, X+1, Y, P);
        if(X + P > N || Y + P > N) return;
        
        int[][] copyMap = new int[N][N];
        int[] copyPaper = paper.clone();
        for(int m = 0; m < N; m++) {
            copyMap[m] = map[m].clone();
        }
        boolean check = false;
        outBreak:
        for(int pY = 0; pY < P; pY++){
            for(int pX = 0; pX < P; pX++){
                if(map[Y+pY][X+pX] == 0){
                    check = true;
                    break outBreak;
                }
            }
        }
        if(check)
            return;
        
            
        copyPaper[P-1]--;
        
        for(int pY = 0; pY < P; pY++){
            for(int pX = 0; pX < P; pX++){
                copyMap[Y+pY][X+pX] = 0;
            }
        }
        // System.out.println("=============");
        // for(int pY = 0; pY < N; pY++){
        //     for(int pX = 0; pX < N; pX++){
        //         System.out.print(copyMap[pY][pX]);
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }
        for(int i = 5; i > 0; i--)
            dfs(copyMap, copyPaper, X+P, Y, i);
    }
}