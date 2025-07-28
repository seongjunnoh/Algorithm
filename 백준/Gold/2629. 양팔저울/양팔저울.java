import java.io.*;
import java.util.*;

class Solution_2629 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());        // 추는 30개 이하
        StringTokenizer weights = new StringTokenizer(br.readLine());
        int[] w = new int[n + 1];       // 추의 무게가 가벼운 순
        int sum = 0;        // 모든 추의 합
        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(weights.nextToken());
            sum += w[i];
        }

        boolean[][] dp = new boolean[n + 1][sum + 1];       // 양쪽의 무게차이는 최대 sum만큼
        dp[0][0] = true;        // 추를 하나도 사용하지 않은 경우
        for (int i = 1; i <= n; i++) {
            int currentW = w[i];        // 현재 추의 무게
            for (int d = 0; d <= sum; d++) {
                if (!dp[i - 1][d]) continue;        // 이전 상태에서 양쪽의 무게차이가 d가 아니면 패스

                dp[i][d] = true;        // 현재 추를 어느곳에도 놓지 않을 경우
                dp[i][d + currentW] = true;     // 현재 추를 무거운 쪽에 놓은 경우
                dp[i][Math.abs(d - currentW)] = true;       // 현재 추를 가벼운 쪽에 놓은 경우
            }
        }

        int m = Integer.parseInt(br.readLine());        // 구슬은 7개 이하
        StringTokenizer targets = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(targets.nextToken());

            if (target > sum) {     // 예외처리
                sb.append("N").append(" ");
                continue;
            }

            if (dp[n][target]) sb.append("Y").append(" ");
            else sb.append("N").append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2629 s = new Solution_2629();
        s.solution();
    }
}

/**
 * 추는 왼, 오, 놓지 않음 이라는 3가지 상태가 존재 -> 추를 놓는 모든 경우의 수는 3의 30제곱
 *
 * 왼쪽과 오른쪽의 무게차이가 구슬과 같은 경우가 존재한다면, 해당 구슬은 Y 아니면 N
 * -> dp[i][j] = true/false : i번째 추까지 사용했을때, 양쪽 무게차이가 j이면 true, 아니면 false
 * -> dp[n][구슬] = true 이면 Y, 아니면 N
 */
