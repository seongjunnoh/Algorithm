import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_1005 {

    List<Integer>[] graph;
    int[] time;             // i번째 건물을 짓는데 걸리는 시간
    int[] dp;               // i번째 건물을 짓기 까지 걸리는 최소 시간

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            time = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                time[j] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 1; j <= k; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[to].add(from);
            }

            int w = Integer.parseInt(br.readLine());

            dp = new int[n + 1];
            Arrays.fill(dp, -1);        // 초기화

            dfs(w);

            sb.append(dp[w]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    void dfs(int node) {
        if (graph[node].isEmpty()) {            // 더 이상 거슬러 올라갈 노드 없음
            dp[node] = time[node];
            return;
        }

        int max = 0;
        for (Integer from : graph[node]) {
            if (dp[from] == -1) dfs(from);
            max = Math.max(max, dp[from] + time[node]);
        }

        dp[node] = max;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1005 s = new Solution_1005();
        s.solution();
    }
}

/**
 * w 건물을 짓기 위해서 먼저 이전 순서의 건물들을 모두 지어야 함
 * w 건물 짓는데 걸리는 최소 시간 = w 건물 이전순서의 건물 짓는데 걸리는 최소시간들 중 최댓값 + w 건물 하나 짓는 시간
 * -> dp
 */