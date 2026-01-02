import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long[] arr = new long[n+1];   // 원본 숫자
        long[] sum = new long[n+1];   // 원본 숫자의 누적합  
        for (int i=1; i<=n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            sum[i] += sum[i-1] + arr[i];
        }
        
        long[] d = new long[n+1];   // 변화량
        Set<Integer> set = new HashSet<>();     // 숫자 변경이 발생한 인덱스 set
        for (int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if (a == 1) {   // 교환
                long num = arr[b] + d[b];    // 현재 b번째 수
                d[b] += c - num;    // 변화량 업데이트
                set.add(b);
            } else {    // 출력
                long res = sum[(int) c] - sum[b-1];   // 초기값
                for (int idx : set) {
                    if (idx < b || c < idx) continue;
                    res += d[idx];
                }
                
                sb.append(res).append("\n");
            }
        }
        
        System.out.println(sb);
        br.close();
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 
 * 
 */