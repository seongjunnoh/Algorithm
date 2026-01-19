import java.util.*;
import java.io.*;

class Solution {
  
  int n;
  int[][] cost;
  int[][] dp;
  int INF = Integer.MAX_VALUE;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    n = Integer.parseInt(br.readLine());
    cost = new int[n][n];
    
    for (int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<n; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());  // i -> j 비용
      }
    }
    
    dp = new int[n][(1 << n)];
    for (int i=0; i<n; i++) {
      Arrays.fill(dp[i], -1);  // dp 초기화
    }
    
    System.out.println(trip(0, 1));  // 0 -> 0 까지의 최단거리 계산
    br.close();
  }
  
  // 현재 위치 : cur, 현재까지 방문한 도시 : mask
  // -> 0번 도시로의 최단경로 구하는 메서드
  int trip(int cur, int mask) {
    // 모든 도시를 방문한 경우
    if (mask == (1 << n) - 1) {
      if (cost[cur][0] == 0) return INF;  // 0번 도시로 순회 X
      return cost[cur][0];
    }
    
    // 계산된 값이 존재하는 경우
    if (dp[cur][mask] != -1) return dp[cur][mask];
    
    // 계산된 값이 존재하지 않는 경우
    dp[cur][mask] = INF;
    for (int next = 0; next<n; next++) {
      // cur -> next 이동 가능 & 아직 방문하지 않은 도시인 경우
      if (cost[cur][next] != 0 && ((mask & (1 << next)) == 0)) {
        int w = trip(next, mask | (1 << next));   // next 도시 방문
        
        if (w != INF) {   // dp 업데이트
          dp[cur][mask] = Math.min(dp[cur][mask], cost[cur][next] + w);  
        }
      }
    }
    
    return dp[cur][mask];
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
  }
}

/**
 * 각 도시를 한번씩만 방문 & 모든 도시 방문 & 순회 여행의 최단경로
 * 완전탐색 : 최악의 경우 16! -> 시간초과
 * 
 * 특정 도시에서 순회를 완료하기 위한 최단거리를 메모이제이션
 * -> dp[cur][이미 방문한 도시들] : cur에서 순회를 완료하기 위한 최단거리
 * -> 방문한 도시들을 비트마스킹로 관리
 */