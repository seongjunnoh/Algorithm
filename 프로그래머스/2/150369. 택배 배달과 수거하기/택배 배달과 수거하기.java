import java.util.*;

class Solution {
    
    int[] d, p;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        d = new int[n]; p = new int[n];
        boolean dFlag = true, pFlag = true;
        
        for (int i=0; i<n; i++) {
            d[i] = deliveries[i];
            if (d[i] != 0) dFlag = false;
        }
        for (int i=0; i<n; i++) {
            p[i] = pickups[i];
            if (p[i] != 0) pFlag = false;
        }
        
        if (dFlag && pFlag) return 0;   // 예외처리 -> 모두 0인 경우
        
        int dIdx = n-1, pIdx = n-1; // 시작 위치
        int idx;
        long answer = 0;
        
        while (true) {
            if (dIdx == 0 && d[dIdx] == 0 && pIdx == 0 && p[pIdx] == 0) {
                return answer;  // 방문할 집 없는 경우
            }
            
            idx = Math.max(dIdx, pIdx);
            answer += 2 * (idx + 1);
            
            dIdx = findIdx(dIdx, cap, d);
            pIdx = findIdx(pIdx, cap, p);
        }
    }
    
    int findIdx(int idx, int cap, int[] arr) {
        int sum = 0;
        
        for (int i=idx; i>=0; i--) {
            if (sum + arr[i] > cap) {   // i집 상자 전부 처리 X
                arr[i] -= cap - sum;
                return i;
            }
            
            sum += arr[i];
            arr[i] = 0;
        }
        
        return 0;
    }
}