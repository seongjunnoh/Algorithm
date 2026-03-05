import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    int[] second = new int[n+1];
    Set<Integer> firstSet = new HashSet<>();
    Set<Integer> secondSet = new HashSet<>();
    for (int i=1; i<=n; i++) {    // 초기화
      second[i] = Integer.parseInt(br.readLine());
      firstSet.add(i);
      secondSet.add(second[i]);
    }
    
    boolean[] flag = new boolean[n+1];    // flag[i] : 첫번쨰 줄의 i가 집합에 포함되는지 
    Arrays.fill(flag, true);    // 초기화 
    
    while (true) {
      boolean b = true;
      
      for (int i=1; i<=n; i++) {
        if (!flag[i]) continue;
        
        if (!firstSet.contains(second[i])) {
          flag[i] = false;    // flag 업데이트
          b = false;
        }
        
        if (!secondSet.contains(i)) {
          flag[i] = false;    // flag 업데이트
          b = false;
        }
      }
      
      if (b) break;   // 탈출
      
      // firstSet 업데이트
      firstSet = new HashSet<>();   // 객체 새로 만드는 거 말고 다른 방법 ??
      secondSet = new HashSet<>();
      for (int i=1; i<=n; i++) {
        if (!flag[i]) continue;
        
        firstSet.add(i);
        secondSet.add(second[i]);
      }
    }
    
    int cnt = 0;
    List<Integer> res = new ArrayList<>();
    for (int i=1; i<=n; i++) {
      if (flag[i]) {
        cnt++;
        res.add(second[i]);
      }
    }
    
    Collections.sort(res);
    
    // 출력
    System.out.println(cnt);
    for (int i=0; i<res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 뽑은 수들의 집합이 같음 -> 이때 집합 최대 크기 구하기
// 일단 다 넣어두고, 둘째 줄 집합에 없는 수를 첫째 줄 집합에서 제거 -> O(n제곱)

