import java.io.*;

class Solution_1309 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        
        if (n == 1) {       // 예외처리
            System.out.println(3);
            br.close();
            return;
        }
        
        dp[1] = 3;
        dp[2] = 7;
        for (int i = 3; i <= n; i++) {
            dp[i] = (((2 * dp[i - 1]) % 9901) + (dp[i - 2] % 9901)) % 9901;
        }

        System.out.println(dp[n]);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1309 s = new Solution_1309();
        s.solution();
    }
}

/**
 * 사자는 0마리 ~ n 마리 까지 배치 가능
 *
 * dp 점화식이 왜 저렇게 나오는지 ??
 */