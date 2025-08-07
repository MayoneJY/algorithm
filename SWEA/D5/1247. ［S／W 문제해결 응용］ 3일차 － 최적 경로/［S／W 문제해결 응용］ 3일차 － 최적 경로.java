
import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static List<int[]> com;
    static Where[] people;
    static int min;
    static Where office;
    static Where home;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            people = new Where[N];
            com = new ArrayList<int[]>();
            min = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            office = new Where(Integer.parseInt(st.nextToken()),
                                        Integer.parseInt(st.nextToken()));
            home = new Where(Integer.parseInt(st.nextToken()),
                                        Integer.parseInt(st.nextToken()));
            for(int n = 0; n < N; n++){
                people[n] = new Where(Integer.parseInt(st.nextToken()),
                                        Integer.parseInt(st.nextToken()));
            }
            int[] temp = new int[N];
            for(int i = 0; i < N; i++)
                temp[i] = -1;
            combination(temp, 0);

            // for(int i = 0; i < com.size(); i++){
            //     sumDistance(com.get(i), office, home);
            // }
            bw.write("#" + Integer.toString(t) + " " + Integer.toString(min) + "\n");
            bw.flush();
        }
        bw.close();
    }

    static void sumDistance(int[] c){
        Where now = office;
        int sum = 0;

        for(int i = 0; i < N; i++){
            Where next = people[c[i]];
            sum += Math.abs(now.x - next.x) + Math.abs(now.y - next.y);
            if(sum > min) return;
            now = next;
        }
        sum += Math.abs(now.x - home.x) + Math.abs(now.y - home.y);
        
        min = Math.min(min, sum);
    }

    static void combination(int[] c, int idx){
        if(idx == N){
            // com.add(c);
            sumDistance(c);
            // System.out.println(Arrays.toString(c));
            return;
        }

        for(int i = 0; i < N; i++){
            boolean temp = false;
            for(int j = 0; j < N; j++){
                if(c[j] == i) temp = true;
            }
            if(!temp){
                int[] tempC = new int[N];
                tempC = c.clone();
                tempC[idx] = i;
                combination(tempC, idx+1);
            }
        }
    }

    static class Where{
        int x, y;
        Where(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
