import java.io.*;
import java.util.*;
import java.lang.*;

class Node_1916 implements Comparable<Node_1916> {
    int num;            // 노드 번호
    long w;             // 해당 노드로 오는 에지의 가중치

    Node_1916(int num, long w) {
        this.num = num;
        this.w = w;
    }

    @Override
    public int compareTo(Node_1916 n) {         // 가중치 기준 오름차순 정렬
        if (this.w < n.w) return -1;
        else if (this.w == n.w) return 0;
        return 1;
    }
}

class Solution_1916 {

    List<Node_1916>[] graph;
    boolean[] visit;
    long[] dist;
    final long INF = 10_000_000_000L;
    int start, end;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from].add(new Node_1916(to, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 다익스트라
        visit = new boolean[n + 1];
        dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        dijkstra();

        System.out.println(dist[end]);
        br.close();
    }

    void dijkstra() {
        PriorityQueue<Node_1916> pq = new PriorityQueue<>();
        pq.add(new Node_1916(start, 0));        // 출발지점

        while (!pq.isEmpty()) {
            Node_1916 poll = pq.poll();
            if (!visit[poll.num]) {
                visit[poll.num] = true;

                // poll 노드와 인접한 노드로의 dist update
                for (Node_1916 next : graph[poll.num]) {
                    if (dist[next.num] > dist[poll.num] + next.w) {
                        dist[next.num] = dist[poll.num] + next.w;
                        pq.add(new Node_1916(next.num, dist[next.num]));
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1916 s = new Solution_1916();
        s.solution();
    }
}

/**
 * 골드 5 1916번 최소비용 구하기
 *
 * 노드 n개, 에지 m개 -> 출발점에서 도착점까지의 최소 비용 구하기
 * 플로이드 알고리즘 -> 시간 초과
 * 다익스트라 알고리즘 -> ok
 *
 */
