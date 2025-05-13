import java.io.*;
import java.util.*;

class Node_9370 implements Comparable<Node_9370> {
    int to;
    int w;

    Node_9370(int to, int w) {
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Node_9370 e) {
        return this.w - e.w;
    }
}

class Solution_9370 {

    final int INF = Integer.MAX_VALUE;
    int node;
    int[] dest;
    List<Node_9370>[] graph;
    int[] startPath, hPath, gPath;
    boolean[] visit;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            node = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int destSize = Integer.parseInt(st.nextToken());
            dest = new int[destSize];
            graph = new ArrayList[node + 1];
            for (int n = 1; n <= node; n++) {
                graph[n] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int j = 0; j < edge; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph[from].add(new Node_9370(to, w));
                graph[to].add(new Node_9370(from, w));
            }

            for (int j = 0; j < destSize; j++) {
                dest[j] = Integer.parseInt(br.readLine());
            }

            // 다익스트라 3번 (시작점이 start, h, g 3개)
            visit = new boolean[node + 1];
            startPath = new int[node + 1];
            Arrays.fill(startPath, INF);
            dijkstra(start, startPath);

            hPath = new int[node + 1];
            Arrays.fill(hPath, INF);
            Arrays.fill(visit, false);
            dijkstra(h, hPath);

            gPath = new int[node + 1];
            Arrays.fill(gPath, INF);
            Arrays.fill(visit, false);
            dijkstra(g, gPath);

            List<Integer> possibleDest = new ArrayList<>();
            for (int j = 0; j < destSize; j++) {
                int d = dest[j];

                if (startPath[d] == Math.min((startPath[h] + hPath[g] + gPath[d]), (startPath[g] + gPath[h] + hPath[d]))) {
                    possibleDest.add(d);
                }
            }

            Collections.sort(possibleDest);
            for (int d : possibleDest) {
                sb.append(d).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    void dijkstra(int start, int[] path) {
        path[start] = 0;        // start -> 모든 노드로의 최단 거리
        PriorityQueue<Node_9370> pq = new PriorityQueue<>();
        pq.add(new Node_9370(start, 0));

        while (!pq.isEmpty()) {
            Node_9370 curNode = pq.poll();
            if (visit[curNode.to]) continue;       // 이미 방문한 노드 -> 업데이트 X

            visit[curNode.to] = true;
            for (Node_9370 next : graph[curNode.to]) {
                if (path[next.to] > path[curNode.to] + next.w) {        // 업데이트
                    path[next.to] = path[curNode.to] + next.w;
                    pq.add(new Node_9370(next.to, path[next.to]));
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_9370 s = new Solution_9370();
        s.solution();
    }
}

/**
 * start에서 dest 중 하나로 가는 가능한 모든 최단경로에 g<->h edge가 포함되는지 아닌지 판단해야한다
 * start -> dest 의 가중치 < min((start->h) + (h->g) + (g->dest), (start->g) + (g->h) + (h->dest))
 * 일 경우 해당 dest는 불가능
 * 아닐 경우 해당 dest는 ok
 * -> 다익스트라 알고리즘으로 node->node 간의 최단거리 구하기
 */