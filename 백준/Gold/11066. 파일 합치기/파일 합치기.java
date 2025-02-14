import java.io.*;
import java.util.*;

class Solution_11066 {

    int[] sum;
    long[][] dp;
    final long INF = 25_000_000_000_000L;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());

            sum = new int[k + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= k; j++) {
                int curSize = Integer.parseInt(st.nextToken());
                sum[j] = sum[j-1] + curSize;         // 누적합
            }

            dp = new long[k + 1][k + 1];
            for (int from = 1; from <= k; from++) {         // dp 초기화
                for (int to = from; to <= k; to++) {
                    if (from == to) dp[from][to] = 0;
                    else if (from + 1 == to) dp[from][to] = sum[to] - sum[from - 1];
                    else dp[from][to] = INF;
                }
            }

            sb.append(play(1, k)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    long play(int from, int to) {
        if (dp[from][to] != INF) return dp[from][to];

        for (int mid = from; mid < to; mid++) {
            dp[from][to] = Math.min(dp[from][to], play(from, mid) + play(mid + 1, to) + sum[to] - sum[from - 1]);
        }

        return dp[from][to];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_11066 s = new Solution_11066();
        s.solution();
    }
}

/**
 * 모든 경우의 수 : n! -> 시간초과 (n은 최대 500)
 *
 * dp[i][j] : i부터 j파일 까지 합칠 때 필요한 최소비용
 * -> dp[1][k] = dp[1][1] + dp[2][k] + (1~k까지 누적합), dp[1][2] + dp[3][k] + (1~k까지 누적합), ,,, 중 최소값
 *
 */