import java.io.*;
import java.util.*;

class Solution_1495 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] vol = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            vol[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][s] = true;        // s는 시작 볼륨

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j]) {         // i번째 곡을 연주한 후 볼륨이 j인 경우
                    if (j + vol[i + 1] <= m) dp[i + 1][j + vol[i + 1]] = true;
                    if (j - vol[i + 1] >= 0) dp[i + 1][j - vol[i + 1]] = true;
                }
            }
        }

        for (int j = m; j >= 0; j--) {
            if(dp[n][j]) {
                System.out.println(j);
                br.close();
                return;
            }
        }

        System.out.println(-1);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1495 s = new Solution_1495();
        s.solution();
    }
}

/**
 * 전체 가능한 경우 -> 2의 50제곱 -> 시간초과
 *
 * ??
 * dp[i][j] : i번째 곡을 연주한 후 볼륨이 j가 될 수 있는지 -> true : ok, false : no
 * -> dp[n][j]를 모두 탐색하면서 dp값이 true인 j 중 최대값이 정답이다
 * -> 모든 dp[n][j] 가 false 라면 -1이 정답
 *
 */
