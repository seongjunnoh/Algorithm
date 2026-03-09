import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    int n = Integer.parseInt(line.split(" ")[0]);
    int h = Integer.parseInt(line.split(" ")[1]);
    
    int[] down = new int[h+1];    // 석순
    int[] up = new int[h+1];    // 종유석
    for (int i=0; i<n; i++) {
      int len = Integer.parseInt(br.readLine());
      
      // 특정 len의 종유석, 석순 개수 카운트
      if (i%2 == 0) down[len]++;
      else up[len]++;
    }
    
    for (int i=h-1; i>=1; i--) {
      down[i] += down[i+1];   // down[i] : 길이가 i이상인 석순 개수
      up[i] += up[i+1];   // up[i] : 길이가 i이상인 종유석 개수
    }
    
    int[] res = new int[h+1];   // res[i] : i 구간에서 걸리는 장애물 수
    for (int i=1; i<=h; i++) {
      // 석순 i 이상 & 종유석 h-i+1 이상이어야 걸린다
      res[i] = down[i] + up[h-i+1];   
    }
    
    Arrays.sort(res);
    
    int min = res[1];   // 1 based
    int count = 1;
    for (int i=2; i<=h; i++) {
      if (res[i] == min) count++;
      else break;
    }
    
    System.out.println(min + " " + count);
    br.close();
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// (석순, 종유석) 반복
// h 개의 구간 존재 (i 구간 : 길이 i-1 ~ 길이 i 중간 지점)
// 총 h개의 구간 탐색 & 각 구간에서 몇개의 장애물에 걸리는지를 이진 탐색으로 판단
// -> O(h * logn)

// 누적합으로도 해결 가능