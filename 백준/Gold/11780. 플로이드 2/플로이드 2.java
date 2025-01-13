import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_11780 {

    final int INF = 10_000_001;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][n + 1];         // i -> j 로 가는 최소 비용
        for (int i = 1; i <= n; i++) {              // dp 초기화
            for (int j = 1; j <= n; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = INF;
            }
        }

        List<Integer>[][] routes = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                List<Integer> list = new ArrayList<>();
                routes[i][j] = list;            // routes 초기화 (빈 리스트)
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dp[a][b] = Math.min(dp[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        List<Integer> list = new ArrayList<>();
                        list.addAll(routes[i][k]);
                        list.add(k);
                        list.addAll(routes[k][j]);
                        routes[i][j] = list;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == INF) sb.append(0).append(" ");
                else sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == 0 || dp[i][j] == INF) sb.append(0);
                else {
                    sb.append(routes[i][j].size() + 2).append(" ");
                    sb.append(i).append(" ");
                    for (Integer node : routes[i][j]) {
                        sb.append(node).append(" ");
                    }
                    sb.append(j).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}

public class Main
{
    public static void main(String[] args) throws IOException {
        Solution_11780 s = new Solution_11780();
        s.solution();
    }
}

/**
 * 골드 2 11780번 플로이드 2
 *
 * 그래프의 모든 노드 쌍의 최단 거리 구하기 & 노드 개수 적음 -> 플로이드 알고리즘
 * i -> j 로 가는 최단 거리 & 그 경로도 알아야 함
 */
