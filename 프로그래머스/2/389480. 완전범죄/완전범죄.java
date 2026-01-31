import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[info.length][m];
        for (int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], INF);     // dp 초기화
        }
        
        for (int i=0; i<info.length; i++) {
            int a = info[i][0];     // a 흔적 개수
            int b = info[i][1];     // b 흔적 개수
            
            if (i == 0) {       // 첫번째 물건 따로 처리
                if (a < n) dp[i][0] = a;    // a가 훔친다
                if (b < m) dp[i][b] = 0;    // b가 훔친다
                continue;
            }
            
            for (int j=0; j<m; j++) {
                int before = dp[i-1][j];    // i-1 물건까지, b 누적 흔적 개수가 j-1 일때 a 누적 흔적 개수 최솟값
                if (before == INF) continue;
                
                if (a + before < n) dp[i][j] = Math.min(dp[i][j], a + before);  // a가 훔친다
                if (b + j < m) dp[i][b + j] = Math.min(dp[i][b + j], before);       // b가 훔친다
            }
        }
        
        int min = Integer.MAX_VALUE;    // a 최솟값
        for (int j=0; j<m; j++) {
            int val = dp[info.length-1][j];
            if (val == INF) continue;
            
            min = Math.min(min, val);
        }
        
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<m; j++) {
                System.out.print(dp[i][j] + " | ");
            }
            System.out.println();
        }
        
        if (min == INF) return -1;      // 가능한 경우 없음
        return min;
    }
}

/**
 * 물건 -> a or b 가 훔쳐야한다
 * 모든 경우의 수 : 2의 40제곱 -> 시간초과
 * 
 * dp[i][j] : i번째 물건까지 확인, b 누적 흔적 개수가 j일때의 a 누적 흔적 개수 최솟값
 */