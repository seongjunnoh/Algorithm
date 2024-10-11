import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][10];

        // dp 초기화
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        if (n == 1) {
            System.out.println("10");
            br.close();
            return;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                int sum = 0;
                for (int k = j; k < 10; k++) {
                    sum += dp[i - 1][k];
                }
                dp[i][j] = sum % 10007;
            }
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i];
        }
        System.out.println(result % 10007);
        br.close();
    }
}

/**
 * 실버 1 11057 오르막 수
 *
 * 길이가 n인 오르막 수의 개수 구하기
 * -> 이전 자리수의 값이 j 이면, 현재 수는 10-j 가지의 경우가 가능함
 *
 * dp[i][j] : 길이가 i, 제일 앞자리가 j(0~9)로 시작 할 때의 오르막 수
 * => 정답 = dp[n][] 의 합
 *
 * dp[i][0] = dp[i-1][0] + dp[i-1][1] + ,,, + dp[i-1][9]
 * dp[i][1] = dp[i-1][1] + dp[i-1][2] + ,,, + dp[i-1][9]
 * dp[i][9] = dp[i-1][9]
 *
 */
