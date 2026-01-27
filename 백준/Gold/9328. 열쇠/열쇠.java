import java.io.*;
import java.util.*;

class Pair {    // 좌표
  char c;
  int x;
  int y;
  
  Pair(char c, int x, int y) {
    this.c = c;
    this.x = x;
    this.y = y;
  }
}

class Solution {
  
  int h, w;
  char[][] map;
  boolean[] key;
  int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      st = new StringTokenizer(br.readLine());
      h = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());
      
      map = new char[h+2][w+2];
      for (int i=1; i<=h; i++) {
        String line = br.readLine();
        for (int j=1; j<=w; j++) {
          map[i][j] = line.charAt(j-1);
        }
      }
      
      key = new boolean[26];    // 0 ~ 25
      String firstKey = br.readLine();
      if (!firstKey.equals("0")) {
        for (int i=0; i<firstKey.length(); i++) {
          char k = firstKey.charAt(i);
          key[k - 'a'] = true;
        }
      }
      
      sb.append(find()).append("\n");
    }
    
    System.out.println(sb);
    br.close();
  }
  
  int find() {
    boolean[][] visit = new boolean[h+2][w+2];
    Queue<Pair>[] closeDoor = new LinkedList[26];   // 닫힌 문들
    for (int i=0; i<26; i++) {
      closeDoor[i] = new LinkedList<>();
    }
    int res = 0;
    
    // bfs
    Queue<Pair> q = new LinkedList<>();
    for (int i=0; i<h+2; i++) {
      if (i == 0 || i == h+1) {   // 가장 위, 아래 행
        for (int j=0; j<w+2; j++) {
          visit[i][j] = true;
          q.add(new Pair('.', i, j));
        }
      } else {    // 나머지 중간 행
        visit[i][0] = true;
        visit[i][w+1] = true;
        q.add(new Pair('.', i, 0));
        q.add(new Pair('.', i, w+1));
      }
    }
    
    while (!q.isEmpty()) {
      Pair poll = q.poll();
      
      for (int i=0; i<4; i++) {
        int nx = poll.x + pos[i][0];
        int ny = poll.y + pos[i][1];
        
        if (nx<0 || nx>h+1 || ny<0 || ny>w+1) continue;
        if (visit[nx][ny] || map[nx][ny] == '*') continue;
        
        visit[nx][ny] = true;   // 다음 공간 방문 처리
        
        if (map[nx][ny] == '.') {   // 빈 공간
          q.add(new Pair(map[nx][ny], nx, ny));
        } else if ('a' <= map[nx][ny] && map[nx][ny] <= 'z') {    // 열쇠
          key[map[nx][ny] - 'a'] = true;
          q.add(new Pair(map[nx][ny], nx, ny));   
          
          Queue<Pair> targetDoor = closeDoor[map[nx][ny] - 'a'];
          while (!targetDoor.isEmpty()) {   // 획득한 열쇠로 기존 닫힌 문을 열 수 있는 경우
            Pair door = targetDoor.poll();
            q.add(new Pair(door.c, door.x, door.y));    // bfs 탐색범위에 추가
          }
        } else if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {    // 문
          if (key[map[nx][ny] - 'A']) {   // 열 수 있는 문 -> bfs 탐색범위 추가
            q.add(new Pair(map[nx][ny], nx, ny));
          } else {    // 열 수 없는 문 -> bfs 탐색범위 X
            closeDoor[map[nx][ny] - 'A'].add(new Pair(map[nx][ny], nx, ny));
          }
        } else {    // 문서
          res++;
          q.add(new Pair(map[nx][ny], nx, ny));
        }
      }
    }
    
    return res;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

/**
 * 나중에 열쇠를 얻으면, 이미 지나온 문을 열 수 있다
 * 
 */
