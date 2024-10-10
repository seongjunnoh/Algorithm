import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        if (n == 1) {
            System.out.println(arr[0]);
            br.close();
            return;
        }
        if (n == 2) {
            System.out.println(arr[0] + arr[1]);
            br.close();
            return;
        }
        if (n == 3) {
            int max = Math.max(arr[0] + arr[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));
            System.out.println(max);
            br.close();
            return;
        }

        dp[0] = arr[0];
        dp[1] = dp[0] + arr[1];
        dp[2] = Math.max(arr[0] + arr[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));
        for (int i = 3; i < n; i++) {
            // i번째 선택하지 않거나,
            // 선택하는 경우는 -> dp[i-2] + arr[i] vs dp[i-3] + arr[i-1] + arr[i]
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
        }

        System.out.println(dp[n - 1]);
        br.close();
    }
}

/**
 * 실버 1 2156 포도주 시식
 *
 * 연속으로 놓여 있는 3개는 모두 선택할 수 없음
 *
 * dp[i] : i번째 포도주까지 고려할 경우, 최대로 마실 수 있는 포도주 양
 * -> 정답 = dp[n-1]
 *
 */
