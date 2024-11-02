import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());     // num은 1~n 까지의 수

            dp[num] = dp[num - 1] + 1;
            max = Math.max(max, dp[num]);
        }

        System.out.println(n - max);
        br.close();
    }
}

/**
 * 골드 2 7570 줄 세우기
 *
 * line의 연속된 가장 긴 증가하는 부분 수열의 길이를 찾아야 함 => 정답 = n - max 길이
 * -> dp[i] : 숫자 i 까지 고려했을 때, 연속된 가장 긴 증가하는 부분 수열의 길이
 *      => dp[i] = dp[i-1] + 1 (i-1과 i 는 연속된 수 이므로)
 *
 */
