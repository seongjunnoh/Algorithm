import java.util.*;
import java.io.*;

class Node {
  Map<Character, Node> child;
  boolean end;    // 끝지점 여부
  
  Node() {
    this.child = new HashMap<>();
    this.end = false;
  }
}

class Solution {
  
  Node root;
  
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      
      String[] arr = new String[n];
      root = new Node();
      for (int i=0; i<n; i++) {
        String str = br.readLine();
        
        arr[i] = str;   // 원본 문자열 저장
        add(str);   // 트라이에 add
      }
      
      boolean flag = false;
      for (int i=0; i<n; i++) {
        if (!select(arr[i])) {
          flag = true;
          break;
        }
      }
      
      if (flag) sb.append("NO").append("\n");
      else sb.append("YES").append("\n");
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  boolean select(String str) {
    Node node = root;
    
    for (int i=0; i<str.length(); i++) {
      char c = str.charAt(i);
      node = node.child.get(c);
      
      if (node.end && i != str.length() - 1) return false;
    }
    
    return true;
  }
  
  void add(String str) {
    Node node = root;
    
    for (int i=0; i<str.length(); i++) {
      char c = str.charAt(i);
      
      node.child.putIfAbsent(c, new Node());
      node = node.child.get(c);   // 부모 node 업데이트
    }
    
    node.end = true;    // 마지막 노드
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

/**
 * 한 번호가 다른 번호의 접두어가 되는 경우가 있는지 판단
 * 하나의 테케에서 n제곱 탐색 -> 시간초과
 * 
 * 트라이 알고리즘 -> 문자열 트리 만들기
 * 
 */