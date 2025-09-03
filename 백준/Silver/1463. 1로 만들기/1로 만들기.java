import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[N + 1];
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            int best = dp[i - 1];
            if (i % 2 == 0) best = Math.min(best, dp[i / 2]);
            if (i % 3 == 0) best = Math.min(best, dp[i / 3]);
            dp[i] = best + 1;
        }
        System.out.println(dp[N]);
    }
}