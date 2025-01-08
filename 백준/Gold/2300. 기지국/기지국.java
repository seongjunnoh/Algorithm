import java.io.*;
import java.util.*;
import java.lang.*;

class Pair_2300 {
    int x;
    int y;

    Pair_2300(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_2300 {

    Pair_2300[] pairs;
    long[] dp;
    final int INF = Integer.MAX_VALUE;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        pairs = new Pair_2300[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair_2300(x, y);
        }

        Arrays.sort(pairs, new Comparator<>() {
            @Override
            public int compare(Pair_2300 p1, Pair_2300 p2) {
                return p1.x - p2.x;
            }
        });

        dp = new long[n];
        for (int i = 0; i < n; i++) dp[i] = INF;        // 초기화
        dp[0] = Math.abs(pairs[0].y) * 2L;
        for (int i = 1; i < n; i++) {
            Pair_2300 cur = pairs[i];
            int maxH = 0;           // cur 건물과 같은 정사각형에 들어갈 건물들의 높이 중 최대값
            for (int j = i; j >= 0; j--) {
                int xLen = cur.x - pairs[j].x;
                maxH = Math.max(maxH, Math.abs(pairs[j].y));

                if (j > 0) dp[i] = Math.min(dp[i], dp[j - 1] + Math.max(xLen, maxH * 2));
                else dp[i] = Math.min(dp[i], Math.max(xLen, maxH * 2));
            }
        }

        System.out.println(dp[n - 1]);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2300 s = new Solution_2300();
        s.solution();
    }
}

/**
 * 골드 2 2300번 기지국
 *
 * x좌표 기준 오름차순 정렬
 * -> i번째 건물을 기존 사각형이 포함시킬 것인지, 새로 사각형을 만들것인지
 * -> 기존 사각형에 포함시킨다면 기존 사각형들 중 어디까지 포함할지
 * -> dp
 *
 * dp[i] = i번째 사각형까지 고려할 때의 최적해
 * dp[i] 구하기 위해서 n번의 비교 연산 필요 -> 총 n 제곱 -> 1억 -> ok
 */
