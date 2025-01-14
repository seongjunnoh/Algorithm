import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_13141 {

    final int INF = 2_000_001;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][n + 1];         // 두 노드 사이의 최단 거리
        for (int i = 1; i <= n; i++) {              // dp 초기화
            for (int j = 1; j <= n; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = INF;
            }
        }

        int[][] longest = new int[n + 1][n + 1];        // 두 노드 사이의 최장 거리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dp[s][e] = Math.min(dp[s][e], l);
            dp[e][s] = Math.min(dp[e][s], l);
            longest[s][e] = Math.max(longest[s][e], l);
            longest[e][s] = Math.max(longest[e][s], l);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        double result = INF;        // 정답
        for (int start = 1; start <= n; start++) {
            double current = 0;     // start 정해졌을 때의 모든 그래프가 타는 시간
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    int xT = dp[start][x];          // x 노드에 불이 붙는 시각
                    int yT = dp[start][y];          // y 노드에 불이 붙는 시각

                    if (longest[x][y] == 0) {       // x, y가 같은 노드 & x <-> y 인 에지 없음
                        current = Math.max(current, xT);
                        continue;
                    }

                    double startT = Math.min(xT, yT);          // 에지가 불타기 시작하는 시각
                    double firstBurnT = Math.abs(xT - yT);     // 에지 중 한쪽만 타는 시간
                    double secondBurnT = (longest[x][y] - firstBurnT) / 2;          // 에지의 양쪽이 모두 타는 시간 -> 무조건 firstBurnT <= longest[x][y] 이므로 secondBurnT >= 0 이다
                    double totalBurnT = startT + firstBurnT + secondBurnT;

                    current = Math.max(current, totalBurnT);
                }
            }

            result = Math.min(result, current);
        }

        System.out.printf("%.1f", result);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_13141 s = new Solution_13141();
        s.solution();
    }
}

/**
 * 플래티넘 5 13141번 Ignition
 *
 * 그래프의 모든 노드, 모든 에지가 전부 타야됨 & 에지의 시작점, 끝점이 같을 수 있음 (양방향 그래프)
 * 노드에 불이 붙으면 바로 노드와 연결된 에지에 불이 전달
 * -> 어떤 노드에 불을 붙여야 그래프가 모두 타는 시간이 최소가 되는가?
 *
 * 노드에 불이 붙어야 인접한 간선도 불이 붙음
 * -> 일단 모든 노드들에 최대한 빨리 불 붙이기(= 모든 노드사이의 최단 거리 구하기 = 플로이드 알고리즘)
 *    & 출발 노드 정하기
 *    & 모든 노드에 대해서 자신과 인접한 모든 간선이 불타는 시간 구하기
 *      = 모든 노드쌍 중 거리가 최대인 에지가 불타는 시간들 중 최대값
 *
 * ---------------------------------------------------
 * 출발 노드를 미리 정할 수 없다 -> 간선들도 같이 고려해야하기 때문
 * 일단 플로이드 알고리즘으로 모든 노드들 간의 최단거리를 구한 다음, 모든 정점에서 출발했을 때의 그래프가 모두 타는 시간 비교
 */
