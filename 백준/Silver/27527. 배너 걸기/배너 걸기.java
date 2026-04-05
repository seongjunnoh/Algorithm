import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    int num = 9 * m / 10;   // 기준 숫자 구하기
    if ((9 * m) % 10 != 0) num++;
    
    Map<Integer, Integer> map = new HashMap<>();    // count map
    
    // 윈도우 초기화
    for (int i=0; i<m; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
      
      if (map.get(arr[i]) >= num) {   // 현재 윈도우에서 YES인 경우
        System.out.println("YES");
        return;
      }
    }
    
    for (int i=m; i<n; i++) {
      map.put(arr[i-m], map.get(arr[i-m]) - 1);   // 윈도우에서 요소 제거
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);   // 윈도우에 요소 추가
      
      if (map.get(arr[i]) >= num) {   // 현재 윈도우에서 YES인 경우
        System.out.println("YES");
        return;
      }
    }
    
    System.out.println("NO");   // 탐색 마침 -> NO
    br.close();
  }
}

// O(n) 으로 해결해야한다 -> 슬라이딩 윈도우
// 윈도우에 포함된 arr 요소들의 값이, 몇개씩 존재하는지 알고 있어야 한다 -> 해시 맵으로 관리
// 윈도우에 지금 추가되는 arr 요소의 count만 확인하면 된다 (나머지 count 값은 임계치를 넘지 않음이 보장됨)