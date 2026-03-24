import java.util.*;
import java.io.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    String[] origin = new String[n];      // 원본 배열
    String[] arr = new String[n];
    for (int i=0; i<n; i++) {
      origin[i] = br.readLine();
      arr[i] = origin[i];
    }
    
    Arrays.sort(arr);   // 사전순 정렬
    
    Set<String> set = new HashSet<>();    // max 접두어 Set
    int maxLen = 0;
    for (int i=0; i<n-1; i++) {
      String s1 = arr[i];
      String s2 = arr[i+1];
      
      int len = calc(s1, s2);
      
      if (maxLen < len) {
        maxLen = len;
        
        set.clear();    // 기존 Set 비우기
        set.add(s1.substring(0, maxLen));
      } else if (maxLen == len) {   // set에 s1, s2 도 add
        set.add(s1.substring(0, maxLen));
      }
    }
    
    String[] res = new String[2];
    String validPre = "";
    int idx = 0;
    for (int i=0; i<n; i++) {
      if (origin[i].length() < maxLen) continue;
      
      String curPre = origin[i].substring(0, maxLen);
      
      if (set.contains(curPre)) {
        if (idx == 0) validPre = curPre;
        else {
          // validPre 와 같아야 정답
          if (!validPre.equals(curPre)) continue;
        }  
          
         
        res[idx++] = origin[i];
        
        if (idx == 2) break;
      }
    }
    
    System.out.println(res[0]);
    System.out.println(res[1]);
  }
  
  int calc(String s1, String s2) {    // 겹치는 접두어 길이 구하기
    int minLen = Math.min(s1.length(), s2.length());
    
    int res = 0;
    for (int i=0; i<minLen; i++) {
      if (s1.charAt(i) != s2.charAt(i)) break;
      
      res++;
    }
    
    return res;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 정렬 & 인접한 문자열끼리 비교해서 정답 후보 찾기
// O(nlogn * l)