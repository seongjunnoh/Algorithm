import java.io.*;
import java.util.*;

class Solution {
    int n;
    int[] dp;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[n];  // dp[i] : 길이가 i+1 인 증가 부분수열의 최소 끝 값
        dp[0] = arr[0];
        int len = 1;    // 현재까지 찾은 LIS의 길이
        
        for (int i=1; i<n; i++) {
            if (dp[len - 1] < arr[i]) dp[len++] = arr[i];     // 추가
            else dp[bs(arr[i], 0, len - 1)] = arr[i];     // 교체
        }
        
        System.out.println(n - len);
    }
    
    int bs(int x, int l, int r) {     // dp에서 x이상이 처음 나오는 인덱스 반환 -> lower bound
        while (l <= r) {
            int mid = (l+r) / 2;
            
            if (dp[mid] >= x) r = mid - 1;
            else l = mid + 1;
        }
        
        return l;
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
	    Solution s = new Solution();
	    s.solution();
	}
}

/**
 * LIS, 이분탐색
 */