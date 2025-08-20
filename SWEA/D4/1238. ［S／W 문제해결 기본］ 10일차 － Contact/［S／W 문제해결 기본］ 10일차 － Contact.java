
import java.util.*;
import java.io.*;

public class Solution {
    static final int T = 10;
    static int N, S;
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    static Map<Integer, Integer> visite = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            map.clear();
            visite.clear();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N/2; i++){
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                if(map.containsKey(s)){
                    map.get(s).add(e);
                }
                else{
                    Set<Integer> set = new HashSet<>();
                    set.add(e);
                    map.put(s, set);
                }
                if(!visite.containsKey(s))
                    visite.put(s, 0);
                if(!visite.containsKey(e))
                    visite.put(e, 0);
            }

            bfs();
            int max = Integer.MIN_VALUE;
            int idx = Integer.MIN_VALUE;
            for(Integer key : visite.keySet()){
                int value = visite.get(key);
                if(max <= value){
                    max = value;
                    if(idx <= key)
                        idx = key;
                }
            }
            System.out.printf("#%d %d\n", t, idx);
        }
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(S);
        visite.replace(S, 1);
        while(!q.isEmpty()){
            Integer nowIdx = q.poll();
            if(map.containsKey(nowIdx)){
                Set<Integer> now = map.get(nowIdx);
                
                for(Integer n : now){
                    if(visite.get(n) == 0){
                        q.add(n);
                        visite.replace(n, visite.get(nowIdx)+1);
                    }
                }
            }
        }
    }
}
