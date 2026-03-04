import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair> {
  int x, y, w;
  Pair(int x, int y, int w) {
    this.x = x;
    this.y = y;
    this.w = w;
  }
  
  @Override
  public int compareTo(Pair p) {
    return this.w - p.w;    // w 기준 오름차순 정렬
  }
}

class Solution {
  
  int n;
  int[][] map;
  int[][] pos = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    int cnt = 1;
    while (true) {
      n = Integer.parseInt(br.readLine());
      if (n == 0) break;
      
      map = new int[n][n];
      for (int i=0; i<n; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<n; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      sb.append("Problem ").append(cnt++).append(": ").append(play()).append("\n");
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  int play() {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    boolean[][] visit = new boolean[n][n];
    
    pq.add(new Pair(0, 0, map[0][0]));
    
    while (!pq.isEmpty()) {
      Pair poll = pq.poll();
      
      // poll 한 후 visit 처리 -> 이미 방문했다면 skip
      if (visit[poll.x][poll.y]) continue;
      visit[poll.x][poll.y] = true;
      
      if (poll.x == n-1 && poll.y == n-1) {
        return poll.w;    // 정답
      }
      
      for (int i=0; i<4; i++) {
        int nx = poll.x + pos[i][0];
        int ny = poll.y + pos[i][1];
        
        if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
        if (!visit[nx][ny]) {
          pq.add(new Pair(nx, ny, poll.w + map[nx][ny]));
        }
      }
    }
    
    return -1;    // 쓰레기 값
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 0,0 -> n,n 최소비용 구하기
// 다익스트라 알고리즘

