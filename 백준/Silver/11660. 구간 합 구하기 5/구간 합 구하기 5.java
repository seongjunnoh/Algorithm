import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (j == 0) dp[i][j] = map[i][j];
                else dp[i][j] = dp[i][j - 1] + map[i][j];
            }
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Pair start = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            Pair end = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

            int sum = 0;
            for (int row = start.x; row <= end.x; row++) {
                if (start.y == 0) sum += dp[row][end.y];
                else sum += dp[row][end.y] - dp[row][start.y - 1];
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버1 11660 구간 합 구하기 5
 *
 *
 */
