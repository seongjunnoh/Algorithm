import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] c) {  // 논문 인용 횟수
        Arrays.sort(c);     // 정렬
        
        int answer = 0;
        for (int i=0; i<=c.length; i++) {
            int j;
            for (j=0; j<c.length; j++) {
                if (i > c[j]) continue;
                break;
            }
            
            // j : i번 미만 인용된 논문 개수, 전체-j : i번 이상 인용된 논문 개수
            if (j < i && c.length - j >= i) {
                answer = i;
            }
            
            // System.out.println("i = " + i + ", j = " + j + ", answer = " + answer);
        }
    
        return answer;
    }
}
