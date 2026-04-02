import java.util.*;
import java.io.*;

class Node {
  int bit;    // 0/1
  Node[] child;
  int w;    // 판단 데이터 끝일 경우 1/-1
  
  Node (int bit, Node[] child) {
    this.bit = bit;
    this.child = child;
    this.w = 0;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int b = Integer.parseInt(br.readLine());
    
    Node root = new Node(-1, new Node[2]);    // 루트 노드
    int len = 0;    // 판단 데이터 길이
    
    for (int i=0; i<b; i++) {
      String str = br.readLine();   // 차고 낮음 판단 데이터
      len = str.length();
      append(root, str, -1);
    }
    
    for (int i=0; i<b; i++) {
      String str = br.readLine();   // 차고 높음 판단 데이터
      append(root, str, 1);
    }
    
    int n = Integer.parseInt(br.readLine());
    
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<n; i++) {
      String data = br.readLine();    // 실시간 데이터
      
      int c = find(root, data);
      
      if (c > 0) {
        sb.append("LOW ").append(c).append("\n");
      } else if (c < 0) {
        sb.append("HIGH ").append(Math.abs(c)).append("\n");
      } else {
        sb.append("GOOD").append("\n");
      }
    }
    
    System.out.println(sb.toString());
    br.close();
  }
  
  static int find(Node root, String str) {
    int res = 0;
    
    for (int j=0; j<str.length(); j++) {    // j부터 시작하는 부분 문자열을 트라이에서 검색
      Node current = root;
      
      for (int k=j; k<str.length(); k++) {
        int bit = Integer.parseInt(String.valueOf(str.charAt(k)));
        
        if (current.child[bit] == null) break;
        
        current = current.child[bit];
        res += current.w;
      }
    }
    
    return res;
  }
  
  static void append(Node root, String str, int w) {
    Node current = root;
    
    for (int j=0; j<str.length(); j++) {
      int bit = Integer.parseInt(String.valueOf(str.charAt(j)));
      
      if (current.child[bit] == null) {
        Node node = new Node(bit, new Node[2]);
        
        current.child[bit] = node;
        current = node;
      } else {
        current = current.child[bit];
      }
      
      if (j == str.length() - 1) {    // 판단 데이터 끝 부분
        current.w = w;
      }
    }
  }
}

// 문자열 매칭 여부 빠르게 진행해야함 -> 트라이 활용
// 판단 데이터를 모두 트라이에 저장, 실시간 데이터의 모든 문자에 대해서 부분 문자열 비교
