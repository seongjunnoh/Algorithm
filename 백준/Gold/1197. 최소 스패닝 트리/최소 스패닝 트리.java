import java.io.*;
import java.util.*;

class Edge_1197 implements Comparable<Edge_1197> {
    int x;
    int y;
    int w;

    Edge_1197(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1197 e) {
        return this.w - e.w;
    }
}

class Solution_1197 {

    Edge_1197[] edges;
    int[] parent;
    int result;         // 최소 스패닝 트리 가중치

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        edges = new Edge_1197[e];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge_1197(x, y, w);
        }

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;          // parent 초기화
        }

        Arrays.sort(edges);
        for (Edge_1197 edge : edges) {
            if (union(edge.x, edge.y)) result += edge.w;
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
        if (parent[node] == node) return node;

        parent[node] = find(parent[node]);
        return parent[node];
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1197 s = new Solution_1197();
        s.solution();
    }
}

/**
 * 골드 4 1197번 최소 스패닝 트리
 *
 * 크루스칼 알고리즘으로 구현
 */
