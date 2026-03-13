import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n = Integer.parseInt(br.readLine());
    List<Integer> crain = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i=0; i<n; i++) {
      crain.add(Integer.parseInt(st.nextToken()));
    }
    Collections.sort(crain, Collections.reverseOrder());    // 내림차순 정렬
    
    int m = Integer.parseInt(br.readLine());
    List<Integer> box = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i=0; i<m; i++) {
      box.add(Integer.parseInt(st.nextToken()));
    }
    Collections.sort(box, Collections.reverseOrder());    // 내림차순 정렬
    
    if (crain.get(0) < box.get(0)) {    // 예외처리
      System.out.println("-1");
      br.close();
      return;
    }
    
    int time = 0;
    LinkedList<Integer> list = new LinkedList<>(box);   // 정렬된 box 연결 리스트
    
    while (!list.isEmpty()) {
      Iterator<Integer> iter = list.iterator();
      int i = 0;
      
      while (iter.hasNext() && i < n) {
        int w = iter.next();
        
        if (crain.get(i) >= w) {
          iter.remove();    // 해당 박스 이동 완료
          i++;
        }
      }
      
      time++;
    }
    
    System.out.println(time);
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
 * 크레인 : 1분에 박스 하나씩 이동 가능
 * 그리디하게 접근 -> O(m제곱)
 * 
 */