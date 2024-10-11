import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[1000001];

        dp[1] = 1;          // 1
        dp[2] = 2;          // 1+1, 2
        dp[3] = 4;          // 1+1+1, 1+2, 2+1, 3

        for (int i = 4; i <= 1000000; i++) {
            // dp[i] = i-1 표현 + 1 , i-2 표현 + 2 , i-3 표현 + 3
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
        br.close();
    }
}

/**
 * 실버 2 15988 1,2,3 더하기 3
 *
 * dp[i] : i를 1,2,3 의 합으로 나타내는 방법의 수를 나눈 수
 *
 */
