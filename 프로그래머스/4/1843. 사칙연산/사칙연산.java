import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1;     // 숫자 개수
        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];
        
        for (int i=0; i<n; i++) {
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }
        
        for (int i=0; i<n; i++) {
            maxDp[i][i] = Integer.parseInt(arr[i*2]);       // dp 초기화
            minDp[i][i] = Integer.parseInt(arr[i*2]);
        }
        
        for (int len = 1; len < n; len++) {
            for (int l=0; l<n; l++) {
                int r = l + len;
                if (r >= n) continue;
                
                for (int k=l; k<r; k++) {
                    String op = arr[k*2 + 1];       // 현재 operator
                    
                    if (op.equals("+")) {
                        maxDp[l][r] = Math.max(maxDp[l][r], maxDp[l][k] + maxDp[k+1][r]);
                        minDp[l][r] = Math.min(minDp[l][r], minDp[l][k] + minDp[k+1][r]);
                    } else {
                        maxDp[l][r] = Math.max(maxDp[l][r], maxDp[l][k] - minDp[k+1][r]);
                        minDp[l][r] = Math.min(minDp[l][r], minDp[l][k] - maxDp[k+1][r]);
                    }
                }
            }
        }
        
//         for (int i=0; i<n; i++) {
//             for (int j=0; j<n; j++) {
//                 System.out.print(maxDp[i][j] + ", ");
//             }
//             System.out.println();
//         }
        
//         for (int i=0; i<n; i++) {
//             for (int j=0; j<n; j++) {
//                 System.out.print(minDp[i][j] + ", ");
//             }
//             System.out.println();
//         }
        
        return maxDp[0][n-1];
    }
}

// 전체 경우 모두 확인
// 중간 계산 결과 메모이제이션 -> 최대/최소값 나눠서 저장