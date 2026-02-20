import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
  int idx;    // 기존 인덱스
  int num;    // 숫자 값
  
  Node (int idx, int num) {
    this.idx = idx;
    this.num = num;
  }
  
  @Override
  public int compareTo(Node n) {
    if (this.num == n.num) return this.idx - n.idx;
    return this.num - n.num;    // num 기준 오름차순 정렬
  }
}

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Node[] arr = new Node[n];
    
    for (int i=0; i<n; i++) {
      arr[i] = new Node(i, Integer.parseInt(br.readLine()));
    }
    
    Arrays.sort(arr);   // num 기준 오름차순 정렬
    
    int maxDiff = 0;    // 왼쪽으로 이동한 거리의 max 값 == 버블소트 횟수
    for (int i=n-1; i>=0; i--) {
      maxDiff = Math.max(maxDiff, arr[i].idx - i);    // 기존 idx -> 현재 idx
    }
    
    System.out.println(maxDiff + 1);
    br.close();
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
    }
}

// 10 1 5 2 3
// 1) 1 5 2 3 10
// 2) 1 2 3 5 10
// 3) 1 2 3 5 10 -> 3 출력

// 직접 n제곱 버블소트 구현하면 시간초과
// 버블소트 횟수는 [정렬 전 인덱스 - 정렬 후 인덱스]의 max 값

