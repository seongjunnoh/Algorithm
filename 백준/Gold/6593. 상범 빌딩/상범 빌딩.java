import java.util.*;
import java.io.*;

class Pair {
  int z, x, y, w;
  Pair (int z, int x, int y, int w) {
    this.z = z;
    this.x = x;
    this.y = y;
    this.w = w;
  }
}

class Solution {
  
  int[][] pos = {{0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    while (true) {
      st = new StringTokenizer(br.readLine());
      
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      if (l == 0 && r == 0 && c == 0) break;
      
      char[][][] map = new char[l][r][c];
      int[] start = new int[3];
      int[] end = new int[3];
      
      for (int z=0; z<l; z++) {
        for (int x=0; x<r; x++) {
          String line = br.readLine();
          for (int y=0; y<c; y++) {
            map[z][x][y] = line.charAt(y); 
            
            if (map[z][x][y] == 'S') {
              start[0] = z;
              start[1] = x;
              start[2] = y;
            }
            
            if (map[z][x][y] == 'E') {
              end[0] = z;
              end[1] = x;
              end[2] = y;
            }
          }
        }
        
        br.readLine();    // 빈 줄
      }
      
      // 탐색
      Deque<Pair> q = new ArrayDeque<>();
      boolean[][][] visit = new boolean[l][r][c];
      
      q.add(new Pair(start[0], start[1], start[2], 0));
      visit[start[0]][start[1]][start[2]] = true;
      
      int res = -1;
      while (!q.isEmpty()) {
        Pair poll = q.poll();
        
        if (poll.z == end[0] && poll.x == end[1] && poll.y == end[2]) {   // 도착
          res = poll.w;
          break;
        }
        
        for (int i=0; i<6; i++) {
          int nz = poll.z + pos[i][0];
          int nx = poll.x + pos[i][1];
          int ny = poll.y + pos[i][2];
          
          if (nz<0 || nz>=l || nx<0 || nx>=r || ny<0 || ny>=c) continue;
          if (!visit[nz][nx][ny] && map[nz][nx][ny] != '#') {
            q.add(new Pair(nz, nx, ny, poll.w + 1));
            visit[nz][nx][ny] = true;
          }
        }
      }
      
      if (res != -1) {
        sb.append("Escaped in ").append(res).append(" minute(s).").append("\n");
      } else {
        sb.append("Trapped!").append("\n");
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

// 3차원 그래프 탐색
