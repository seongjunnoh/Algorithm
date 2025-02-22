import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] count = new int[n];       // count[i] : i가 몇번 등장했는지
        for (int i=0; i<n; i++) {
            count[a[i]]++;
        }
        
        int max = 0;        
        for (int num = 0; num < count.length; num++) {
            if (count[num] * 2 <= max) continue;       
            
            int cur = 0;        // num을 기준으로 스타수열 만들 경우, 스타수열의 길이
            for (int i=0; i<n-1; i++) {     
                if ((a[i] == num && a[i] != a[i+1]) || (a[i+1] == num && a[i] != a[i+1])) {
                    // a[i], a[i+1] 을 한쌍으로 묶을 수 있다
                    cur += 2;
                    i++;        // 다음쌍으로
                }
            }
            
            max = Math.max(max, cur);
        }
        
        return max;
    }
}