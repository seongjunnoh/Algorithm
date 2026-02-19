import java.io.*;
import java.util.*;

class Solution {
  
  int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    Map<Integer, Set<Integer>> map = new HashMap<>();  // key : 현재 위치, val : 여기에서 불 킬수 있는 위치 
    for (int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int key = encode(x, y);
      int val = encode(a, b);
      
      map.putIfAbsent(key, new HashSet<>());
      map.get(key).add(val);
    }
    
    // 1,1 부터 bfs
    Deque<Integer> q = new ArrayDeque<>();  // 이동가능한 위치 보관
    boolean[][] light = new boolean[n+1][n+1];
    boolean[][] visit = new boolean[n+1][n+1];
    
    q.addLast(encode(1, 1));
    light[1][1] = true;
    visit[1][1] = true;
    int res = 1;
    
    while (!q.isEmpty()) {
      int cur = q.pollFirst();
    
      // 현재 방에서 가능한 모든 불 켜기
      if (map.containsKey(cur)) {
        for (int val : map.get(cur)) {
          int[] valPos = decode(val);
          int vx = valPos[0];
          int vy = valPos[1];
          
          if (light[vx][vy]) continue;  // 이미 vx, vy에 불이 켜진 경우
          
          light[vx][vy] = true;
          res++;
          
          // vx, vy 로 이동가능한지 판단
          for (int i=0; i<4; i++) {
            int nx = vx + pos[i][0];
            int ny = vy + pos[i][1];
            
            if (nx<=0 || nx>n || ny<=0 || ny>n) continue;
            if (visit[nx][ny]) {    // nx, ny 를 방문한 적이 있다 -> vx, vy 방문가능하다
              visit[vx][vy] = true;
              q.add(encode(vx, vy));
            }
          }
        }
      }
      
      int[] curPos = decode(cur);
      for (int i=0; i<4; i++) {   // cur에서 이동가능한 방 있는지 탐색
        int nx = curPos[0] + pos[i][0];
        int ny = curPos[1] + pos[i][1];
        
        if (nx<=0 || nx>n || ny<=0 || ny>n) continue;
        if (!visit[nx][ny] && light[nx][ny]) {
          visit[nx][ny] = true;
          q.add(encode(nx, ny));
        }
      }
    }
    
    System.out.println(res);
    br.close();
  }
  
  int encode(int x, int y) {
    return 1000 * x + y;
  }
  
  int[] decode(int num) {
    int x = num / 1000;
    int y = num % 1000;
    
    return new int[]{x, y};
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
    }
}

// 1,1 에서 탐색 시작 -> 상하좌우 위치에 불이 켜져 있어야 이동 가능
// bfs 탐색
// 현위치에서 다른방 모두 불켜기 -> 불킨 방으로 이동가능한지 판단

// x,y -> 1000 * x + y 로 해싱