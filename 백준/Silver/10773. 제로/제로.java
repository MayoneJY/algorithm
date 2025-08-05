
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static Stack<Integer> st = new Stack<Integer>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp == 0){
                st.pop();
            }
            else{
                st.push(temp);   
            }
        }

        int sum = 0;
        for(int s: st){
            sum += s;
        }

        System.out.println(sum);
    }
}
