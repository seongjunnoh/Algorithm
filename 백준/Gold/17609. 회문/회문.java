import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<n; i++) {
      String line = br.readLine();
      sb.append(check(line, 0, line.length() - 1, 0)).append("\n");
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  int check(String s, int l, int r, int cnt) {
    while (l <= r) {
      char lc = s.charAt(l);
      char rc = s.charAt(r);
      
      if (lc == rc) {
        l++;
        r--;
      } else {    // 유사 회문 or 둘다 X
        if (cnt == 1) return -1;    // 이미 1번 기회 사용한 경우 -> return
      
        int i1 = check(s, l+1, r, cnt + 1);
        int i2 = check(s, l, r-1, cnt + 1);
        
        if (i1 == 0 || i2 == 0) return 1;   // 유사 회문
        else return 2;    // 둘다 X 
      }
    }
    
    return 0;   // 무사 탈출 -> 회문
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 회문, 유사회문, 해당 X 판별
// 투 포인터로 판단
