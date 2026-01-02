import java.io.*;
import java.util.*;

class Solution {
    long[] arr;
    long[] tree;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        arr = new long[n+1];   // 원본 숫자
        for (int i=1; i<=n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        tree = new long[4*n];   // 구간합 트리
        makeTree(1, n, 1);
        
        for (int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if (a == 1) {   // 교환
                long dif = c - arr[b];
                arr[b] = c;
                updateTree(1, n, 1, b, dif);   // 루트 노드부터 업데이트
            } else {    // 출력
                sb.append(sum(1, n, 1, b, (int)c)).append("\n");
            }
        }
        
        System.out.println(sb);
        br.close();
    }
    
    // arr의 s~e 구간합 트리 만들기
    void makeTree(int s, int e, int idx) {
        if (s == e) {
            tree[idx] = arr[s];     // idx : 리프
            return;
        }
        
        int mid = (s + e) / 2;
        
        makeTree(s, mid, idx*2);   // idx 왼쪽 자식
        makeTree(mid+1, e, idx*2 + 1);     // idx 오른쪽 자식
        tree[idx] = tree[idx*2] + tree[idx*2 + 1];
    }
    
    // s~e 구간합 트리 업데이트
    void updateTree(int s, int e, int idx, int targetIdx, long dif) {
        // s~e 구간에 targetIdx가 속하지 않는 경우
        if (targetIdx < s || e < targetIdx) return;
        
        tree[idx] += dif;
        
        if (s == e) return;     // idx : 리프
        
        int mid = (s + e) / 2;
        updateTree(s, mid, idx*2, targetIdx, dif);  // idx 왼쪽 자식 업데이트
        updateTree(mid+1, e, idx*2 + 1, targetIdx, dif);    // idx 오른쪽 자식 업데이트
    }
    
    // s~e 구간합 트리 이용해서 l~r 구간합 계산
    long sum(int s, int e, int idx, int l, int r) {
        // idx부터 시작하는 서브트리 범위에 l~r이 속하지 않는 경우
        if (e < l || r < s) return 0;
        
        // 서브트리 범위가 l~r에 속하는 경우
        if (l <= s && e <= r) return tree[idx];
        
        // 서브트리 범위의 일부가 l~r에 속하는 경우 -> 하위 서브트리로 쪼개기
        int mid = (s + e) / 2;
        return sum(s, mid, idx*2, l, r) + sum(mid+1, e, idx*2 + 1, l, r);
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 최대 2억번의 계산을 반복 -> 시간초과 위험
 * 세그먼트 트리로 [변경 발생하는 배열 구간합 계산] 시간 복잡도 낮추기
 * 
 */