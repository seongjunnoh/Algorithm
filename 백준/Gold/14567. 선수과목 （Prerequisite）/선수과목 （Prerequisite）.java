import java.util.*;
import java.io.*;

class Node {
  int num;
  int time;
  
  Node (int num, int time) {
    this.num = num;
    this.time = time;
  }
}

public class Main {
  
  static List<Integer>[] graph;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    graph = new ArrayList[n+1];
    for (int i=1; i<=n; i++) {
      graph[i] = new ArrayList<>();
    }
    
    int[] in = new int[n+1];    // node i의 진입 차수
    
    for (int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      graph[a].add(b);    // a -> b
      in[b]++;
    }
    
    Deque<Node> q = new ArrayDeque<>();
    for (int i=1; i<=n; i++) {
      if (in[i] == 0) q.add(new Node(i, 1));    // 큐 초기화
    }
    
    int[] res = new int[n+1];   // 정답
    
    while (!q.isEmpty()) {
      Node node = q.poll();
      
      if (res[node.num] != 0) continue;   // 이미 방문한 노드
      
      res[node.num] = node.time;
      
      for (int next : graph[node.num]) {
        in[next]--;
        
        if (in[next] == 0 && res[next] == 0) {
          q.add(new Node(next, node.time + 1));
        } 
      }
    }
    
    StringBuilder sb = new StringBuilder();
    for (int i=1; i<=n; i++) {
      sb.append(res[i]).append(" ");
    }
    System.out.println(sb.toString());
    br.close();
  }
}

// 위상정렬
// 특정 과목을 가장 빨리 이수할 수 있는 학기 계산해야함