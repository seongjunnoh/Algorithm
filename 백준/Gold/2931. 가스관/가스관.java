import java.util.*;
import java.io.*;

class Solution {
  
  int r, c;
  char[][] map;
  boolean[][] visit;
  int blockCnt;
  List<Character> blockList;
  boolean success;
  StringBuilder sb;
  int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 위, 아래, 왼, 오른 -> 0,1,2,3
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    map = new char[r+1][c+1];
    
    blockList = new ArrayList<>();    // |, -, + 순서대로 탐색 -> list
    blockList.add('|');
    blockList.add('-');
    blockList.add('+');
    blockList.add('1');
    blockList.add('2');
    blockList.add('3');
    blockList.add('4');
    
    int sx = 0, sy = 0;
    blockCnt = 0;
    for (int i=1; i<=r; i++) {
      String line = br.readLine();
      for (int j=1; j<=c; j++) {
        map[i][j] = line.charAt(j-1);
        
        if (map[i][j] == 'M') {
          sx = i;
          sy = j;
        }
        
        if (map[i][j] != 'M' && map[i][j] != 'Z' && map[i][j] != '.') blockCnt++;
      }
    }
    
    blockCnt++;   // 전체 블록 개수
    
    int d = -1;
    for (int i=0; i<4; i++) {
      int nx = sx + pos[i][0];
      int ny = sy + pos[i][1];
      
      if (nx<1 || nx>r || ny<1 || ny>c) continue;
      if (map[nx][ny] != 'M' && map[nx][ny] != 'Z' && map[nx][ny] != '.') {
        // M과 인접하는 블록
        sx = nx;
        sy = ny;
        d = i;
        break; 
      }
    }
    
    visit = new boolean[r+1][c+1];
    success = false;
    play(sx, sy, d, 0, false);    // 탐색 시작
    
    System.out.println(sb);
    br.close();
  }
  
  void play(int x, int y, int d, int cnt, boolean flag) {
    // 범위 벗어나는 경우 -> 탐색 X
    if (x<1 || x>r || y<1 || y>c) return;
    
    char c = map[x][y];
    
    // 도착
    if (c == 'Z') {
      // System.out.println("cnt = " + cnt + ", blockCnt = " + blockCnt);
      
      if (cnt == blockCnt) success = true;
      return;
    }
    
    // + 블록이 아닌데 이전에 방문한 블록인 경우 -> 탐색 X
    if (c != '+' && visit[x][y]) return;
    
    // 길이 끊김
    if (c == '.') {
      // 이미 블록을 추가했는데 길이 끊김 -> 탐색 X
      if (flag) return;
      
      for (char b : blockList) {
        // System.out.println("x = " + x + ", y = " + y + ", d = " + d + ", cnt = " + cnt);
        // System.out.println("b = " + b);
        
        int nd = calc(b, d);
        if (nd == -1) continue;
        
        map[x][y] = b;    // 블록 채우기
        visit[x][y] = true;   // 방문 처리
        
        if (nd == 0) play(x-1, y, 0, cnt+1, true);
        if (nd == 1) play(x+1, y, 1, cnt+1, true);
        if (nd == 2) play(x, y-1, 2, cnt+1, true);
        if (nd == 3) play(x, y+1, 3, cnt+1, true);
        
        if (!success) {
          map[x][y] = '.';
          visit[x][y] = false;    // 원복
          continue;
        }
        
        // 성공
        sb = new StringBuilder();
        sb.append(x).append(" ").append(y).append(" ").append(b);
        return;
      }
    }
    
    // + 블록을 다시 방문 -> cnt 업데이트 X 
    if (c == '+' && visit[x][y]) cnt--;
    
    visit[x][y] = true;   // 현재 블록 방문처리
    
    // 계속 탐색
    int nd = calc(c, d);
    if (nd == 0) play(x-1, y, 0, cnt+1, flag);
    if (nd == 1) play(x+1, y, 1, cnt+1, flag);
    if (nd == 2) play(x, y-1, 2, cnt+1, flag);
    if (nd == 3) play(x, y+1, 3, cnt+1, flag);
    
    visit[x][y] = false;    // 원복
  }
  
  int calc(char c, int d) {
    if (c == '|') {
      if (d == 0) return 0;
      if (d == 1) return 1;
    } else if (c == '-') {
      if (d == 2) return 2;
      if (d == 3) return 3;
    } else if (c == '+') {
      if (d == 0) return 0;
      if (d == 1) return 1;
      if (d == 2) return 2;
      if (d == 3) return 3;
    } else if (c == '1') {
      if (d == 0) return 3;
      if (d == 2) return 1;
    } else if (c == '2') {
      if (d == 1) return 3;
      if (d == 2) return 0;
    } else if (c == '3') {
      if (d == 3) return 0;
      if (d == 1) return 2;
    } else if (c == '4') {
      if (d == 3) return 1;
      if (d == 0) return 2;
    }
    
    return -1;
  }
}


public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

/**
 * M부터 블록 따라서 탐색 -> 이때는 문제 상황 없음
 * 처음 빈칸 만났을 때 -> 7가지 순서대로 블록 할당 (백트래킹)
 * 빈칸에 블록 할당한 이후 -> 탐색 이어가면서 map 범위 넘지 않는지 체크 필요
 */

