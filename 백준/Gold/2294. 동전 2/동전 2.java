import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_2294 {

    int INF = 987654321;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin);

        int[] dp = new int[k + 1];
        Arrays.fill(dp, INF);    // INF로 초기화
        for (int i = 0; i < n; i++) {           // coin 가치가 작은 것부터
            for (int val = 1; val <= k; val++) {
                if (val == coin[i]) dp[val] = 1;
                else if (val > coin[i]) dp[val] = Math.min(dp[val], dp[val - coin[i]] + 1);
            }
        }

        if (dp[k] == INF) System.out.println("-1");
        else System.out.println(dp[k]);
        br.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_2294 s = new Solution_2294();
        s.solution();
    }
}

/**
 * 골드 5 2294번 동전 2
 *
 * 가치의 합이 k가 되는 최소의 동전 개수 구하기
 * 가치가 큰 동전들부터 그리디하게 생각 ??
 * -> no. 결국 모든 경우의 수를 탐색해야하는데 이 경우 시간 초과 ??
 *
 * dp로 메모이제이션 -> 시간초과 해결하자
 *
 * dp[i] : 가치의 합이 i가 되는 최소의 동전 개수 -> 정답 = dp[k]
 * 현재 동전 가치를 뺀 나머지 가치의 dp값 = 나머지 가치를 위한 최소 동전 개수
 *
 */
