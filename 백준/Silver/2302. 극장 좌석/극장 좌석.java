import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        boolean[] isFix = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int idx = Integer.parseInt(br.readLine());
            isFix[idx] = true;
        }

        dp[1] = 1;
        if (n == 1) {       // 예외처리
            System.out.println(dp[1]);
            br.close();
            return;
        }
        
        if (isFix[1] || isFix[2]) dp[2] = 1;
        else dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            if(isFix[i] || isFix[i-1]) dp[i] = dp[i - 1];
            else dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
        br.close();
    }
}

/**
 * 실버 1 2302 극장 좌석
 *
 * 고정석을 제외하고, 양옆으로 자리바꿀수 있음 -> 이때 가능한 자리배치의 경우의 수 구하기
 *
 * dp[i] : 좌석 i개 일때 가능한 자리 배치 경우의 수
 * -> dp[i] = 좌석 i-1개 있을때 & i번째 사람 자기 자리에 착석 + 좌석 i-2개 있을때 & i-1 <-> i 자리 체인지
 * => dp[i] = dp[i-1] + dp[i-2] (여기서 고정석인 상황을 추가로 고려)
 */
