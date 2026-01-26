import java.io.*;
import java.util.*;

class Solution {
  
  long[] arr;
  long[] tree;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int q = Integer.parseInt(st.nextToken());
    arr = new long[n+1];
    
    st = new StringTokenizer(br.readLine());
    for (int i=1; i<=n; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }
    
    tree = new long[n * 4]; 
    init(1, n, 1);    // 세그먼트 트리 초기화
    
    StringBuilder sb = new StringBuilder();
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      if (x > y) {
        int temp = y;
        y = x;
        x = temp;
      }
      
      sb.append(calc(1, n, 1, x, y)).append("\n");    // 구간합 게산
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      update(1, n, 1, a, b);
    }
    
    System.out.println(sb);
    br.close();
  }
  
  // a번째 수를 b로 업데이트, 세그먼트 트리 수정
  void update(int s, int e, int node, int a, long b) {
    // 트리 범위 밖
    if (a < s || e < a) return; 
    
    if (s == e) {   // 리프노드(= a 노드)
      tree[node] = b;
      return;
    } 
    
    int mid = (s + e) / 2;
    if (a <= mid) {   // 왼쪽 자식
      update(s, mid, node*2, a, b);
    } else {  // 오른쪽 자식
      update(mid + 1, e, node*2 + 1, a, b);
    }
    
    tree[node] = tree[node*2] + tree[node*2 + 1];
  }
  
  // s~e 구간의 트리, l~r 구간합 계산
  long calc(int s, int e, int node, int l, int r) {
    // l~r 구간에 트리가 포함되지 않는 경우
    if (r < s || e < l) return 0;
    
    // 트리가 전부 포함
    if (l <= s && e <= r) return tree[node];
    
    // 부분만 포함
    int mid = (s + e) / 2;
    long lc = calc(s, mid, node*2, l, r);
    long rc = calc(mid + 1, e, node*2 + 1, l, r);
    
    return lc + rc;
  }
  
  void init(int s, int e, int node) {
    if (s == e) {
      tree[node] = arr[s];
      return;
    }
    
    int mid = (s + e) / 2;
    init(s, mid, node*2);   // 왼쪽 자식
    init(mid+1, e, node*2 + 1);   // 오른쪽 자식
    
    tree[node] = tree[node*2] + tree[node*2 + 1];
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

/**
 * 세그먼트 트리로 구간합 관리
 */
