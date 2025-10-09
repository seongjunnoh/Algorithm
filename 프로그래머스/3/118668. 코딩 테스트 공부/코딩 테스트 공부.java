import java.util.*;

class Solution {
    int INF = 1_000_000_000;
    
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        for (int i=0; i<problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i=0; i<=maxAlp; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        // 예외처리 -> 초기 alp, cop 가 이미 더 클 경우
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);    
        
        dp[alp][cop] = 0;
        
        for (int a=alp; a<=maxAlp; a++) {
            for (int c=cop; c<=maxCop; c++) {
                int cur = dp[a][c];
                if (cur == INF) continue;
                
                // 알고리즘 공부
                if (a+1 <= maxAlp) {
                    dp[a+1][c] = Math.min(dp[a+1][c], cur + 1);
                }
                
                // 코딩 공부
                if (c+1 <= maxCop) {
                    dp[a][c+1] = Math.min(dp[a][c+1], cur + 1);
                }
                
                // 문제풀기
                for (int[] p : problems) {
                    int reqA = p[0], reqC = p[1];
                    int rwdA = p[2], rwdC = p[3];
                    int cost = p[4];
                    
                    if (a >= reqA && c >= reqC) {   // 현재 문제 풀 수 있음
                        int nA = Math.min(maxAlp, a + rwdA);
                        int nC = Math.min(maxCop, c + rwdC);
                        dp[nA][nC] = Math.min(dp[nA][nC], cur + cost);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}

/**
 * 그리디 X, DP O
 * dp[i][j] : alp=i, cop=j 에 도달하는데 걸리는 최단 시간
 */