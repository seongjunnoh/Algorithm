import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        int cnt = 1;
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[cnt];
            cnt++;
        }

        int[] input = new int[t];
        for (int i = 0; i < t; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < t; i++) {
            System.out.println(dp[input[i]]);
        }

        br.close();
    }
}

/**
 * 실버 3 9461 파도반 수열
 *
 * 1,1,1,2,2, 2+1, 3+1, 4+1, 5+2, 7+2, 9+3, 12+4, 16+5, 21+7, 28+9, 37+
 */
