import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
  int n1;
  int n2;
  int w;
  Edge(int n1, int n2, int w) {
    this.n1 = n1;
    this.n2 = n2;
    this.w = w;
  }
  
  @Override
  public int compareTo(Edge e) {
    return this.w - e.w;    // 오름차순 정렬
  }
}

class Solution {
  
  int[] parent;
  
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
      int w = Integer.parseInt(st.nextToken());
      
      arr[i] = new Edge(a, b, w);
    }
    
    Arrays.sort(arr);   // 정렬
    
    parent = new int[n+1];    // 1~n 까지의 노드
    for (int i=1; i<=n; i++) {
      parent[i] = i;    // 초기화
    }
    
    int maxIdx = 0;   // MST에 포함되는 간선 중, 가중치 최대인 간선의 idx
    int sum = 0;    // MST 가중치 합
    for (int i=0; i<m; i++) {
      Edge e = arr[i];
      
      if (find(e.n1) != find(e.n2)) {
        union(e.n1, e.n2);
        maxIdx = i;
        sum += e.w;
      }
    }
    
    sum -= arr[maxIdx].w;   // MST 에서 maxIdx 간선 제외
    System.out.println(sum);
    br.close();
  }
  
  int find(int node) {
    if (parent[node] == node) return node;
    parent[node] = find(parent[node]);
    return parent[node];
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

/**
 * 2개의 MST 만들기 -> MST 1개 만든 후, 가중치 가장 큰 간선 제거
 */