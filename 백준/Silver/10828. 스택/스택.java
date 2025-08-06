
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    int n = Integer.parseInt(st.nextToken());
                    stack.push(n);
                    break;
                case "pop":
                    if(stack.empty()) System.out.println(-1);
                    else System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    if(stack.empty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "top":
                    if(stack.empty()) System.out.println(-1);
                    else System.out.println(stack.lastElement());
                default:
                    break;
            }
        }
    }
}
