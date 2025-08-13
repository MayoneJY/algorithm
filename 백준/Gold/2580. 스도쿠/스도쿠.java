import java.util.*;
import java.io.*;

public class Main {
    static final int N = 9;
    static int[][] map = new int[N][N];
    static List<Node> list = new ArrayList<>();
    static boolean[] checkX = new boolean[N];
    static boolean[] checkY = new boolean[N];
    static boolean[] checkXY = new boolean[N];
    public static void main(String[] args) throws Exception {
        init();
        dfs(0);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) list.add(new Node(i, j));
            }
        }
    }

    static void dfs(int idx){
        if(idx == list.size()){
            print();
            System.exit(0);
        }

        Node now = list.get(idx);

        for(int i = 1; i <= N; i++){
            if(checkX(now.y, now.x, i) && checkY(now.y, now.x, i) && checkXY(now.y, now.x, i)){
                map[now.y][now.x] = i;
                dfs(idx+1);
                map[now.y][now.x] = 0;
            }
        }
    }

    static boolean checkX(int y, int x, int n){
        for(int i = 0; i < N; i++){
            if(map[y][i] == n) return false;
        }
        return true;
    }

    static boolean checkY(int y, int x, int n){
        for(int i = 0; i < N; i++){
            if(map[i][x] == n) return false;
        }
        return true;
    }

    static boolean checkXY(int y, int x, int n){
        for(int i = (y / 3) * 3; i < ((y / 3) + 1) * 3; i++){
            for(int j = (x / 3) * 3; j < ((x / 3) + 1) * 3; j++){
                if(map[i][j] == n)
                    return false;
            }
        }
        return true;
    }

    static void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
