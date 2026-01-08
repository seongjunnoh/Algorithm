import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int w;
    
    Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.w - e.w;    // w 기준 오름차순 정렬
    }
}

class Solution {
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Edge>[] graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a].add(new Edge(a, b, c));
            graph[b].add(new Edge(b, a, c));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n+1];
        
        for (Edge e : graph[1]) {   // 슈퍼 컴퓨터와 연결되어 있는 edge들로 pq 초기화
            pq.add(e);
        }
        visit[1] = true;    // 슈퍼 컴퓨터 방문 처리
        
        List<int[]> res = new ArrayList<>();    // 복구할 회선 쌍
        int count = 0;      // 복구할 회선 개수
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            
            if (visit[poll.to]) continue;
            
            visit[poll.to] = true;      // to 점 방문 처리
            
            count++;
            int[] r = {poll.from, poll.to};
            res.add(r);
            
            for (Edge e : graph[poll.to]) {
                pq.add(new Edge(e.from, e.to, poll.w + e.w));   // 1 -> e.to 까지의 이동 거리
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for (int[] r : res) {
            sb.append(r[0]).append(" ").append(r[1]).append("\n");
        }
        
        System.out.println(sb);
        br.close();
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 
 * 슈퍼 컴퓨터는 다른 컴퓨터들과 최소 비용으로 통신해야한다
 * 슈퍼 컴퓨터 기준 다익스트라 진행
 */