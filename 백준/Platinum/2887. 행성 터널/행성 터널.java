import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;

    static class Planet {
        int idx, x, y, z;
        Planet(int idx, int x, int y, int z){
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge {
        int s, v, w;
        Edge(int s, int v, int w){
            this.s = s;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Planet[] planets = new Planet[N];
        parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
        }
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i, a, b, c);
        }
        
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));

        Arrays.sort(planets, Comparator.comparing(o -> o.x));
        for(int i = 0; i < N-1; i++){
            pq.add(new Edge(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].x - planets[i+1].x)));
        }
        Arrays.sort(planets, Comparator.comparing(o -> o.y));
        for(int i = 0; i < N-1; i++){
            pq.add(new Edge(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].y - planets[i+1].y)));
        }
        Arrays.sort(planets, Comparator.comparing(o -> o.z));
        for(int i = 0; i < N-1; i++){
            pq.add(new Edge(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].z - planets[i+1].z)));
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Edge n = pq.poll();
            int a = find(n.s);
            int b = find(n.v);
            if(a != b){
                union(a, b);
                result += n.w;
            }
        }
        System.out.println(result);
    }

    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }
}
