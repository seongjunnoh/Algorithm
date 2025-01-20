import java.io.*;
import java.util.*;
import java.lang.*;

class Node_1238 implements Comparable<Node_1238> {
    int num;        // 노드 번호
    int t;          // 해당 노드로 가는데 걸리는 시간

    Node_1238(int num, int t) {
        this.num = num;
        this.t = t;
    }

    @Override
    public int compareTo(Node_1238 n) {
        return this.t - n.t;
    }
}

class Solution_1238 {

    boolean[] visit;
    final int INF = 1_000_001;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Node_1238>[] nGraph = new ArrayList[n + 1];            // 정방향 그래프
        List<Node_1238>[] rGraph = new ArrayList[n + 1];            // 역방향 그래프
        for (int i = 1; i <= n; i++) {
            nGraph[i] = new ArrayList<>();
            rGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            nGraph[from].add(new Node_1238(to, t));
            rGraph[to].add(new Node_1238(from, t));
        }

        int[] toHome = new int[n + 1];
        Arrays.fill(toHome, INF);
        visit = new boolean[n + 1];
        dijkstra(x, nGraph, toHome);        // x -> n개의 집

        int[] toParty = new int[n + 1];
        Arrays.fill(toParty, INF);
        visit = new boolean[n + 1];
        dijkstra(x, rGraph, toParty);       // n개의 집 -> x

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, toHome[i] + toParty[i]);
        }

        System.out.println(max);
        br.close();
    }

    void dijkstra(int start, List<Node_1238>[] graph, int[] dist) {     // start -> n개의 노드로의 최단거리
        PriorityQueue<Node_1238> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node_1238(start, 0));

        while (!pq.isEmpty()) {
            Node_1238 poll = pq.poll();

            if (!visit[poll.num]) {
                visit[poll.num] = true;

                for (Node_1238 near : graph[poll.num]) {
                    if (dist[near.num] > dist[poll.num] + near.t) {
                        dist[near.num] = dist[poll.num] + near.t;
                        pq.add(new Node_1238(near.num, dist[near.num]));
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1238 s = new Solution_1238();
        s.solution();
    }

}

/**
 * 집 -> x -> 집으로 가는 시간이 가장 큰 학생의 소요시간 구하기
 *
 * 플로이드 -> 시간초과
 * 다익스트라 n번 반복 -> 모든 노드에 대해서 다른 노드로의 최단 거리 구하기
 * cf) 정방향, 역방향으로 그래프를 2개로 나눠 생각하면 다익스트라 2번으로 해결 가능
 */