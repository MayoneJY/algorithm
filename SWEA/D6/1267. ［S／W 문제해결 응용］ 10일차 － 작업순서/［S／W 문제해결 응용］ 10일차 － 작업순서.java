import java.util.*;
import java.io.*;

public class Solution {
    static final int T = 10;
    static Map<Integer, Info> graph = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= T; t++){
            int V, E;
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            for(int v = 1; v <= V; v++){
                graph.put(v, new Info(0, new ArrayList<>()));
            }
            st = new StringTokenizer(br.readLine());
            for(int e = 0; e < E; e++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).nodes.add(to);
                graph.get(to).inD++;
            }
            System.out.printf("#%d ", t);
            bfs();
        }
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= graph.size(); i++){
            if(graph.get(i).inD == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            Integer now = q.poll();

            System.out.printf("%d ", now);

            Info nowInfo = graph.get(now);
            for(int i = 0; i < nowInfo.nodes.size(); i++){
                int nextInfoIdx = nowInfo.nodes.get(i);
                Info nextInfo = graph.get(nextInfoIdx);
                nextInfo.inD--;
                if(nextInfo.inD == 0)
                    q.add(nextInfoIdx);

            }
        }
        System.out.println();
    }

    static class Info{
        int inD;
        List<Integer> nodes;
        Info(int inD, List<Integer> nodes){
            this.inD = inD;
            this.nodes = nodes;
        }
    }
}
