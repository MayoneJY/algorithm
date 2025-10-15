import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int N, K;

    static int[] a, b;
    
    public static void main(String[] args) throws IOException {
        N = sc.nextInt();
        K = sc.nextInt();

        a = new int[N];
        b = new int[N - 1];

        for (int i = 0; i < N; ++i) a[i] = sc.nextInt();
        Arrays.sort(a);

        for (int i = 0; i < N - 1; ++i) b[i] = a[i + 1] - a[i];
        Arrays.sort(b);

        int ans = a[N - 1] - a[0];

        for (int i = 0; i < K - 1; ++i) {
            if (N - 2 - i < 0) break;
            ans -= b[N - 2 - i];
        }

        System.out.println(ans);
    }
}