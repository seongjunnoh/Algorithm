import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());   // 투자 금액
    int m = Integer.parseInt(st.nextToken());   // 기업 개수
    
    int[][] v = new int[m+1][n+1];    // v[i][j] : i번 기업에 j만큼 투자했을 때 이익
    for (int i=1; i<=n; i++) {
      st = new StringTokenizer(br.readLine());
      int price = Integer.parseInt(st.nextToken());   // 투자액수
      
      for (int j=1; j<=m; j++) {
        int reward = Integer.parseInt(st.nextToken());    // 각 기업에서의 투자 이익
        v[j][price] = reward;
      }
    }
    
    int[][] dp = new int[m+1][n+1];
    int[][] invest = new int[m+1][n+1];
    
    for (int i=1; i<=m; i++) {
      for (int j=1; j<=n; j++) {
        for (int curW = 0; curW <= j; curW++) {   // curW : 현재 기업에 투자할 금액
          int curProfit = dp[i-1][j-curW] + v[i][curW];   // 현재 기업에 curW 만큼 투자
          
          if (dp[i][j] < curProfit) {   // dp 업데이트
            dp[i][j] = curProfit;
            invest[i][j] = curW;
          }
        }
      }
    }
    
    int[] res = new int[m+1];   // 각 기업에 얼마 투자했는지
    int money = n;
    for (int i=m; i>=1; i--) {
      res[i] = invest[i][money];
      money -= res[i];    // money 업데이트
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append(dp[m][n]).append("\n");
    
    for (int i=1; i<=m; i++) {
      sb.append(res[i]).append(" ");
    } 
    
    System.out.println(sb.toString());
    br.close();
  }
  
  int key(int i, int j) {
    return i * 100 + j;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 기업에는 한번만 투자 가능
// dp[i][j] : i번 기업까지 고려, 현재 j만큼 투자했을 때의 이익 최댓값
// 각 dp 업데이트 시마다 현재 기업에 얼마나 투자할 지 O(n) 탐색 필요
// 각 기업에 얼마 투자했는지 기록을 위해 2차원 배열 정의
// invest[i][j] : i번 기업까지 고려, 총 j만큼 투자해서 최대 이익 냈을때, i번 기업에 투자한 금액
