import java.io.*;
import java.util.*;

class Solution {
    int n;
    int[] arr;
    int[] tail;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        tail = new int[n+1];  // tail[i] : 길이가 i 인 증가하는 부분수열들 중, 가장 크기가 작은 꼬리값
        tail[1] = arr[1];
        int len = 1;    // LIS 의 길이
        
        for (int i=2; i<=n; i++) {
            if (arr[i] > tail[len]) {
                tail[++len] = arr[i];
            } else {    
                int idx = findIdx(arr[i], len);
                tail[idx] = arr[i];
            }
        }
        
        System.out.println(len);
        br.close();
    }
    
    int findIdx(int target, int len) { // tail 배열 중, target의 lower bound 찾기
        int l = 1;  // tail 배열은 1부터
        int r = len;    // len 까지
        int mid;
        
        while (l <= r) {
            mid = (l + r) / 2;
            
            if (tail[mid] < target) l = mid + 1;
            else r = mid - 1;
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
 * 연결되어 있는 포트들의 번호들 중 가장 긴 증가하는 부분수열 구하기
 * -> n이 크고, 수열의 크기를 구하면 되므로 nlogn 방식으로 접근
 * 
 */