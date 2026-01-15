import java.io.*;
import java.util.*;

class Solution {
    
    int[] arr, tree;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        tree = new int[4 * n];
        makeTree(1, n, 1);
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            sb.append(find(1, n, 1, a, b)).append("\n");
        }
        
        System.out.println(sb);
        br.close();
    }
    
    // s~e 구간의 트리에서 l~r 구간의 최솟값 찾기
    int find(int s, int e, int node, int l, int r) {
        if (r < s || e < l) {
            return Integer.MAX_VALUE;
        }
        
        if (l <= s && e <= r) {
            return tree[node];
        }
        
        int mid = (s+e) / 2;
        int lc = find(s, mid, node*2, l, r);
        int rc = find(mid+1, e, node*2 + 1, l, r);
        
        return Math.min(lc, rc);
    }
    
    // s~e 구간의 트리
    void makeTree(int s, int e, int node) {
        if (s == e) {
            tree[node] = arr[s];
            return;
        }
        
        int mid = (s+e) / 2;
        makeTree(s, mid, node*2);
        makeTree(mid+1, e, node*2 + 1);
        
        tree[node] = Math.min(tree[node*2], tree[node*2 + 1]);      // 구간의 최솟값
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
 */
