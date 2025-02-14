import java.io.*;

class Solution_2133 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[31];         // dp[i] : 3*i 를 타일로 채우는 경우의 수
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) continue;

            int q = i / 2;
            for (int j = 0; j < q - 1; j++) {
                dp[2 * q] += 2 * dp[2 * j];
            }
            dp[2 * q] += dp[2] * dp[2 * (q - 1)];
        }

        System.out.println(dp[n]);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2133 s = new Solution_2133();
        s.solution();
    }
}

/**
 * n이 홀수 -> 0
 * n=2 : 3
 * n=4 : 3*3 + 2(= 4일때만 만들 수 있는 모양) = 11
 * n=6 : 2&4, 4&2, 6고유 -> dp[2]*dp[4] + 2*dp[2] + 2 = 41
 * n=8 : 2&6, 6&2, 4&4, 8고유 -> dp[2]*dp[6] + 2*dp[2] + 2*dp[4] + 2 = 153
 *
 * -> dp[2*n] = 2 + 2*dp[2*1] + 2*dp[2*2] + ,,, + 2*dp[2*(n-2)] + dp[2]*dp[2*(n-1)]
 *
 */
