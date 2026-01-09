import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        int l = 1;
        int r = k;
        while (l <= r) {
            int x = (l+r) / 2;    // x 값
            int count = count(n, x);    // a배열 중, x 보다 작거나 같은 수의 개수
            
            if (count >= k) r = x-1;    // lower bound
            else l = x+1;
        }
        
        System.out.println(l);
        br.close();
    }
    
    int count(int n, int x) {
        int cnt = 0;
        for (int i=1; i<=n; i++) {
            cnt += Math.min(x/i, n);    
        }
        
        return cnt;
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 정렬된 B 배열에서 B[k] : k번째로 큰 수
 * B[k] = x 라 한다면, A 배열의 각 행에서 x보다 작거나 같은 수의 count 총합은 k이다
 * 
 * x값은 1부터 k까지의 수 중 매개변수 탐색으로 찾기
 * -> lower bound 가 b[k] 값
 */
