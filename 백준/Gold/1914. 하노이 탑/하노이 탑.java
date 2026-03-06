import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
  
  StringBuilder sb;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    BigInteger move = new BigInteger("2").pow(n).subtract(BigInteger.ONE);   // long 도 오버플로우
    System.out.println(move);     // 최소 이동 횟수
    
    if (n <= 20) {   // 과정 출력 X
      sb = new StringBuilder();
      hanoi(n, 1, 2, 3); 
      System.out.println(sb.toString());
    }
    
    br.close();
  }
  
  void hanoi(int n, int s, int m, int e) {
    if (n == 1) {   // 1개 처리
      sb.append(s).append(" ").append(e).append("\n");
      return;
    }
    
    hanoi(n-1, s, e, m);    // n-1 개를 보조기둥으로 이동
    sb.append(s).append(" ").append(e).append("\n");    // 제일 아래 원판 목적지 이동
    hanoi(n-1, m, s, e);    // n-1 개 목적지 이동
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 하노이 탑
// 1 -> 1, 2 -> 3, 3 -> 7, 4 -> 15
// 하노이 탑 최소 이동 횟수 = 2^N - 1
// hanoi(원판 개수, 출발, 보조 기둥, 도착)
