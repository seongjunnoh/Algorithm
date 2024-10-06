import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[101][10];     // dp[i][j] : 길이가 i인 계단 수 중 j로 시작하는 계단 수의 개수

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1] % 1000000000;
                }
                else if (j == 9) {
                    dp[i][j] = dp[i - 1][8] % 1000000000;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long count = 0;
        for (int i = 1; i < 10; i++) {
            count += dp[n][i];
        }
        System.out.println(count % 1000000000);
        br.close();
    }
}

/**
 * 실버 1 10844 쉬운 계단 수
 *
 * 길이가 n인 계단수 개수 구하기
 *
 * 헷갈리니 제일 처음 계단 수를 0~9까지 모두 고려
 * -> 나중에 0으로 시작하는 수 빼주기
 *
 * 현재단계에서 0으로 시작하는 계단 수 -> 이전단계에서 1로 시작할 수 밖에 없음
 * 현재단계에서 9로 시작하는 계단 수 -> 이전단게에서 8로 시작할 수 밖에 없음
 * 나머지(현재단계에서 k로 시작하는 계단 수) -> 이전단게에서 k-1, k+1로 시작하는 계단 수의 개수의 합
 */
