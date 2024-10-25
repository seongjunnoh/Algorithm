import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];       // 1 -> 상근 승, 0 -> 창영 승
        dp[1] = 1;
        dp[2] = 0;
        dp[3] = 1;
        dp[4] = 1;

        for (int i = 5; i <= 1000; i++) {
            if (dp[i - 1] == 0 || dp[i - 3] == 0 || dp[i - 4] == 0) dp[i] = 1;
            else dp[i] = 0;
        }

        if (dp[n] == 1) System.out.println("SK");
        else System.out.println("CY");
        br.close();
    }
}

/**
 * 실버 3 9657 돌 게임 3
 *
 * 돌은 1,3,4 개 한번에 가져갈 수 있음
 * 마지막에 돌 가져가는 사람이 이김
 *
 *
 * 1 -> sk
 * 2 -> cy
 * 3 -> sk
 * 4 -> sk
 * 5 -> sk (3, 1, 1)
 * 6 -> sk (4, 1, 1)
 * 7 -> cy (1, 6 | 3, 4 | 4, 3)
 *
 * => dp[i] : i-1, i-3, i-4 일 때 dp 값이 0 인게 있으면 => 1 , 없으면 => 0
 * (dp 값이 0 이다 == 해당 개수가 남았을때 상대방이 이긴다)
 */
