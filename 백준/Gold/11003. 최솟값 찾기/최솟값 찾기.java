import java.util.*;
import java.io.*;

class Pair {
    int idx;
    int val;
    
    Pair(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        if (n == 1) {   // 예외상황
            System.out.println(arr[1]);
            return;
        }
        
        Deque<Pair> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i=1; i<=n; i++) {
            if (i-l+1 <= 1) {
                add(dq, i, arr[i]);
                sb.append(dq.peekFirst().val).append(" ");
                
                continue;
            } 
            
            // dq : [i-l+1, i] 범위만 존재
            if (!dq.isEmpty() && dq.peekFirst().idx < i-l+1) {
               dq.pollFirst();
            }
            
            add(dq, i, arr[i]);
            sb.append(dq.peekFirst().val).append(" ");
        }
        
        System.out.println(sb);
        br.close();
    }
    
    void add(Deque<Pair> dq, int i, int val) {
        while (!dq.isEmpty() && dq.peekLast().val > val) {
            dq.pollLast();
        }
        
        Pair p = new Pair(i, val);
        dq.addLast(p);
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 세그먼트 트리로 구간의 최솟값 관리
 * O(nlogn)
 * --------------------------
 * n은 최대 500만 -> O(nlogn) 도 시간초과
 * 
 * deque로 윈도우 내의 최솟값 관리
 */