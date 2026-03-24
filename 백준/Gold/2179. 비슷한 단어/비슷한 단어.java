import java.util.*;
import java.io.*;

class Node {
  Map<Character, Node> child;
  int firstIdx;     // 해당 노드 가장 먼저 탐색한 문자열 인덱스
  
  Node() {
    this.child = new HashMap<>();
    this.firstIdx = -1;
  }
}

class Solution {
  
  Node root;
  int maxLen;
  int idx1, idx2;   // 정답 문자열 인덱스
  String[] arr;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    root = new Node();   // 루트 노드
    
    maxLen = 0;
    idx1 = -1;
    idx2 = -1;
    arr = new String[n];
    for (int i=0; i<n; i++) {
      arr[i] = br.readLine();
      add(arr[i], i);
    }
    
    System.out.println(arr[idx1]);
    System.out.println(arr[idx2]);
    br.close();
  }
  
  void add(String str, int strIdx) {
    Node cur = root;
    
    for (int i=0; i<str.length(); i++) {
      char c = str.charAt(i);
      
      if (!cur.child.containsKey(c)) {
        Node node = new Node();
        node.firstIdx = strIdx;
        
        cur.child.put(c, node);
        
        cur = node;   // 다음 노드로 이동
      } else {      // 이미 해당 노드가 존재하는 경우
        int preIdx = cur.child.get(c).firstIdx;
        int curLen = i+1;
      
        if (curLen > maxLen) {
          maxLen = curLen;
          idx1 = preIdx;
          idx2 = strIdx;
        } else if (curLen == maxLen) {    // 문제 조건
          if (idx1 > preIdx) {
            idx1 = preIdx;
            idx2 = strIdx;
          } else if (idx1 == preIdx && idx2 > strIdx) {
            idx2 = strIdx;
          }
        }
        
        cur = cur.child.get(c);   // 다음 노드로 이동
      }
    }
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 완전 탐색 -> O(n제곱 * 100) -> 시간 초과
// 트라이 자료구조 활용