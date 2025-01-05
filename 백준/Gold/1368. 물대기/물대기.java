import java.io.*;
import java.util.*;

class Edge_1368 implements Comparable<Edge_1368> {
    int x;
    int y;
    int w;

    Edge_1368(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1368 e) {
        return this.w - e.w;
    }
}

class Solution_1368 {

    List<Edge_1368> edges;
    int[] parent;
    int result = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            // 우물 파는 비용 == 노드 0에서 해당 노드로 이동하는데 걸리는 비용 이라고 생각
            edges.add(new Edge_1368(0, i, Integer.parseInt(br.readLine())));
        }
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            for (int j = i + 1; j <= n; j++) {
                edges.add(new Edge_1368(i, j, Integer.parseInt(tokens[j - 1])));
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;          // parent 초기화
        }

        Collections.sort(edges);            // 에지들의 가중치 기준 오름차순 정렬
        for (Edge_1368 edge : edges) {
            if (union(edge.x, edge.y)) result += edge.w;        // 해당 에지 채택
        }

        System.out.println(result);
        br.close();
    }

    boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;

        if (x < y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    int find(int node) {
        if (node == parent[node]) return node;
        parent[node] = find(parent[node]);          // 경로 압축
        return parent[node];
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1368 s = new Solution_1368();
        s.solution();
    }
}

/**
 * 골드 2 1368번 물대기
 *
 * 모든 논에 물대는데 필요한 최소 비용 구하기
 * -> 크루스칼 알고리즘으로 최소 스패닝 트리 구성
 * -> & 모든 트리에 대해서 우물을 몇개를 파는게 좋은지를 체크 -> ?? -> 가상의 노드 하나 추가 (우물을 파는 비용을 가상의 노드에서 해당 노드로 이동하는 거라고 생각)
 *
 */
