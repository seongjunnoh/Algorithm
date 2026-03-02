import java.util.*;
import java.io.*;

class Solution {
  
  int n;
  int m;    // 상근이 돈
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    while (true) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      // m = (int) (Double.valueOf(st.nextToken()) * 100.00);   // 100 곱해서 정수로 변환
      m = Integer.parseInt(st.nextToken().replace(".", ""));    // 부동 소수점 오류 방지
      
      if (n == 0 && m == 0) break;
      
      int[] cal = new int[n+1];
      int[] price = new int[n+1];
      for (int i=1; i<=n; i++) {
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken().replace(".", ""));
        
        cal[i] = c;
        price[i] = p;
      }
      
      sb.append(calc(cal, price)).append("\n");
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  // 가장 높은 칼로리 계산
  int calc(int[] cal, int[] price) {
    int[][] dp = new int[n+1][m+1];
    
    for (int i=1; i<=n; i++) {
      for (int j=0; j<=m; j++) {
        if (price[i] > j) {
          // i번째 사탕 살 수 없다 -> 이전 사탕까지의 최적해 적용
          dp[i][j] = dp[i-1][j];    
        } else {
          // i번째 사탕 살 수 있다 -> 하나 더 산다 vs 사지 않는다
          // 사탕 개수 제한 없이 살 수 있다 (현재 행(dp[i]) 에서 업데이트)
          dp[i][j] = Math.max(dp[i-1][j], dp[i][j-price[i]] + cal[i]);
        }
      }
    }
    
    return dp[n][m];
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 같은 사탕 중복해서 구매해도 ok
// dp[i][j] : i번 사탕까지 고려, j만큼의 돈으로 살 수 있는 칼로리 최댓값
