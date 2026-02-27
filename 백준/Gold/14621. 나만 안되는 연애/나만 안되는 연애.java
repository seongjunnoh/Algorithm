import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
  int n1, n2, w;
  Edge(int n1, int n2, int w) {
    this.n1 = n1;
    this.n2 = n2;
    this.w = w;
  }
  
  @Override
  public int compareTo(Edge e) {
    return this.w - e.w;    // w 기준 오름차순 정렬
  }
}

class Solution {
  
  int n;
  int[] school;
  int[] parent;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    school = new int[n+1];    // 학교 종류(0 : M, 1 : W)
    parent = new int[n+1];    // 유니온 파인드를 위한 parent 배열
    
    st = new StringTokenizer(br.readLine());
    for (int i=1; i<=n; i++) {
      parent[i] = i;    // parent 초기화
      
      String s = st.nextToken();
      if (s.equals("M")) school[i] = 0;
      else school[i] = 1;
    }
    
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    for (int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      pq.add(new Edge(n1, n2, w));
    }
    
    int len = 0;
    int cnt = 0;
    while (!pq.isEmpty()) {
      Edge poll = pq.poll();
      
      if (find(poll.n1) != find(poll.n2) && school[poll.n1] != school[poll.n2]) {
        union(poll.n1, poll.n2);
        len += poll.w;
        cnt++;
      }
    }
    
    // 모든 학교 연결되었는지 확인
    if (cnt != n-1) {
      System.out.println("-1");
    } else {
      System.out.println(len);
    }
    br.close();
  }
  
  int find(int n) {
    if (parent[n] == n) return n;
    parent[n] = find(parent[n]);
    return parent[n];
  }
  
  void union(int n1, int n2) {
    int r1 = find(n1);
    int r2 = find(n2);
    
    if (r1 < r2) parent[r2] = r1;
    else parent[r1] = r2;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 서로 다른 종류의 노드만 연결 & 모든 노드가 연결 & 최단 거리
// MST 구하기, 크루스칼 알고리즘