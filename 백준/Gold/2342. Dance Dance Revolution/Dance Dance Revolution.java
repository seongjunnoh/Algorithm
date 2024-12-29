import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_2342 {

    List<Integer> command = new ArrayList<>();
    int[][][] dp;
    int INF = Integer.MAX_VALUE;            // dp 초기화

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos == 0) break;

            command.add(pos);
        }

        dp = new int[command.size() + 1][5][5];
        for (int i = 0; i <= command.size(); i++) {
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    dp[i][l][r] = INF;          // dp 초기화
                }
            }
        }

        dp[0][0][0] = 0;        // 시작 지점

        for (int i = 1; i <= command.size(); i++) {
            int pos = command.get(i - 1);           // 이번에 밟아야 할 발판

            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if (dp[i - 1][l][r] == INF) continue;

                    // 이전단계에서의 위치 : (l, r)
                    // 왼발 이동
                    dp[i][pos][r] = Math.min(dp[i][pos][r], dp[i - 1][l][r] + power(l, pos));

                    // 오른발 이동
                    dp[i][l][pos] = Math.min(dp[i][l][pos], dp[i - 1][l][r] + power(r, pos));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                min = Math.min(min, dp[command.size()][l][r]);
            }
        }
        System.out.println(min);
        br.close();
    }

    int power(int start, int end) {
        if (start == 0) return 2;
        if (start == end) return 1;
        if (Math.abs(start - end) == 2) return 4;
        return 3;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_2342 s = new Solution_2342();
        s.solution();
    }
}

/**
 * 골드 3 2342번 Dance Dance Revolution
 *
 * 직전 위치에서 가장 적은 힘을 들이고 다음위치로 이동 & 이걸 계속 반복 -> 그리디
 *
 * 현재의 최선이 전체적으로 봤을때도 최선이 맞는듯?? -> NO
 * -> 반레 : 1 2 3 2 0 -> 정답 = (1,0),(2,0),(2,3),(2,3) = 2+3+2+1 = 8
 * => 현재는 왼발을 이동하는게 최소이지만, 전체적으로는 오른발을 이동하는게 최소일 수 있음 => 그리디하게 접근하면 X (모든 경우를 따져봐야 함)
 * ------------------------------------------------------------------
 * dp[i][l][r] : i번째 발판 누를때, 왼발이 l, 오른발이 r 있을 때의 최소 누적 비용
 * -> dp[i][l][r] = min(dp[i-1][이전 l][r] + 왼발 이동, dp[i-1][l][이전 r] + 오른발 이동)
 * -> 매번 이동할 때마다 지사 사항의 방향으로 이동
 * -> 정답 = dp[n] 중 최소값
 */
