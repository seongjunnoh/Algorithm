import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    int n = Integer.parseInt(line.split(" ")[0]);
    int h = Integer.parseInt(line.split(" ")[1]);
    
    int[] down = new int[n/2];    // 석순 (n 짝수 보장)
    int[] up = new int[n/2];    // 종유석
    for (int i=0; i<n; i++) {
      int len = Integer.parseInt(br.readLine());
      
      if (i%2 == 0) down[i/2] = len;
      else up[i/2] = len;
    }
    
    Arrays.sort(down);
    Arrays.sort(up);
    
    int[] res = new int[h+1];   // res[i] : i 구간에서 걸리는 장애물 수
    for (int i=1; i<=h; i++) {
      int count = 0;
      count += n/2 - lowerbound(down, i);   // 충돌 == 전체 석순 - 길이가 i미만인 석순
      count += n/2 - lowerbound(up, h-i+1); // 충돌 == 길이가 (h-i+1) 이상인 종유석 == 전체 종유석 - 길이가 h-i+1 미만인 종유석
      
      res[i] = count;
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
  
  int lowerbound(int[] arr, int curH) {
    int l = 0;
    int r = arr.length - 1;
    while (l <= r) {
      int mid = (l+r) / 2;
      
      if (arr[mid] >= curH) r = mid - 1;
      else l = mid + 1;
    }
    
    return l;   // curH 이상인 값이 최초 등장 인덱스 반환
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