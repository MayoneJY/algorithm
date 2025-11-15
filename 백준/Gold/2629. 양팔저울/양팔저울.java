import java.io.*;
import java.util.*;

public class Main {
    static int N, B;
    static int[] NS;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        NS = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0; i < N; i++){
            NS[i] = Integer.parseInt(st.nextToken());
            sum += NS[i];
        }

        boolean[][] dp = new boolean[N+1][sum+1];

        dp[0][0] = true;
        
        for(int i = 0; i < N; i++){
            int w = NS[i];
            for(int j = 0; j < sum; j++){
                if(!dp[i][j]) continue;

                dp[i+1][j] = true;

                if(j+w <= sum){
                    dp[i+1][j+w] = true;
                }

                int nd = Math.abs(j-w);
                if(nd <= sum){
                    dp[i+1][nd] = true;
                }
            }
        }


        B = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < B; i++){
            int x = Integer.parseInt(st.nextToken());
            if(x > sum){
                System.out.print("N ");
                continue;
            }
            if(dp[N][x])
                System.out.print("Y ");
            else
                System.out.print("N ");
        }
    }
}
