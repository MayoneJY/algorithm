
import java.io.*;
import java.util.*;

class Main{
    static ArrayList<ArrayList<Integer>> players = new ArrayList<ArrayList<Integer>>();
    // static boolean[] visitor = new boolean[3];
    static ArrayList<Integer> temp = new ArrayList<Integer>();
    static int max;
    static int d;
    static int killMax = Integer.MIN_VALUE;
    static int rows;
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try{
            StringTokenizer st = new StringTokenizer(br.readLine());
            rows = Integer.parseInt(st.nextToken());
            max = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Integer>> oMap = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i < rows; i++){
                StringTokenizer cols = new StringTokenizer(br.readLine());
                ArrayList<Integer> t = new ArrayList<Integer>();
                while (cols.hasMoreTokens()) {
                    t.add(Integer.parseInt(cols.nextToken()));
                }
                oMap.add(t);
            }
            combinations(0);
            for (ArrayList<Integer> player : players) {
                ArrayList<ArrayList<Integer>> cMap = new ArrayList<>();
                for (ArrayList<Integer> row : oMap) {
                    cMap.add(new ArrayList<>(row)); // 각 행을 새로 복사해서 추가
                }
                int k = kill(cMap, player);
                killMax = Math.max(k, killMax);
            }
            bw.write(Integer.toString(killMax));
            bw.flush();
            bw.close();


        }
        catch(Exception e){

        }
    }

    public static void combinations(int idx){
        if(temp.size() == 3){
            ArrayList<Integer> t = new ArrayList<Integer>(temp);
            players.add(t);
            return;
        }
        for(int i = idx; i < max; i++){
            temp.add(i);
            combinations(i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static int kill(ArrayList<ArrayList<Integer>> cMap, ArrayList<Integer> player){
        int killCount = 0;
        for(int m = 1; m <= rows; m++){
            ArrayList<int[]> k = new ArrayList<int[]>();
            for (int p : player) {
                loopOut:
                for(int j = 0; j < d; j++){
                    int i = 0;
                    int l = 0;
                    boolean turn = false;
                    while (i >= 0) {
                        if(i == j) turn = true;
                        // System.out.println(player);
                        // System.out.println(p+l-j);
                        // System.out.println(cMap.size()-m-i);
                        if(0 <= cMap.size()-m-i){
                            if(p+l-j >= 0 && p+l-j < max){
                                if(cMap.get(cMap.size()-m-i).get(p+l-j) == 1){
                                    k.add(new int[]{p+l-j, cMap.size()-m-i});
                                    break loopOut;
                                }
                            }
                        }
                        if(turn) i--;
                        else i++;
                        l++;
                    }
                }
            }
            for (int[] temp : k) {
                ArrayList<Integer> c = cMap.get(temp[1]);
                
                if(c.get(temp[0]) == 1){
                    c.set(temp[0], 0);
                    cMap.set(temp[1], c);
                    killCount++;
                }
            }
            k.clear();
        }
        // System.out.println(player);
        // System.out.println(killCount);
        return killCount;
    }
}