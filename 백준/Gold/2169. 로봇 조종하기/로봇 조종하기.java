import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); 
        
        int[][] map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] dp = new int[n][m]; // dp[i][j] : i,j 에서의 최대 가치합
        dp[0][0] = map[0][0];
        for (int j=1; j<m; j++) {
            dp[0][j] = dp[0][j-1] + map[0][j];  // 0행 초기화
        }
        
        for (int i=1; i<n; i++) {
            int[] left = new int[m];
            left[0] = dp[i-1][0] + map[i][0];
            for (int j=1; j<m; j++) {
                left[j] = Math.max(left[j-1], dp[i-1][j]) + map[i][j];
            }
            
            int[] right = new int[m];
            right[m-1] = dp[i-1][m-1] + map[i][m-1];
            for (int j=m-2; j>=0; j--) {
                right[j] = Math.max(right[j+1], dp[i-1][j]) + map[i][j];
            }
            
            for (int j=0; j<m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
        
        System.out.println(dp[n-1][m-1]);
        br.close();
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 백트래킹으로 모든 경우 커버 -> 시간 초과
 * 
 * i, j 로 들어오는 경로 : 위, 왼, 오
 * -> 3가지 방향으로 들어오는 경로 중 최대값을 계속 메모이제이션
 */
 
 