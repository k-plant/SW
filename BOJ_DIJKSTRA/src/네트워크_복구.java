import java.util.*;
import java.io.*;

public class 네트워크_복구 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input/네트워크_복구.txt"));
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

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[n+1];
		Edge here;
		ArrayList<Edge> results = new ArrayList<>();
		
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			here = pq.poll();
			if(visited[here.num]) continue;
			visited[here.num] = true;
			if(here.from != 0) results.add(here);
			
			for(Edge next : map[here.num]) {
				if(!visited[next.num] && dist[next.num] > dist[here.num] + next.cost) {
//					next.from = here.num;
                    dist[next.num] = dist[here.num] + next.cost;
					pq.offer(new Edge(next.num, dist[next.num], here.num));
//					pq.offer(new Edge(next.num, next.cost, here.num));
				}
			}
		}

		bw.write(results.size() + "\n");
		for(Edge e : results) bw.write(e.from + " " + e.num + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int num;
		int cost;
		
		Edge(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		Edge(int num, int cost, int from) {
			this.num = num;
			this.cost = cost;
			this.from = from;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost > o.cost ? 1 : -1;
		}
	}
}

