import java.io.*;
import java.util.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 선분 ab
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] a = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    int[] b = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    
    // 선분 cd
    st = new StringTokenizer(br.readLine());
    int[] c = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    int[] d = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    
    int r1 = ccw(a, b, c);
    int r2 = ccw(a, b, d);
    
    int r3 = ccw(c, d, a);
    int r4 = ccw(c, d, b);
    
    if (r1 * r2 < 0 && r3 * r4 < 0) {   // 두 선분이 교차한다
      System.out.println("1");
    } else {
      System.out.println("0");
    }
    br.close();
  }
  
  // 벡터 xy, 벡터 xz의 외적 계산
  int ccw(int[] x, int[] y, int[] z) {
    // 벡터 xy = (y0 - x0, y1 - x1, 0)
    // 벡터 xz = (z0 - x0, z1 - x1, 0)
    long result = (long) (y[0] - x[0]) * (z[1] - x[1]) - (long) (z[0] - x[0]) * (y[1] - x[1]);
    
    if (result > 0) return 1;   // 반시계
    else if (result < 0) return -1;   // 시계
    return 0;   // 일직선 -> 문제 조건에 의해 0이 return 될 수는 없다
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
    }
}

// 선분 2개가 교차하는지 아닌지 판단
// CCW 알고리즘으로 판단

