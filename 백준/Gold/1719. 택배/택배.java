import java.io.*;
import java.util.*;

class Edge {
    int from;
    int to;
    int w;
    
    Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

class Node implements Comparable<Node> {
    int v;  // 현재 노드 번호
    int d;  // start -> v 까지의 최단거리
    
    Node(int v, int d) {
        this.v = v;
        this.d = d;
    }
    
    @Override
    public int compareTo(Node n) {
        return this.d - n.d;
    }
}

class Solution {
    
    int n, m;
    List<Edge>[] graph; 
    int[][] map;    // 경로표
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph[x].add(new Edge(x, y, w));
            graph[y].add(new Edge(y, x, w));
        }
        
        map = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            dijkstra(i);    // 다익스트라 n번
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (map[i][j] == 0) sb.append("-").append(" ");
                else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        br.close();
    }
    
    void dijkstra(int start) {
        int[] dist = new int[n+1];  // 최단거리
        int[] firstHop = new int[n+1];  // start -> i 의 최단 경로 중, 첫번째 방문 노드
        boolean[] visit = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(firstHop, 0);
        
        dist[start] = 0;
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node poll = pq.poll();
            
            if (visit[poll.v]) continue;
            
            // start -> poll.v 까지의 최단경로 fix
            visit[poll.v] = true;
            if (poll.v != start) map[start][poll.v] = firstHop[poll.v];
            
            for (Edge near : graph[poll.v]) {   // pq에 연결된 다음 노드 add
                if (visit[near.to]) continue;
                if (dist[poll.v] + near.w < dist[near.to]) {
                    dist[near.to] = dist[poll.v] + near.w;
                    firstHop[near.to] = (poll.v == start) ? near.to : firstHop[poll.v];
                    pq.add(new Node(near.to, dist[near.to]));
                }
            }
        }
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 최단경로를 통하기 위해서 제일 먼저 들어야하는 집하장 구하기
 * 
 * 다익스트라 n번 + 다익스트라 시 경로표의 집하장 기록 추가 
 * 다익스트라 시에 PQ에 불필요한 경로는 push 하지 않는게 더 효율적
 */