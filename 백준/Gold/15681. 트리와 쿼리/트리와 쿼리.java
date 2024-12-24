import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static int[] parent;
    static int n;
    static int[] dp;        // dp[i] : i가 루트인 subtree에 속하는 노드의 개수

    static void makeParent(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        boolean[] visit = new boolean[n + 1];
        visit[node] = true;

        while (!q.isEmpty()) {
            int poll = q.poll();
            for (int near : graph[poll]) {
                if (!visit[near]) {
                    parent[near] = poll;
                    visit[near] = true;
                    q.add(near);
                }
            }
        }
    }

    static int dfs(int node) {
        int count = 0;

        for (int near : graph[node]) {
            if (parent[near] == node) {     // near가 child 인 경우
                if (dp[near] != 0) dp[node] += dp[near];
                else dp[node] += dfs(near);
                count++;
            }
        }

        if (count == 0) {       // node가 leaf인 경우
            dp[node] = 1;
            return dp[node];
        }

        dp[node] += 1;     // subtree 개수에 자기 자신(= node) 도 포함
        return dp[node];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        parent = new int[n + 1];
        parent[r] = 0;      // r이 루트노드

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // parent 배열 채우기
        makeParent(r);

        // dp table update
        dp = new int[n + 1];
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 5 15681번 트리와 쿼리
 *
 * 입력 트리 -> root 부터 bfs 하면서 각 노드의 부모 설정
 *
 * ------------------------------------
 * 메모리 초과 -> 매번 bfs 로 subtree의 노드 개수 세는 것이 아니라 dp로 메모이제이션 해야할 듯 -> 백트래킹으로 dp table update
 */
