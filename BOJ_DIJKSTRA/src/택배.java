import java.util.*;
import java.io.*;

public class 택배 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input/택배.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] map = new ArrayList[n+1];
		for(int i = 0; i < map.length; i++) map[i] = new ArrayList<Edge>();
		int a, b, t;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			map[a].add(new Edge(b, t));
			map[b].add(new Edge(a, t));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new EdgeComp());
//		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(
//				(Edge x, Edge y)->Integer.compare(x.cost,  y.cost));
		int[] init = new int[n+1];
		int[] dist = new int[n+1];
		int[][] parent = new int[n+1][n+1];
		boolean[] visited = new boolean[n+1];
		Edge here;
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(init, 0);
			Arrays.fill(dist, Integer.MAX_VALUE);
			Arrays.fill(parent[i], 0);
			Arrays.fill(visited, false);
			pq.clear();
			
			pq.add(new Edge(i, 0));
			dist[i] = 0;
			while(!pq.isEmpty()) {
				here = pq.poll();
				if(visited[here.num]) continue;
				visited[here.num] = true;
				for(Edge next : map[here.num]) {
					if(!visited[next.num] && dist[next.num] > dist[here.num] + next.cost) {
						dist[next.num] = dist[here.num] + next.cost;
						parent[i][next.num] = here.num;
						pq.offer(new Edge(next.num, dist[next.num]));
					}
				}
			}
		}
		
		for(int j = 1; j <= n; j++) {
			for(int k = 1; k <= n; k++) {
				if(parent[k][j] == 0) bw.write("- ");
				else bw.write(parent[k][j] + " ");
			}
			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
}

class EdgeComp implements Comparator<Edge> {
	@Override
	public int compare(Edge o1, Edge o2) {
		if(o1.cost > o2.cost) return 1;
		if(o1.cost < o2.cost) return -1;
		return 0;
	}
	
}

class Edge {
	int num;
	int cost;
	Edge(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}