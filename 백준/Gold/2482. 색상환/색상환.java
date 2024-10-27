import java.io.*;

public class Main {

    static final long mod = 1000000003;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        dp = new long[n + 1][k + 1];        // dp[i][j] : i 개중 j 개 선택

        // dp 초기화 (0개 중 j개 선택 = 0)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;       // 0개 선택 -> 1
            if (i >= 1) dp[i][1] = i;       // 1개 선택 -> i
        }

        // dp update
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                if (i >= j * 2 - 1) {           // i개의 선형배열 중 인접하지 않는 j개를 선택하기 위한 조건
                    dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
                }
            }
        }

        long result = 0;
        if (n >= k * 2) {
            long case1 = dp[n - 3][k - 1];          // 첫 번째 색상을 선택하는 경우
            long case2 = dp[n - 1][k];              // 첫 번째 색상을 선택하지 않는 경우
            result = (case1 + case2) % mod;
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 3 2482 색상환
 *
 * 원형 -> 선형으로 생각
 *  => 첫번째를 선택하는 경우 vs 선택하지 않는 경우로 나눠서 생각
 *
 * dp[i][j] = i번째를 선택 & i-2 개중 j-1개 선택 + i번째 선택 x & i-1 개 중 j개 선택
 *          = dp[i-2][j-1] + dp[i-1][j]
 *
 */
