import java.io.*;
import java.util.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    
    int maxLen = 0;
    for (int i=0; i<input.length(); i++) {
      String subStr = input.substring(i);   // i부터 시작하는 부분 문자열
      maxLen = Math.max(maxLen, find(subStr));
    }
    
    System.out.println(maxLen);
    br.close();
  }
  
  int find(String s) {
    // table[i] : s[0]~s[i] 까지 부분 문자열에서, 접두사/접미사 최대 길이
    int[] table = new int[s.length()];
    int max = 0;    // s에서의 가장 긴 반복 길이
    int preEnd = 0;   // 접두사 끝지점
    
    for (int sufEnd=1; sufEnd<s.length(); sufEnd++) {    // sufEnd : 접미사의 끝지점
      while (preEnd > 0 && s.charAt(sufEnd) != s.charAt(preEnd)) {
        // preEnd-1 -> 마지막으로 일치한 접두사 끝지점
        // [0, preEnd-1](접두사) == [?, sufEnd-1](접미사)
        // table[preEnd-1] -> 접두사 블록에서의 데칼코마니 최대 길이
        // == 접미사 블록에서의 데칼코마니 최대 길이
        // 따라서 preEnd를 table[preEnd-1] 로 업데이트
        preEnd = table[preEnd - 1];
      }
    
      if (s.charAt(sufEnd) == s.charAt(preEnd)) {   // 접두사 끝지점 == 접미사 끝지점
        preEnd++;
        table[sufEnd] = preEnd;   // table update
        max = Math.max(max, preEnd);
      }
    }
    
    return max;
  }
}

public class Main {
    public static void main(String[] args) throws IOException {
      Solution s = new Solution();
      s.solution();
    }
}

// 두 부분 문자열은 겹쳐도 ok
// 두 번 이상 나오는 부분 문자열 중, 가장 길이가 긴 것을 구하라
// KMP 알고리즘 이란게 있다네,,
