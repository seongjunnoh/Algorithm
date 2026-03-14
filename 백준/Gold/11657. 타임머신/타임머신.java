import java.util.*;
import java.io.*;

class Edge {
  int from, to, w;
  Edge(int from, int to, int w) {
    this.from = from;
    this.to = to;
    this.w = w;
  }
}

class Solution {
  
  long INF = Long.MAX_VALUE;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    Edge[] arr = new Edge[m];
    for (int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      arr[i] = new Edge(a, b, c);   // edge 배열에 추가
    }
    
    // 벨만 포트 알고리즘
    long[] min = new long[n + 1];   // 1 -> 도시 까지의 최단거리 (long 타입)
    Arrays.fill(min, INF);    // 초기화
    min[1] = 0;
    
    for (int i=0; i<n; i++) {
      for (int j=0; j<m; j++) {
        Edge cur = arr[j];
        
        if (min[cur.from] != INF && min[cur.to] > min[cur.from] + cur.w) {
          min[cur.to] = min[cur.from] + cur.w;
        }
      }
    }
    
    // 음의 사이클 존재하는지 확인
    boolean flag = false;
    for (int j=0; j<m; j++) {
      Edge cur = arr[j];
      
      if (min[cur.from] != INF && min[cur.to] > min[cur.from] + cur.w) {
        flag = true;
        break;
      }
    }
    
    if (flag) {
      System.out.println("-1");
      return;
    } 
    
    StringBuilder sb = new StringBuilder();
    for (int i=2; i<=n; i++) {
      if (min[i] == INF) {
        sb.append("-1").append("\n");
      } else {
        sb.append(min[i]).append("\n");
      }
    }
    
    System.out.println(sb.toString());
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
 * 1번 도시를 포함하는 음의 사이클이 존재 -> -1
 * 1번 도시에서 특정 도시로 이동할 수 없다 -> 해당 도시만 -1
 * 
 * 벨만 포드 알고리즘
 */