import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n = Integer.parseInt(br.readLine());
    
    int[] arr = new int[n+1];   // 1 based idx
    int[] sum = new int[n+1];   // 누적합 (누적 실수 횟수)
    st = new StringTokenizer(br.readLine());
    for (int i=1; i<=n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    // sum 업데이트 -> 현재 악보가 다음 악보보다 어렵다면, 현재 악보를 실수한다
    for (int i=1; i<n; i++) {
      if (arr[i] > arr[i+1]) sum[i] = sum[i-1] + 1;
      else sum[i] = sum[i-1];
    }
    sum[n] = sum[n-1];    // 마지막 원소
    
    // for (int i=1; i<=n; i++) {
    //   System.out.print(sum[i] + " ");
    // }
    // System.out.println();
    
    int q = Integer.parseInt(br.readLine());
    
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<q; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      // 악보 마지막에서는 실수 X -> [x, y-1] 범위에서만 판단
      sb.append(sum[y-1] - sum[x-1]).append("\n");
    }
    
    System.out.println(sb.toString());
  }
}

// O(n*q) -> 시간초과
// dp[][] -> 이것도 O(n제곱), 시간초과
// 누적합 알고리즘 -> 1번 악보부터 시작, 누적된 실수 횟수 계속 계산
// i j 에서의 실수횟수 -> sum[j] - sum[i-1] 로 O(1) 해결 가능