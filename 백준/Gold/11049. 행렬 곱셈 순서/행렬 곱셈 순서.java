import java.io.*;
import java.util.*;

class Pair_11049 {
    int r;
    int c;

    Pair_11049(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution_11049 {

    Pair_11049[] arr;
    int[][] dp;
    final int INF = Integer.MAX_VALUE;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new Pair_11049[n + 1];       // 입력 행렬들
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair_11049(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        if (n == 1) {       // 예외처리
            System.out.println("1");
            br.close();
            return;
        }

        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        System.out.println(play(1, n));
        br.close();
    }

    int play(int i, int j) {
        if (i == j) return 0;       // 같은 번호의 행렬 곱 연산 횟수 -> 0 리턴

        if (dp[i][j] < INF) return dp[i][j];

        if (i + 1 == j) {
            dp[i][j] = arr[i].r * arr[i].c * arr[j].c;
            return dp[i][j];
        }

        for (int mid = i; mid < j; mid++) {
            dp[i][j] = Math.min(dp[i][j], play(i, mid) + play(mid + 1, j) + arr[i].r * arr[mid].c * arr[j].c);
        }
        return dp[i][j];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_11049 s = new Solution_11049();
        s.solution();
    }
}

/**
 * 행렬 n개를 모두 곱하는데 필요한 곱셈 연산 횟수의 최소값 구하기
 * 가능한 모든 경우의 수 = 500 팩토리얼 -> 시간 초과
 * -> dp
 *
 * dp[i][j] : i ~ j 번째 행렬의 곱에 필요한 연산 횟수의 최소값 -> dp[1][n]이 정답
 */