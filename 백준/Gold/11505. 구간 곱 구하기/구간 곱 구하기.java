import java.util.*;
import java.io.*;

class Solution {
    
    final int devide = 1_000_000_007;
    long[] arr, tree;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        arr = new long[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        tree = new long[4*n];
        makeTree(1, n, 1);
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if (a == 1) {
                change(1, n, 1, b, c);
            } else {
                sb.append(calc(1, n, 1, b, c)).append("\n");
            }
        }
        
        System.out.println(sb);
        br.close();
    }
    
    // [s,e] 트리에서 [l,r] 의 구간곱 계산
    long calc(int s, int e, int node, int l, int r) {
        if (r < s || e < l) {
            return 1;   // 쓰레기 값
        }
        
        if (l <= s && e <= r) {
            return tree[node];
        }
        
        int mid = (s+e) / 2;
        long lc = calc(s, mid, node*2, l, r);
        long rc = calc(mid + 1, e, node*2 + 1, l, r);
        
        return (lc * rc) % devide;
    }
    
    // arr[idx] 를 val 로 변경
    void change(int s, int e, int node, int idx, int val) {
        if (idx < s || e < idx) {   // 트리 범위 밖인 경우
            return;
        }
        
        if (s == e) {   // 리프 노드 변경
            tree[node] = val % devide;
            return;
        }
        
        int mid = (s+e) / 2;    
        if (idx <= mid) change(s, mid, node*2, idx, val);   // 왼쪽 자식 변경
        else change(mid + 1, e, node*2 + 1, idx, val);  // 오른쪽 자식 변경
        
        tree[node] = (tree[node*2] * tree[node*2 + 1]) % devide;  // node 변경
    }
    
    // [s,e] 구간의 곱을 devide로 나눈 나머지
    void makeTree(int s, int e, int node) {
        if (s == e) {
            tree[node] = arr[s];
            return;
        }
        
        int mid = (s+e) / 2;
        makeTree(s, mid, node*2);   // 왼쪽 자식
        makeTree(mid + 1, e, node*2 + 1);   // 오른쪽 자식
        
        tree[node] = (tree[node*2] * tree[node*2 + 1]) % devide;
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 세그먼트 트리로 구간 곱 관리
 * 
 */