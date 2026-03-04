import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    // 감소하는 수 미리 계산
    List<Long> list = new ArrayList<>();
    for (int i=0; i<=9; i++) {
      make(i, 1, list);
    }
  
    Collections.sort(list);   // 오름차순 정렬
    
    if (list.size() <= n) {
      System.out.println("-1");  
    } else {
      System.out.println(list.get(n));  
    }
    br.close();
  }
  
  void make(long num, int len, List<Long> list) {
    if (len > 10) return;
    
    list.add(num);
    
    long lastDigit = num % 10;
    for (int i=0; i<lastDigit; i++) {
      make(num * 10 + i, len + 1, list);    // 마지막 자릿수보다 작은 수 이어붙이기
    }
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// n번째 감소하는 수 출력
// 0 -> 0, 1 -> 1, ,,,, 10 -> 10
// 20 -> 11, 21 -> 12
// 30 -> 13, 31 -> 14, 32 -> 15
// 0~1,000,000 번째 감소하는 수를 미리 전부 계산 ??
// 가장 작은 감소하는 수 : 0, 가장 큰 감소하는 수 : 9876543210

