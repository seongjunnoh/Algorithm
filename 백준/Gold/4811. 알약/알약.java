import java.io.*;
import java.util.*;

class Solution_4811 {

    long[][] dp;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            dp = new long[n + 1][n + 1];        // 메모이제이션
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1L);        // 초기화
            }
            sb.append(back(n, 0)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    long back(int w, int h) {       // 큰 알약 w개, 작은 알약 h개 있을 때 가능한 경우의 수 반환
        if (w == 0) {
            dp[w][h] = 1L;
            return 1L;
        }
        if (dp[w][h] != -1) return dp[w][h];        // 메모이제이션

        long result = 0L;

        result += back(w - 1, h + 1);           // 큰 알약 먹는 상황 고려
        if (h > 0) result += back(w, h - 1);       // 작은 알약 먹는 상황 고려

        dp[w][h] = result;
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_4811 s = new Solution_4811();
        s.solution();
    }
}

/**
 * w, h 로 구성된 가능한 서로 다른 문자열의 개수 구하기
 * 백트래킹 -> 시간 초과 (n이 30일 때 예시 답안 너무 크다)
 * -> 메모이제이션 필요 -> dp 배열 도입
 *
 */