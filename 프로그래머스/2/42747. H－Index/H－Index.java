import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] c) {  // 논문 인용 횟수
        Arrays.sort(c);     // 정렬
        
        for (int i=0; i<c.length; i++) {
            int count = c.length - i;   // 현재보다 인용횟수가 크거나 같은 논문 개수
            int cur = c[i]; // 현재 논문 인용 횟수
            
            if (cur >= count) {     // 현재
                return count;
            }
        }
    
        return 0;
    }
}
