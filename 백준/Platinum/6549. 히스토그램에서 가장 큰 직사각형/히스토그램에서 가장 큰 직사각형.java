import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            if (n == 0) break;
            
            int[] h = new int[n];
            for (int i=0; i<n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }
            
            sb.append(calc(0, n-1, h)).append("\n");    // 최대넓이 계산
        }
        
        System.out.println(sb);
        br.close();
    }
    
    long calc(int l, int r, int[] h) {      // l~r 구간의 최대넓이 계산 -> 분할 정복
        if (l == r) {
            return (long) h[l];
        }
        
        int mid = (l+r) / 2;
        long leftW = calc(l, mid, h);  // 왼쪽 넓이
        long rightW = calc(mid + 1, r, h); // 오른쪽 넓이
        
        long max = Math.max(leftW, rightW);     // 왼 vs 오른 넓이
        
        // l~r 구간에서의 최대 넓이 계산
        return Math.max(max, calcRange(l, r, mid, h));
    }
    
    long calcRange(int l, int r, int mid, int[] h) {    // mid부터 시작해서 l~r 구간의 최대넓이 계산
        int leftEnd = mid;      // 구간 왼쪽 끝
        int rightEnd = mid;     // 구간 오른쪽 끝
        
        int height = h[mid];    // 구간 내 직사각형 높이
        long maxW = (long) h[mid];     // mid에서 시작
        
        while (l < leftEnd && rightEnd < r) {
            if (h[leftEnd - 1] < h[rightEnd + 1]) {  // 오른쪽으로 확장
                rightEnd++;
                height = Math.min(height, h[rightEnd]);
            } else {    // 왼쪽으로 확장
                leftEnd--;
                height = Math.min(height, h[leftEnd]);
            }
            
            // maxW 업데이트
            maxW = Math.max(maxW, (long) height * (rightEnd - leftEnd + 1));
        }
        
        while (rightEnd < r) {      // r 까지 마저 탐색
            rightEnd++;
            height = Math.min(height, h[rightEnd]);
            maxW = Math.max(maxW, (long) height * (rightEnd - leftEnd + 1));
        }
        
        while (l < leftEnd) {       // l 까지 마저 탐색
            leftEnd--;
            height = Math.min(height, h[leftEnd]);
            maxW = Math.max(maxW, (long) height * (rightEnd - leftEnd + 1));
        }
        
        return maxW;
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* N제곱 -> 시간 초과
 * 
 * 분할정복 & 투포인터 -> nlogn
 */