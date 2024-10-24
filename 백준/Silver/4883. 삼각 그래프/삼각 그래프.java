import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int count = 1;

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            sb.append(count + ". ");

            int[][] map = new int[n][3];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][3];
            dp[0][0] = Integer.MAX_VALUE;
            dp[0][1] = map[0][1];
            dp[0][2] = dp[0][1] + map[0][2];
//            System.out.print(dp[0][0] + " || " + dp[0][1] + " || " + dp[0][2] + " || ");
//            System.out.println();

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][0];
                dp[i][1] = Math.min(dp[i][0], Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2]))) + map[i][1];
                dp[i][2] = Math.min(dp[i][1], Math.min(dp[i - 1][1], dp[i - 1][2])) + map[i][2];

//                System.out.print(dp[i][0] + " || " + dp[i][1] + " || " + dp[i][2] + " || ");
//                System.out.println();
            }

            sb.append(dp[n - 1][1]).append("\n");
            count++;
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 1 4883 삼각 그래프
 *
 * dp[i][j] : 0,1 -> i,j 까지의 총 비용
 *
 */
