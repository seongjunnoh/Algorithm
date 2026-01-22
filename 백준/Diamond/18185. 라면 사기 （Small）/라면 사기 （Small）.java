import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] cnt = new int[n+2];   // 구매해야하는 라면 개수
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i=0; i<n; i++) {
      cnt[i] = Integer.parseInt(st.nextToken());
    }
    
    int min = 0;
    for (int i=0; i<n; i++) { // i, i+1, i+2 고려
      // i+1 > i+2 일 경우, 초과분에 대하여 (i, i+1) 먼저 구매
      if (cnt[i+1] > cnt[i+2]) {
        int x = Math.min(cnt[i], cnt[i+1] - cnt[i+2]);  // (i, i+1) 세트 수
        min += x * 5;
        cnt[i] -= x;
        cnt[i+1] -= x;
      }
      
      // (i, i+1, i+2) 구매 -> 가장 작은 물량 기준
      int x = Math.min(cnt[i], Math.min(cnt[i+1], cnt[i+2]));
      min += x * 7;
      cnt[i] -= x;
      cnt[i+1] -= x;
      cnt[i+2] -= x;
      
      // 나머지 i 처리
      min += cnt[i] * 3;
    }
    
    System.out.println(min);
    br.close();
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
  }
}

/**
 * 최대한 연속해서 라면 구매해야 이득
 * i, i+1, i+2
 * i부터 1연속 가능 -> i만 구매
 * i부터 2연속 가능 -> i, i+1 연속 구매 포함
 * i부터 3연속 가능 -> i, i+1, i+2 연속 구매 포함
 * -> 연속 구매 후, i+1 부터 연속이 가능해야 이득
 */