import java.util.*;
import java.io.*;

class Solution {
    int n;
    int[] cd;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] ab = new int[n * n];
        int idx = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ab[idx++] = a[i] + b[j];
            }    
        }
        
        cd = new int[n * n];
        idx = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                cd[idx++] = c[i] + d[j];
            }    
        }
        
        Arrays.sort(cd);    // cd 정렬
        
        long ans = 0;   // 정답 개수는 long 형이어야함 -> 최대 정답 개수 = n^4
        for (int i=0; i<n * n; i++) {
            int target = -1 * ab[i];
            
            int loIdx = lowerbound(target);
            int upIdx = upperbound(target);
            ans += upIdx - loIdx;
        }
        
        System.out.println(ans);
        br.close();
    }
    
    int lowerbound(int target) {
        int l = 0;
        int r = n*n - 1;
        int mid;
        while (l <= r) {
            mid = (l+r) / 2;
            if (cd[mid] >= target) r = mid - 1;
            else l = mid + 1;
        }
        
        return l;
    }
    
    int upperbound(int target) {
        int l = 0;
        int r = n*n - 1;
        int mid;
        while (l <= r) {
            mid = (l+r) / 2;
            if (cd[mid] <= target) l = mid + 1;
            else r = mid - 1;
        }
        
        return l;
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 완전탐색 -> 시간초과
 * 
 * (a,b)의 모든 합, (c,d)의 모든 합을 각각 배열로 저장
 * (a,b) 배열에 대하여 이분탐색으로 (c,d) 에서 값 찾기
 */