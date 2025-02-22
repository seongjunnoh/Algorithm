class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        
        int l = 1;
        int r = 300_000;
        while (l <= r) {
            int mid = (l + r) / 2;      // 현재 레벨
            
            long sum = 0;
            for (int i=0; i<n; i++) {
                if (diffs[i] <= mid) sum += times[i];
                else {
                    if (i == 0) sum += (diffs[i] - mid) * times[i] + times[i];
                    else sum += (diffs[i] - mid) * (times[i] + times[i-1]) + times[i];
                }    
            }
            
            if (sum <= limit) r = mid - 1;      // 계속 레벨을 줄여가자 (lower bound)
            else l = mid + 1;
        }
        
        return l;
    }
}