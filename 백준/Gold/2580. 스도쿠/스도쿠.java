import java.util.*;
import java.io.*;

public class Main {
    static final int N = 9;
    static int[][] map = new int[N][N];
    static List<Node> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) list.add(new Node(i, j));
            }
        }
        dfs(0);
    }

    static void dfs(int idx){
        if(idx == list.size()){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        Node now = list.get(idx);
        boolean[] checkX = new boolean[N];
        boolean[] checkY = new boolean[N];
        for(int i = 0; i < N; i++){
            if(map[now.y][i] != 0)
                checkX[map[now.y][i]-1] = true;
            if(map[i][now.x] != 0)
                checkY[map[i][now.x]-1] = true;
        }
        boolean[] checkXY = new boolean[N];
        for(int i = (now.y / 3) * 3; i < ((now.y / 3) + 1) * 3; i++){
            for(int j = (now.x / 3) * 3; j < ((now.x / 3) + 1) * 3; j++){
                if(map[i][j] != 0)
                    checkXY[map[i][j]-1] = true;
            }
        }
        int[] possible = new int[N];
        Arrays.fill(possible, -1);

        boolean check = false;
        int p = 0;
        for(int i = 0; i < N; i++){
            if(!checkX[i] && !checkY[i] && !checkXY[i]){
                possible[p] = i+1;
                p++;
                check = true;
            }
        }
        if(!check) return;
        for(int i = 0; i < possible.length; i++){
            if(possible[i] == -1) break;
            map[now.y][now.x] = possible[i];
            dfs(idx+1);
            map[now.y][now.x] = 0;
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
