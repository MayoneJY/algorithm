
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int MAX_COUNT = 5;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(map, 1, new ArrayList<>());
        System.out.println(max);
    }

    static void dfs(int[][] map, int count, ArrayList<int[][]> arr){
        if(count > MAX_COUNT){
            checkMaxValue(map);
            return;
        }

        for(int dir = 0; dir < 4; dir++){
            int[][] copyMap = new int[N][N];
            for(int i = 0; i < N; i++)
                copyMap[i] = map[i].clone();
            move(copyMap, dir);
            ArrayList<int[][]> copyArr = new ArrayList<>();
            copyArr.addAll(arr);
            copyArr.add(copyMap);
            dfs(copyMap, count+1, copyArr);
        }
    }

    static void checkMaxValue(int[][] map){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                max = Math.max(map[i][j], max);
            }
        }
    }

    static void move(int[][] copyMap, int direction){
        switch (direction) {
            case 0: // 오른쪽
                for(int i = 0; i < N; i++){
                    boolean[] check = new boolean[N];
                    for(int j = N-1; j >= 0; j--){
                        if(copyMap[i][j] == 0) continue;
                        int count = 0;
                        while(true){
                            if(j+1+count >= N) break;
                            if(copyMap[i][j+1+count] != 0){
                                if(!check[j+1+count] && copyMap[i][j+1+count] == copyMap[i][j+count]){
                                    check[j+1+count] = true;
                                    copyMap[i][j+1+count] += copyMap[i][j+count];
                                    copyMap[i][j+count] = 0;
                                }
                                break;
                            }
                            copyMap[i][j+1+count] = copyMap[i][j+count];
                            copyMap[i][j+count] = 0;
                            count++;
                        }
                    }
                }
                break;
        
            case 1: // 아래
                for(int j = 0; j < N; j++){
                    boolean[] check = new boolean[N];
                    for(int i = N-1; i >= 0; i--){
                        if(copyMap[i][j] == 0) continue;
                        int count = 0;
                        while(true){
                            if(i+1+count >= N) break;
                            if(copyMap[i+1+count][j] != 0){
                                if(!check[i+1+count] && copyMap[i+1+count][j] == copyMap[i+count][j]){
                                    check[i+1+count] = true;
                                    copyMap[i+1+count][j] += copyMap[i+count][j];
                                    copyMap[i+count][j] = 0;
                                }
                                break;
                            }
                            copyMap[i+1+count][j] = copyMap[i+count][j];
                            copyMap[i+count][j] = 0;
                            count++;
                        }
                    }
                }
                break;
        
            case 2: // 왼쪽
                for(int i = 0; i < N; i++){
                    boolean[] check = new boolean[N];
                    for(int j = 0; j < N; j++){
                        if(copyMap[i][j] == 0) continue;
                        int count = 0;
                        while(true){
                            if(j-1-count < 0) break;
                            if(copyMap[i][j-1-count] != 0){
                                if(!check[j-1-count] && copyMap[i][j-1-count] == copyMap[i][j-count]){
                                    check[j-1-count] = true;
                                    copyMap[i][j-1-count] += copyMap[i][j-count];
                                    copyMap[i][j-count] = 0;
                                }
                                break;
                            }
                            copyMap[i][j-1-count] = copyMap[i][j-count];
                            copyMap[i][j-count] = 0;
                            count++;
                        }
                    }
                }
                break;
        
            case 3: // 위
                for(int j = 0; j < N; j++){
                    boolean[] check = new boolean[N];
                    for(int i = 0; i < N; i++){
                        if(copyMap[i][j] == 0) continue;
                        int count = 0;
                        while(true){
                            if(i-1-count < 0) break;
                            if(copyMap[i-1-count][j] != 0){
                                if(!check[i-1-count] && copyMap[i-1-count][j] == copyMap[i-count][j]){
                                    check[i-1-count] = true;
                                    copyMap[i-1-count][j] += copyMap[i-count][j];
                                    copyMap[i-count][j] = 0;
                                }
                                break;
                            }
                            copyMap[i-1-count][j] = copyMap[i-count][j];
                            copyMap[i-count][j] = 0;
                            count++;
                        }
                    }
                }
                break;
        
            default:
                break;
        }
    }
}
