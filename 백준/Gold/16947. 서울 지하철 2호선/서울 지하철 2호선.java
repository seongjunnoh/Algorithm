import java.util.*;
import java.io.*;

public class Main {
  
  static int n;
  static List<Integer>[] graph;
  static int[] edgeCount;
  static boolean[] inCycle;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    n = Integer.parseInt(br.readLine());
    
    graph = new ArrayList[n+1];
    for (int i=1; i<=n; i++) {
      graph[i] = new ArrayList<>();
    }
    
    edgeCount = new int[n+1];   // 각 노드와 연결된 간선 개수
    
    for (int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      graph[x].add(y);
      graph[y].add(x);
      
      edgeCount[x]++;
      edgeCount[y]++;
    }
  
    // edgeCount 개수가 1인 노드 -> 리프노드
    // 리프노드부터 탐색 시작 -> 사이클 본체 찾기
    inCycle = new boolean[n+1];
    findCycle();
    
    // 정답 출력
    StringBuilder sb = new StringBuilder();
    for (int i=1; i<=n; i++) {
      if (inCycle[i]) {   // i 노드가 사이클에 속하는 경우
        sb.append("0").append(" ");
      } else {    // i 노드가 사이클에 속하지 않는 경우
        sb.append(bfs(i)).append(" ");
      }
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  static void findCycle() {
    // edgeCount 값이 1인 노드 찾기 -> 리프노드
    // 리프노드부터 bfs 탐색 시작 -> 사이클 본체 찾기
    
    Deque<Integer> q = new ArrayDeque<>();
    boolean[] visit = new boolean[n+1];
    
    for (int i=1; i<=n; i++) {
      if (edgeCount[i] == 1) {
        q.add(i);
        visit[i] = true;
      }
    }
    
    while (!q.isEmpty()) {
      int poll = q.poll();
      
      for (int next : graph[poll]) {
        if (visit[next]) continue;
        
        edgeCount[next]--;
        
        if (edgeCount[next] == 1) {
          q.add(next);
          visit[next] = true;
        }
      }
    }
    
    // edgeCount 값이 2이상인 노드들 -> 사이클 본체
    for (int i=1; i<=n; i++) {
      if (edgeCount[i] >= 2) inCycle[i] = true;
    }
  }
  
  static int bfs(int node) {   // node와 가장 가까운 사이클에 속하는 노드까지의 거리 구하기
    Deque<int[]> q = new ArrayDeque<>();
    boolean[] visit = new boolean[n+1];
    
    q.add(new int[]{node, 0});
    visit[node] = true;
    
    while (!q.isEmpty()) {
      int[] poll = q.poll();
      
      if (inCycle[poll[0]]) return poll[1];   // 사이클에 속하는 노드
      
      for (int next : graph[poll[0]]) {
        if (visit[next]) continue;
        
        visit[next] = true;
        q.add(new int[]{next, poll[1] + 1});
      }
    }
    
    return -1;    // 여기까지 오면 안됨
  }
}

// 사이클 본체, 사이클 본체와 연결된 트리 구조를 판단해야 한다
// -> 사이클 본체에 포함되어 있다면 항상 2개 이상의 노드와 연결되어 있음
// -> 노드와 연결된 간선 개수를 차감시키면서 탐색 진행

// 사이클 본체에 포함되는 노드는 최단 거리가 0
// 사이클 본체에 포함되지 않는 노드는, 사이클 본체 만날 때까지 bfs
