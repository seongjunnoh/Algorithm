import java.util.*;
import java.io.*;

class Dice {
  int t, r, l, f, b, d;   // 위 오른 왼 앞 뒤 아래
  int dir;    // 이동 방향 (0 1 2 3, 위 오른 아래 왼)
  Dice (int t, int r, int l, int f, int b, int d, int dir) {
    this.t = t;
    this.r = r;
    this.l = l;
    this.f = f;
    this.b = b;
    this.d = d;
    this.dir = dir;
  }
}

class Solution {
  
  int n, m;
  int[][] map;
  int[][] pos = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};   // 위 오른 아래 왼
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    map = new int[n][m];
    for (int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    Dice dice = new Dice(1, 3, 4, 2, 5, 6, 1);   // 초기화
    int x = 0;
    int y = 0;
    int point = 0;
    while (k > 0) {
      int[] to = move(dice, x, y);
      
      int val = map[to[0]][to[1]];    // 도착한 칸 값
      point += val * bfs(to[0], to[1]);
      
      // 주사위 이동 방향 결정
      if (dice.d > val) dice.dir = (dice.dir + 1) % 4;
      else if (dice.d < val) dice.dir = (dice.dir + 3) % 4;
      
      x = to[0];
      y = to[1];
      k--;
      
      // System.out.println("f = " + dice.f + ", t = " + dice.t + ", b = " + dice.b + ", d = " + dice.d + ", r = " + dice.r + ", l = " + dice.l + ", dir = " + dice.dir);
      // System.out.println("point = " + point);
    }
    
    System.out.println(point);
    br.close();
  }
  
  int bfs(int x, int y) {
    Deque<int[]> q = new ArrayDeque<>();
    boolean[][] visit = new boolean[n][m];
    
    q.add(new int[]{x, y});
    visit[x][y] = true;
    int target = map[x][y];   // x,y 와 인접한 지점 중, 값이 target인 지점의 개수 찾기
    int count = 1;
    
    while (!q.isEmpty()) {
      int[] poll = q.poll();
      
      for (int i=0; i<4; i++) {
        int nx = poll[0] + pos[i][0];
        int ny = poll[1] + pos[i][1];
        
        if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
        if (!visit[nx][ny] && map[nx][ny] == target) {
          visit[nx][ny] = true;
          q.add(new int[]{nx, ny});
          count++;
        }
      }
    }
    
    return count;
  }
  
  int[] move(Dice dice, int x, int y) {
    int nx = x + pos[dice.dir][0];
    int ny = y + pos[dice.dir][1];
    
    if (nx<0 || nx>=n || ny<0 || ny>=m) {
      dice.dir = (dice.dir + 2) % 4;    // 이동방향 반전
      nx = x + pos[dice.dir][0];
      ny = y + pos[dice.dir][1];
    }
    
    // 주사위 전개도 update
    if (dice.dir == 0) {
      int tmp = dice.f;
      dice.f = dice.t;
      dice.t = dice.b;
      dice.b = dice.d;
      dice.d = tmp;
    } else if (dice.dir == 1) {
      int tmp = dice.l;
      dice.l = dice.d;
      dice.d = dice.r;
      dice.r = dice.t;
      dice.t = tmp;
    } else if (dice.dir == 2) {
      int tmp = dice.d;
      dice.d = dice.b;
      dice.b = dice.t;
      dice.t = dice.f;
      dice.f = tmp;
    } else {    // 3
      int tmp = dice.r;
      dice.r = dice.d;
      dice.d = dice.l;
      dice.l = dice.t;
      dice.t = tmp;
    }
    
    return new int[]{nx, ny};
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// x,y 의 정수가 B일때, x,y 와 인접한 정수 B인 칸의 개수를 C라 하면 -> 점수 = B*C