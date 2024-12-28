import java.io.*;
import java.util.*;

class Edge {
    int to;
    int w;

    Edge(int to, int w) {
        this.to = to;
        this.w = w;
    }
}

class Solution_1240 {

    List<Edge>[] graph;
    StringBuilder sb = new StringBuilder();
    boolean[] visit;
    int[] l;
    int result;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, w));
            graph[to].add(new Edge(from, w));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            visit = new boolean[n + 1];
            l = new int[n + 1];
            visit[from] = true;
            dfs(from, 0);

            sb.append(l[to]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    void dfs(int node, int length) {
        for (Edge edge : graph[node]) {
            int near = edge.to;
            if (!visit[near]) {
                visit[near] = true;
                l[near] = edge.w + length;
                dfs(near, l[near]);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1240 s = new Solution_1240();
        s.solution();
    }
}

/**
 * 골드 5 1240번 노드사이의 거리
 *
 * dfs를 m번 반복해도 시간초과 X
 */
