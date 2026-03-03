import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    int[] arr = new int[n];
    int maxLen = 0;   // 강의 하나의 max 값
    int sum = 0;    // 강의 전체 합
    
    st = new StringTokenizer(br.readLine());
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      maxLen = Math.max(maxLen, arr[i]);
      sum += arr[i];
    }
    
    int l = maxLen;
    int r = sum;
    while (l <= r) {
      int mid = (l + r) / 2;
      
      // mid를 블루레이 크기로 설정 -> 모든 강의 담을 수 있는지 체크
      int curSum = 0;
      int cnt = 0;    
      for (int i=0; i<n; i++) {
        if (curSum + arr[i] <= mid) {
          curSum += arr[i];
        } else {
          cnt++;
          curSum = 0;
          i--;    // i 요소 다시 탐색
        }
      }
      cnt++;    // 마지막 집합 count
      
      if (cnt <= m) {   // lower bound
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    
    System.out.println(l);
    br.close();
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 가능한 블루레이 크기 중 최소 구하기
// 매개변수 탐색 -> [강의 하나 크기의 최대, 강의 전체 크기 합] 범위에서의 매개변수 탐색
// 각 l,r 마다 O(n) 탐색
// O(n * log sum)
