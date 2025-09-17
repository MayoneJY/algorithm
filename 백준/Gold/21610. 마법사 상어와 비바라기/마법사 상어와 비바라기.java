import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static final int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static final int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

    static int N;
    static int M;

    static int a[][];
    static boolean cloud[][];

    static void step1(int d, int s) {
        boolean temp[][] = new boolean[N][N];
        
        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            int pi = i + dx[d] * s;
            int pj = j + dy[d] * s;

            while (pi < 0) pi += N;
            while (pj < 0) pj += N;

            pi %= N;
            pj %= N;

            temp[pi][pj] = cloud[i][j];
        }

        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            cloud[i][j] = temp[i][j];
        }
    }

    static void step2() {
        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            a[i][j] += cloud[i][j] ? 1 : 0;
        }
    }

    static void step3() {
        int temp[][] = new int[N][N];

        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            if (!cloud[i][j]) continue;

            for (int k = 1; k < 8; k += 2) {
                int pi = i + dx[k];
                int pj = j + dy[k];

                if (pi < 0 || pj < 0 || pi >= N || pj >= N) continue;

                temp[i][j] += a[pi][pj] != 0 ? 1 : 0;
            }
        }
        
        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            a[i][j] += temp[i][j];
        }
    }

    static void step4() {
        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            if (cloud[i][j] == false && a[i][j] >= 2) {
                a[i][j] -= 2;
                cloud[i][j] = true;
            } else {
                cloud[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();

        a = new int[N][N];
        cloud = new boolean[N][N];

        cloud[N - 1][0] = true;
        cloud[N - 1][1] = true;
        cloud[N - 2][0] = true;
        cloud[N - 2][1] = true;

        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            a[i][j] = sc.nextInt();
        }

        for (int i = 0; i < M; ++i) {
            step1(sc.nextInt() - 1, sc.nextInt());
            step2();
            step3();
            step4();
        }

        int count = 0;
        for (int i = 0; i < N; ++i) for (int j = 0; j < N; ++j) {
            count += a[i][j];
        }
        System.out.println(count);
    }
}