import java.io.*;
import java.util.*;

class Node_1753 implements Comparable<Node_1753> {
    int num;
    int w;

    Node_1753(int num, int w) {
        this.num = num;
        this.w = w;
    }

    @Override
    public int compareTo(Node_1753 n) {
        return this.w - n.w;
    }
}

class Solution_1753 {

    List<Node_1753>[] graph;
    int[] dist;           // start -> i까지의 최단거리
    boolean[] visit;
    final int INF = 3_000_001;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node_1753(v, w));
        }

        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == start) dist[i] = 0;
            else dist[i] = INF;
        }
        visit = new boolean[n + 1];     
        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    void dijkstra(int start) {
        PriorityQueue<Node_1753> pq = new PriorityQueue<>();
        pq.add(new Node_1753(start, 0));            // 출발점

        while (!pq.isEmpty()) {
            Node_1753 poll = pq.poll();             // 현재 start에서 최단거리로 갈 수 있는 노드

            if (visit[poll.num]) continue;          // 이미 해당 노드로의 최단거리 구한경우는 skip

            visit[poll.num] = true;
            for (Node_1753 next : graph[poll.num]) {            // 뽑은 노드의 주변노드로의 최단 거리 update
                if (dist[next.num] > dist[poll.num] + next.w) {
                    dist[next.num] = dist[poll.num] + next.w;
                    pq.add(new Node_1753(next.num, dist[next.num]));
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1753 s = new Solution_1753();
        s.solution();
    }
}

/**
 * 시작점에서 다른 모든 정점으로의 최단 경로 구하기
 *
 * 특정 정점에서 모든 정점으로의 최단거리 구하기 -> 다익스트라
 * 서로 다른 두 정점 사이에 여러 개의 간선 존재할 수 있음
 */
