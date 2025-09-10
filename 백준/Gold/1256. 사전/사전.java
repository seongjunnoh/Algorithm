import java.io.*;
import java.util.*;

class Solution {
    final long INF = 1_000_000_000;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        
        // a, z 를 조합하여 만들 수 있는 문자열 개수 구하기
        long[][] dp = new long[n+1][m+1];
        dp[0][0] = 0;
        for (int i=1; i<=n; i++) {
            dp[i][0] = 1;
        }
        for (int j=1; j<=m; j++) {
            dp[0][j] = 1;
        }   // dp 초기화
        
        for (int i=1; i<=n; i++) {  // dp 배열 채우기
            for (int j=1; j<=m; j++) {
                dp[i][j] = Math.min(INF, dp[i-1][j] + dp[i][j-1]);  // 오버플로우 대비
            }
        }
        
        if (dp[n][m] < k) { // 예외
            System.out.println("-1");
            return;
        }
        
        // k번째 문자열 구하기 (n+m 길이의 문자열)
        StringBuilder sb = new StringBuilder();
        long curRank = 1;
        int remainA = n;
        int remainZ = m;
        for (int l=1; l<=n+m; l++) {
            // 예외처리 -> 이미 문자열이 특정됨
            if (remainA == 0) { // 계속 z append
                sb.append("z");
                remainZ--;
                continue;
            }
            
            if (remainZ == 0) { // 계속 a append
                sb.append("a");
                remainA--;
                continue;
            }
            
            long countA = dp[remainA - 1][remainZ]; // 현재자리에 a를 선택시 문자열의 수
            if (countA + curRank <= k) {   // z append
                sb.append("z");
                curRank += countA;
                remainZ--;
            } else {    // a append
                sb.append("a");
                remainA--;
            }
        }
        
        System.out.println(sb);
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
 * 백트래킹으로 모든 문자열을 사전순으로 탐색 -> 시간 초과
 * 
 * n+m 중 1번째 자리를 'a'로 고정 -> 나머지 : n+m-1 choose n-1
 * -> 1번째 자리가 'a' 인 문자열은 1 ~ n+m-1 choose n-1 까지의 rank 가짐
 * -> 1번째 자리가 'z' 인 문자열은 n+m-1 choose n-1 + 1 부터의 rank 가짐
 */
 
 