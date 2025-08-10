
import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map;
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for(int i = 0; i < N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(map.containsKey(a)){
                map.get(a).add(b);
            }
            else{
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(b);
                map.put(a, temp);
            }
            if(map.containsKey(b)){
                map.get(b).add(a);
            }
            else{
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(a);
                map.put(b, temp);
            }
        }
        parent = new int[N+1];
        bfs();
        for(int i = 2; i <= N; i++){
            System.out.println(parent[i]);
        }
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(1);
        parent[1] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            List<Integer> temp = map.get(now);
            for(int j = 0; j < temp.size(); j++){
                int next = temp.get(j);
                if(parent[next] == 0){
                    parent[next] = now;
                    q.add(next);
                }
            }
        }
    }
}
