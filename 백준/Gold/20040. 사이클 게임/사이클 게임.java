
import java.util.*;
import java.io.*;
public class Main {
	static int N, M;
	static int[] parents;
	static int count = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(find(a) == find(b)) {
				System.out.println(count);
				System.exit(0);
			}
			union(a, b);
		}
		System.out.println(0);
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if(a != b) {
			parents[b] = a;
			count++;
		}
	}

}
