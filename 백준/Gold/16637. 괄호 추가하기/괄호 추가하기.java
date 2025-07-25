import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Main {
    static int N;
    static ArrayList<Integer> num = new ArrayList<Integer>();
    static ArrayList<Character> ops = new ArrayList<Character>();
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try{
            N = Integer.parseInt(br.readLine());
            String input2 = br.readLine();
            if(input2.length() == 1){
                bw.write(input2);
                bw.flush();
                bw.close();
                return;
            }
            for(int i = 0; i < N; i++){
                if(i % 2 == 0) num.add(input2.charAt(i) - '0');
                else ops.add(input2.charAt(i));
            }

            dfs(0, num.get(0));

            bw.write(Integer.toString(max));
            bw.flush();
            bw.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    public static void dfs(int idx, int current){
        if(idx >= ops.size()){
            max = Math.max(max, current);
            return;
        }

        int calcNum = calc(current, ops.get(idx), num.get(idx+1));
        dfs(idx+1, calcNum);

        if(idx+1 < ops.size()){
            int calcBrackets = calc(num.get(idx+1), ops.get(idx+1), num.get(idx+2));
            int calcBracketsNum = calc(current, ops.get(idx), calcBrackets);
            dfs(idx+2, calcBracketsNum);
        }
    }

    public static int calc(int num1, char ops, int num2){
        switch (ops) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                return num1 * num2;
        }
    }
}