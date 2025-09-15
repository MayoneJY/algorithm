
import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());
            List<String> latter = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++){
                latter.add(st.nextToken());
            }

            if(N % 2 == 0){
                for(int i = (N/2) - 1; i >= 0; i--){
                    String str = latter.get(((N/2) - 1)+(N/2));
                    latter.remove(((N/2) - 1)+(N/2));
                    latter.add(i+1, str);
                }
            }
            else{
                for(int i = (N/2) - 1; i >= 0; i--){
                    String str = latter.get(((N/2) - 1)+(N/2)+1);
                    latter.remove(((N/2) - 1)+(N/2)+1);
                    latter.add(i+1, str);
                }
            }

            System.out.printf("#%d ", t);
            for(int i = 0; i < N; i++){
                System.out.printf("%s ", latter.get(i));
            }
            System.out.println();

            //6
            // 0 1 2 3 4 5
            // a b c d e f
            // 0  3  1  4  2  5
            // a (d) b (e) c (f)
            //5
            // 0 1 2 3 4
            // a b c d e
            // 0  3  1  4  2
            // a (d) b (e) c
            

        }
    }
}


