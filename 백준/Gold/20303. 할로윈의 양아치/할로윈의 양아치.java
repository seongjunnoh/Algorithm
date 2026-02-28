import java.util.*;
import java.io.*;

class Solution {
  
  int[] parent;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    int[] candy = new int[n+1];
    parent = new int[n+1];
    st = new StringTokenizer(br.readLine());
    for (int i=1; i<=n; i++) {
      candy[i] = Integer.parseInt(st.nextToken());
      parent[i] = i;
    }
    
    // union find
    for (int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      if (find(a) != find(b)) union(a, b);
    }
    
    int[] totalChild = new int[n+1];
    int[] totalCandy = new int[n+1];
    for (int i=1; i<=n; i++) {
      int root = find(i);
      totalChild[root]++;
      totalCandy[root] += candy[i];
    }
    
    List<int[]> groups = new ArrayList<>();
    groups.add(new int[]{0, 0});    // 0번 인덱스 더미 add
    for (int i=1; i<=n; i++) {
      if (totalChild[i] > 0) {
        groups.add(new int[]{totalChild[i], totalCandy[i]});    // 유효한 집합만 add
      }
    }
    
    // dp[i][j] : i 집합 까지 고려 & 현재 j명까지 고려한 경우의 최댓값
    int[][] dp = new int[groups.size()][k];
    for (int i=1; i<groups.size(); i++) {
      int w = groups.get(i)[0];   // 현재 그룹의 인원 수
      int v = groups.get(i)[1];   // 현재 그룹의 사탕 수
      
      for (int j=0; j<k; j++) {
        // 현재 그룹 인원수가 j 이하 -> 현재 그룹 포함할지 말지 결정
        // j 초과 -> 포함할 수 없음
        if (w <= j) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + v);
        else dp[i][j] = dp[i-1][j];   
      }
    }
   
    System.out.println(dp[groups.size() - 1][k - 1]);
    br.close();
  }
  
  int find(int node) {
    if (parent[node] == node) return node;
    parent[node] = find(parent[node]);
    return parent[node];
  }
  
  void union(int a, int b) {
    int ra = find(a);
    int rb = find(b);
    
    if (ra > rb) parent[ra] = rb;
    else parent[rb] = ra;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// union find 로 집합 나누기
// 나눈 집합들을 어떻게 조합해야 사람이 k 미만 & 사탕 수 최대인지 판단
// 집합의 사탕 수로 정렬 & 그리디하게 접근 -> X (반례 존재함)
// 그렇다고 모든 조합을 생각하면 시간초과
// dp
