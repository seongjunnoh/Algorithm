import java.io.*;
import java.util.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    Arrays.sort(arr);   // 오름차순 정렬
    
    // 2의 거듭제곱 미리 계산 & 모듈러 적용
    long MOD = 1_000_000_007;
    long[] pow = new long[n];
    pow[0] = 1;
    for (int i=1; i<n; i++) {
      pow[i] = (pow[i-1] * 2) % MOD;
    }
    
    long maxSum = 0;
    long minSum = 0;
    for (int i=0; i<n; i++) {
      long maxCount = pow[i];   // arr[i]가 최대가 되는 부분집합 개수
      long minCount = pow[n-1-i];   // arr[i]가 최소가 되는 부분집합 개수
      
      maxSum = (maxSum + (arr[i] * maxCount) % MOD) % MOD;
      minSum = (minSum + (arr[i] * minCount) % MOD) % MOD;
    }
    
    long res = (maxSum - minSum + MOD) % MOD;   // 음수 결과 방지
    System.out.println(res);
    br.close();
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
    }
}

// 모든 조합에 대해서 [max - min] 합 구하기
// -> 정렬 & 이중 루프 돌면서 [i, j] 구간에 대해 직접 계산 (시간초과)
// 모든 조합에 대해서 max 합 - 모든 조합에 대해서 min 합
// -> 정렬 & 루프 한번만 돌면서, i번째 요소가 최댓값인 구간 개수, 최솟값인 구간 개수 계산


