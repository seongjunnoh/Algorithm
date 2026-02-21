import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
  int x;
  int y;
  int dir;
  int curveCnt;   // 현재 경로까지의 꺾인 부분 개수
  
  Pair (int x, int y, int dir, int curveCnt) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.curveCnt = curveCnt;
  }
  
  @Override
  public int compareTo(Pair p) {
    return this.curveCnt - p.curveCnt;    // curveCnt 기준 오름차순 정렬
  }
}

class Solution {
  
  int w, h;
  char[][] map;
  int[][][] minCurve;   // minCurve[dir][i][j] : dir방향으로 i,j에 도달할 때, 가장 적게 꺾인 횟수
  int[][] pos = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    w = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());
    
    map = new char[h][w];
    List<int[]> list = new ArrayList<>();   // c 위치
    for (int i=0; i<h; i++) {
      String line = br.readLine();
      for (int j=0; j<w; j++) {
        map[i][j] = line.charAt(j);
        
        if (map[i][j] == 'C') {
          list.add(new int[]{i, j});
        }
      }
    }
    
    int[] start = list.get(0);
    int[] end = list.get(1);
    
    System.out.println(bfs(start, end));
    br.close();
  }
  
  int bfs(int[] start, int[] end) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    minCurve = new int[4][h][w];
    for (int i=0; i<4; i++) {   // minCurve 초기화
      for (int x=0; x<h; x++) {
        Arrays.fill(minCurve[i][x], Integer.MAX_VALUE);
      }
    }
    
    for (int i=0; i<4; i++) {   // 출발점 초기화
      pq.add(new Pair(start[0], start[1], i, 0));
      minCurve[i][start[0]][start[1]] = 0;  
    }
    
    while (!pq.isEmpty()) {
      Pair poll = pq.poll();
      if (poll.x == end[0] && poll.y == end[1]) {
        return poll.curveCnt;
      }
      
      for (int i=0; i<4; i++) {
        int nx = poll.x + pos[i][0];
        int ny = poll.y + pos[i][1];
        int nCurveCnt = poll.dir != i ? poll.curveCnt + 1 : poll.curveCnt;
        
        if (nx<0 || nx>=h || ny<0 || ny>=w) continue;
        if (minCurve[i][nx][ny] <= nCurveCnt) continue;   // 이미 nx,ny에 더 효율적으로 도달한 적이 있다
        if (map[nx][ny] != '*') {
          minCurve[i][nx][ny] = nCurveCnt;
          pq.add(new Pair(nx, ny, i, nCurveCnt));
        }
      }
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

// C칸 끼리 가능한 경로 중, 꺾이는 부분의 개수가 최소인 경로 찾기
// bfs 탐색 + 우선순위 큐 활용
// 특정 지점에 꺾인 횟수 최소로 도달해야한다
