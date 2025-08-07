
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int M;
    static int[][] node;
    static Set<Integer> friends = new HashSet<Integer>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        node = new int[M][2];
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            node[i][0] = Integer.parseInt(st.nextToken());
            node[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(node, (o1, o2) -> o1[0]-o2[0]);
        for(int[] temp : node){
            if(temp[0] == 1){
                friends.add(temp[1]);
            }
            else
                break;
        }
        Integer[] tempFriends = new Integer[friends.size()];
        tempFriends = friends.toArray(tempFriends);

        for(int temp : tempFriends){
            for(int[] tempNode : node){
                if(tempNode[0] == temp)
                    friends.add(tempNode[1]);
                else if(tempNode[1] == temp)
                    friends.add(tempNode[0]);
            }
        }

        friends.remove(1);

        System.out.println(friends.size());
        
    }
}
