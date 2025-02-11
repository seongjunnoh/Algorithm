import java.io.*;
import java.util.*;

class Edge_1504 implements Comparable<Edge_1504> {
    int node;
    int w;

    Edge_1504(int node, int w) {
        this.node = node;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1504 e) {
        return this.w - e.w;            // 거리 짧은 순 정렬
    }
}

class Solution_1504 {

    int n, e;
    List<Edge_1504>[] graph;
    List<Integer> len;
    final int INF = 200_000_000;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge_1504(b, c));
            graph[b].add(new Edge_1504(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int w1 = 0;
        len = new ArrayList<>();
        if (dijkstra(1, v1) && dijkstra(v1, v2) && dijkstra(v2, n)) {
            for (int length : len) {
                w1 += length;
            }
        }

        int w2 = 0;
        len = new ArrayList<>();
        if (dijkstra(1, v2) && dijkstra(v2, v1) && dijkstra(v1, n)) {
            for (int length : len) {
                w2 += length;
            }
        }

        if (w1 == 0 && w2 == 0) {
            System.out.println("-1");
        } else {
            if (w1 == 0) {
                System.out.println(w2);
            } else if (w2 == 0) {
                System.out.println(w1);
            } else {
                System.out.println(Math.min(w1, w2));
            }
        }
        br.close();
    }

    boolean dijkstra(int a, int b) {
        boolean[] visit = new boolean[n + 1];
        int[] l = new int[n + 1];           // a -> 다른 노드로 가는 최단 경로
        Arrays.fill(l, INF);        // 초기화

        PriorityQueue<Edge_1504> pq = new PriorityQueue<>();
        pq.add(new Edge_1504(a, 0));

        while (!pq.isEmpty()) {
            if (l[b] < INF) break;          // a -> b 까지의 최단 거리 구했으면 break
            
            Edge_1504 poll = pq.poll();
            visit[poll.node] = true;
            l[poll.node] = Math.min(l[poll.node], poll.w);

            for (Edge_1504 near : graph[poll.node]) {
                if (!visit[near.node] && l[near.node] > near.w + poll.w) {           // pq에 add하는 조건 추가
                    pq.add(new Edge_1504(near.node, near.w + poll.w));
                }
            }
        }

        if (!visit[b]) {            // a에서부터 탐색했는데 b에 도달하지 못한 경우
            return false;
        }

        len.add(l[b]);
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1504 s = new Solution_1504();
        s.solution();
    }
}

/**
 * 1 -> v1 -> v2 -> n or 1 -> v2 -> v1 -> n 경로 중 최단 거리 구하기
 * 다익스트라 알고리즘
 */