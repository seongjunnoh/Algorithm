class Solution {
    public int solution(int[] a) {  
        int answer = 1;     // 가장 작은 수는 무조건 최후에 남는다 (항상 큰 풍선만 터트릴 경우)
        
        boolean[] left = new boolean[a.length];
        int minL = 1_000_000_000;
        for (int i=0; i<a.length; i++) {
            if (minL < a[i]) left[i] = true;        // i 왼쪽에 더 작은 값이 있다
            else minL = a[i];       // i 왼쪽에 더 작은 값이 없다
        }
        
        boolean[] right = new boolean[a.length];
        int minR = 1_000_000_000;
        for (int i=a.length - 1; i>=0; i--) {
            if (minR < a[i]) right[i] = true;        // i 오른쪽에 더 작은 값이 있다
            else minR = a[i];       // i 오른쪽에 더 작은 값이 없다
        }
        
        for (int i=0; i<a.length; i++) {
            if ((left[i] && !right[i]) || (!left[i] && right[i])) answer++;
        }
        
        return answer;
    }
}

// 현재 수보다 작은 수가 양 옆에 있으면 -> X, 한쪽에만 있으면 -> O