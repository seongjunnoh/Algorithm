import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
  void solution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str1 = br.readLine();
    String str2 = br.readLine();
    
    int n = Math.max(str1.length(), str2.length());
    int[][] dp = new int[n][n];
    
    // dp 초기화 -> 0행, 0열 세팅
    for (int j=0; j<str2.length(); j++) {
      if (str1.charAt(0) == str2.charAt(j)) {
        dp[0][j] = 1;
      }
    }
    
    for (int i=0; i<str1.length(); i++) {
      if (str1.charAt(i) == str2.charAt(0)) {
        dp[i][0] = 1;
      }
    }
    
    for (int i=1; i<str1.length(); i++) {
      for (int j=1; j<str2.length(); j++) {
        if (str1.charAt(i) == str2.charAt(j)) {
          dp[i][j] = dp[i-1][j-1] + 1; 
        }
      }
    }
    
    // dp 배열 중 최댓값 찾기
    int max = 0;
    for (int i=0; i<str1.length(); i++) {
      for (int j=0; j<str2.length(); j++) {
        max = Math.max(max, dp[i][j]);
      }
    }
    
    System.out.println(max);
    br.close();
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    Solution s = new Solution();
    s.solution();
  }
}

// 두 문자열에 포함된 부분 문자열 중 가장 긴 것의 길이 구하기
// 완전탐색은 O(n 3제곱)
// LCS 알고리즘 -> dp[i][j] : 문자열A의 i, 문자열B의 j를 마지막 문자로 가지는 부분 문자열의 최대 길이
