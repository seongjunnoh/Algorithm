import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            System.out.println("0");
            System.out.println("0");
            br.close();
            return;
        }

        int flag = 0;
        if (n > 0) flag = 1;
        else {
            if (Math.abs(n) % 2 == 0) flag = -1;
            else flag = 1;
        }

        long[] dp = new long[Math.abs(n) + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= Math.abs(n); i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000000;
        }
        System.out.println(flag);
        System.out.println(dp[Math.abs(n)] % 1000000000);
        br.close();
    }
}

/**
 * 실버 3 1788 피보나치 수의 확장
 *
 * 1 0 -1 -2 -3 -4 -5 -6 -7
 * 1 0 1  -1 2  -3  5 -8 13
 */
