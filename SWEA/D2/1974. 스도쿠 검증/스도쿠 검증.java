import java.util.*;
import java.io.*;

public class Solution {
    static final int N = 9;
    static int T;
    static int[][] map = new int[N][N];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        continueCheck:
        for(int t = 1; t <= T; t++){
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[] check = new boolean[N];
            boolean[] check2 = new boolean[N];
            
            for(int i = 0; i < N; i++){
                check = new boolean[N];
                check2 = new boolean[N];
                for(int j = 0; j < N; j++){
                    if(check[map[i][j]-1] || check2[map[j][i]-1]){
                        System.out.println("#" + t + " 0");
                        continue continueCheck;
                    }
                    check[map[i][j]-1] = true;
                    check2[map[j][i]-1] = true;
                }
            }

            for(int i = 0; i < N; i++){
                check = new boolean[N];
                for(int j = 0; j < N; j++){
                    if(check[map[((j/3) + ((i/3)*3))][(((i*3)%9) + j%3)]-1]){
                        System.out.println("#" + t + " 0");
                        continue continueCheck;
                    }
                    check[map[((j/3) + ((i/3)*3))][(((i*3)%9) + j%3)]-1] = true;
                    // System.out.print("" + (((i*3)%9) + j%3)+ "," + ((j/3) + ((i/3)*3))+" ");
                }
            }
            System.out.println("#" + t + " 1");
        }

        // 1,1 1,2 1,3 | 2,1 2,2 2,3 | 3,1 3,2 3,3
        // 0,0 0,1 0,2 | 1,0 1,1 1,2 | 2,0 2,1 2,2
        
        
    }
}
