import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_11404 {

    final int INF = 10000001;       // 모든 비용이 최대이고, 모든 도시를 거치는 경로보다 큰 값

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][n + 1];     // dp[i][j] : i에서 j까지의 최단 거리
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) dp[i][j] = 0;       // 자기 자신으로 가는 비용은 0
                else dp[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[a][b] = Math.min(dp[a][b], c);           // 단방향 그래프
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
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

        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_11404 s = new Solution_11404();
        s.solution();
    }
}

/**
 * 골드 4 11404번 플로이드
 *
 * 노드 n개, 에지 m개 -> 모든 노드의 쌍 (a, b)에 대해서 a->b 의 최소값 구하기
 * 플로이드 와샬 알고리즘
 * -> dp[i][i] = 0으로 설정해줘야함 & INF는 오버플로우 발생하지 않을 정도의 큰 값으로 설정
 */
