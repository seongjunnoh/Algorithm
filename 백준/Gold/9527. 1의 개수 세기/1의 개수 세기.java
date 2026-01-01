import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        
        long[] dp = new long[60];    // dp[i] : 1 ~ 2의 i제곱-1 까지의 누적합
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2; i<60; i++) {
            dp[i] = dp[i-1] * 2 + (1L << i-1);
        }
        
        System.out.println(calc(dp, b) - calc(dp, a-1));
        br.close();
    }
    
    long calc(long[] dp, long x) {     // 1 ~ x 까지의 f 누적합(= s(x)) 계산
        if (x == 0) return 0;   // s(0) = 0
        
        int t = 0;
        while ((1L << t) - 1 < x) t++;
        
        return dp[t-1] + (x - (1L << t-1) + 1) + calc(dp, x - (1L << t-1));
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 1 : 1
 * 2 : 1, 3 : 2,
 * 4 : 1, 5 : 2, 6 : 2, 7 : 3
 * 8 : 1, 9 : 2, 10 : 2, 11 : 3, 12 : 2, 13 : 3, 14 : 3, 15 : 4
 *
 * s(10) = f(1) ~ f(10) 합 = dp[3] + (8, 9, 10) 의 1 개수
 * f(8) + f(9) + f(10) = 3 + (f(0) + f(1) + f(2)) = 3 + s(2)
 * 
 * 10의 16제곱 < 10의 3제곱 의 6제곱 < 2의 60제곱
 */