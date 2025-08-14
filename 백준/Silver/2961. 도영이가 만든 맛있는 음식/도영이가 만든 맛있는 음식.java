import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] S = new int[N]; // 신맛
        int[] B = new int[N]; // 쓴맛

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        long ans = Integer.MAX_VALUE;

        for (int mask = 1; mask < (1 << N); mask++) {
            long product = 1;
            long sum = 0;

            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    product *= S[i];
                    sum += B[i];
                }
            }

            ans = Math.min(ans, Math.abs(product-sum));

        }

        System.out.println(ans);
    }
}