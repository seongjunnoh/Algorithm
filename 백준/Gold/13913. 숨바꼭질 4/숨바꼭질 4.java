import java.util.*;
import java.io.*;

class Pair {
  int idx;
  int t;
  
  Pair(int idx, int t) {
    this.idx = idx;
    this.t = t;
  }
}

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    if (n == k) {   // 예외처리
      System.out.println("0");
      System.out.println(n);
      return;
    }
    
    int[] time = new int[100001];   // 해당 위치에 도착하는 최소 시간
    int[] before = new int[100001];   // before[i] : i에 가장 빠르게 도착할 때, 직전의 위치
    Arrays.fill(time, Integer.MAX_VALUE);
    
    time[n] = 0;    // 초기화
    before[n] = n;
    
    Deque<Pair> q = new ArrayDeque<>();
    q.add(new Pair(n, 0));
    
    while (!q.isEmpty()) {
      Pair poll = q.poll();
      
      if (poll.idx == k) break;   // 정답
      
      int[] next = new int[3];
      next[0] = poll.idx - 1;
      next[1] = poll.idx + 1;
      next[2] = poll.idx * 2;
      
      for (int i=0; i<3; i++) {
        if (next[i] < 0 || next[i] > 100000) continue;
        if (time[next[i]] > poll.t + 1) {
          time[next[i]] = poll.t + 1;
          before[next[i]] = poll.idx;
          
          q.add(new Pair(next[i], poll.t + 1));
        }
      }
    }
    
    System.out.println(time[k]);
    
    List<Integer> list = new ArrayList<>();
    list.add(k);
    int cur = k;
    while (true) {
      if (before[cur] == n) {
        list.add(n);
        break;
      }
      
      list.add(before[cur]);
      cur = before[cur];
    }
    
    StringBuilder sb = new StringBuilder();
    for (int i=list.size() - 1; i>=0; i--) {
      sb.append(list.get(i)).append(" ");
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

// 걷거나 순간이동을 해서 동생을 가장 빨리 찾기
// bfs & 경로 백트래킹으로 이동 경로 확인

