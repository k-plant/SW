import java.util.*;
import java.io.*;

public class 거의_최단_경로 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
	
		System.setIn(new FileInputStream("input/거의_최단_경로.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N, M;	// 장소의 수, 도로의 수
		int S, D;	// 시작, 도착
		int a, b, c;	// Edge
		int[] visited;	// 방문 여부
		int[] dist;	// 해당 노드까지의 최단 거리
		int[][] map = new int[N][N];

		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N + M == 0) break;
			
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				map[a]
			}
		}
		
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	static class Edge implements Comparable<Edge> {
		int num;
		int cost;
		
		Edge(int num, int cost) {
//			super();
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost > o.cost ? 1 : -1;
		}
	}

}
