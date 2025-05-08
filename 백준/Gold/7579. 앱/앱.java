import java.io.*;
import java.util.*;

class Solution_7579 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        int[] cost = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][10001];
        for (int costSum = 0; costSum <= 10000; costSum++) {        // dp 초기화
            if (costSum < cost[0]) continue;
            dp[0][costSum] = memory[0];
        }

        for (int num = 1; num < n; num++) {
            for (int costSum = 0; costSum <= 10000; costSum++) {
                int curMem = memory[num];       // num번째 App의 memory
                int curCost = cost[num];        // num번째 App의 cost
                if (costSum >= curCost) {
                    // num번째 App을 비활성화 하던지 말던지
                    dp[num][costSum] = Math.max(dp[num - 1][costSum], dp[num - 1][costSum - curCost] + curMem);
                } else {
                    // num번째 App을 비활성화 할 수 없음
                    dp[num][costSum] = dp[num - 1][costSum];
                }
            }
        }
        
        int minCost = 10000;
        for (int costSum = 0; costSum <= 10000; costSum++) {
            if (dp[n-1][costSum] >= m) {
                minCost = costSum;
                break;
            }
        }

        System.out.println(minCost);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_7579 s = new Solution_7579();
        s.solution();
    }
}

/**
 * 모든 경우의 수 전부 고려해서 최소비용인 경우가 정답
 * -> 백트래킹
 * ------------------------------------------------------------------
 * 이론상 2의 100제곱만큼의 경우를 탐색해야하므로 시간초과 발생
 * (아무리 백트래킹으로 중간에 가지치기를 진행한다해도 n=100 일때는 완전탐색을 하는게 아닌듯)
 *
 * sol = DP (배낭 알고리즘)
 * dp[i][j] : i번째 App 까지 고려 & 비용 j 범위에서 얻을 수 있는 최대 메모리 크기
 * (i : 0~100, j : 0~10000 이므로 메모리 초과 X)
 * -> dp[n] 값들 중 m 이상인 값들 찾기 & 그 중 최소인 j값이 정답
 *
 */
