import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N+50];
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            dp[i+1] = Math.max(dp[i], dp[i+1]);
            int finish = i+T[i];
            if(finish <= N+1)
                dp[finish] = Math.max(dp[finish], dp[i] + P[i]);
        }
        Arrays.sort(dp);
        System.out.println(dp[dp.length-1]);
    }
}
