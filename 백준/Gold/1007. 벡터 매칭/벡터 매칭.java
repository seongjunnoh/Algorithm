import java.util.*;
import java.io.*;

class Vector {
  int from;
  int to;
  
  Vector (int from, int to) {
    this.from = from;
    this.to = to;
  }
}

class Solution {
  
  double min;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      
      List<int[]> list = new ArrayList<>();   // 점들의 list
      for (int i=0; i<n; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[] point = {x, y};   // (x, y)
        
        list.add(point);
      }
      
      boolean[] isPlus = new boolean[list.size()];
      min = Double.MAX_VALUE;
      
      play(list, 0, isPlus, 0);
      
      sb.append(min).append("\n");
    }
    
    System.out.println(sb);
    br.close();
  }
  
  void play(List<int[]> list, int idx, boolean[] isPlus, int count) {
    if (count == list.size() / 2) {    // + 점들 선택 완료
      min = Math.min(min, calc(list, isPlus));    // 벡터 합 길이 계산
      return;
    }
    
    for (int i=idx; i<list.size(); i++) {
      isPlus[i] = true;
      play(list, i+1, isPlus, count + 1);
      isPlus[i] = false;    // 원상복구
    }
  }
  
  double calc(List<int[]> list, boolean[] isPlus) {
    int xPlus = 0, yPlus = 0;
    int xMinus = 0, yMinus = 0;
    
    for (int i=0; i<list.size(); i++) {
      int[] point = list.get(i);
      
      if (isPlus[i]) {
        xPlus += point[0];
        yPlus += point[1];  
      } else {
        xMinus += point[0];
        yMinus += point[1];
      }
    }
    
    int dx = xPlus - xMinus;
    int dy = yPlus - yMinus;
    return Math.sqrt((double) dx*dx + (double) dy*dy);
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

/* 벡터 매칭 경우의 수 = 20 C 2 * 18 C 2 * ,,, * 2 C 2 / 10! * 2^10 = 20! / 10!
 * -> 시간 초과
 * 
 * (x1, y1), (x2, y2), (x3, y3), (x4, y4)
 * v1 = (x2, y2) - (x1, y1), v2 = (x4, y4) - (x3, y3) 
 * v1 + v2 = (x2 + x4 - x1 - x3, y2 + y4 - y1 - y3)
 * 
 * 합 벡터는 최대 20개 점들 중 + 부호 10개, - 부호 10개를 고르면 OK
 * 20 C 10 = 20! / 10! / 10!
 */
